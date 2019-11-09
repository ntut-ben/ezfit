package chat.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import chat.component.CacheManager;

public class PresenceChannelInterceptor implements ChannelInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(PresenceChannelInterceptor.class);

	@Override
	public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
		StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
		// ignore non-STOMP messages like heartbeat messages
		if (sha.getCommand() == null) {
			return;
		}
		// 這裏的sessionId和accountId對應HttpSessionIdHandshakeInterceptor攔截器的存放key
		String sessionId = sha.getSessionAttributes().get(Constants.SESSIONID).toString();
		String accountId = sha.getSessionAttributes().get(Constants.SKEY_ACCOUNT_ID).toString();
		// 判斷客戶端的連接狀態
		switch (sha.getCommand()) {
		case CONNECT:
			connect(sessionId, accountId);
			break;
		case CONNECTED:
			break;
		case DISCONNECT:
			disconnect(sessionId, accountId, sha);
			break;
		default:
			break;
		}
	}

	// 連接成功
	private void connect(String sessionId, String accountId) {
		logger.debug(" STOMP Connect [sessionId: " + sessionId + "]");
		// 存放至ehcache
		String cacheName = CacheConstant.WEBSOCKET_ACCOUNT;
		// 若在多個瀏覽器登錄，直接覆蓋保存
		CacheManager.put(cacheName, cacheName + accountId, sessionId);
	}

	// 斷開連接
	private void disconnect(String sessionId, String accountId, StompHeaderAccessor sha) {
		logger.debug("STOMP Disconnect [sessionId: " + sessionId + "]");
		sha.getSessionAttributes().remove(Constants.SESSIONID);
		sha.getSessionAttributes().remove(Constants.SKEY_ACCOUNT_ID);
		// ehcache移除
		String cacheName = CacheConstant.WEBSOCKET_ACCOUNT;
		if (CacheManager.containsKey(cacheName, cacheName + accountId)) {
			CacheManager.remove(cacheName, cacheName + accountId);
		}

	}
}
