package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import chat.handler.HttpSessionIdHandshakeInterceptor;
import chat.handler.PresenceChannelInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	/**
	 * 服務器要監聽的端口，message會從這裏進來，要對這裏加一個Handler 這樣在網頁中就可以通過websocket連接上服務了
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		// 註冊stomp的節點，映射到指定的url,並指定使用sockjs協議
		stompEndpointRegistry.addEndpoint("/contactChatSocket").withSockJS()
				.setInterceptors(httpSessionIdHandshakeInterceptor());
		;
	}

	// 配置消息代理
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// queue、topic、user代理
		registry.enableSimpleBroker("/queue", "/topic", "/user");
		registry.setUserDestinationPrefix("/user/");
	}

	/**
	 * 消息傳輸參數配置
	 */
	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		registry.setMessageSizeLimit(8192) // 設置消息字節數大小
				.setSendBufferSizeLimit(8192)// 設置消息緩存大小
				.setSendTimeLimit(10000); // 設置消息發送時間限制毫秒
	}

	/**
	 * 輸入通道參數設置
	 */
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.taskExecutor().corePoolSize(4) // 設置消息輸入通道的線程池線程數
				.maxPoolSize(8)// 最大線程數
				.keepAliveSeconds(60);// 線程活動時間
		registration.interceptors(presenceChannelInterceptor());
	}

	/**
	 * 輸出通道參數設置
	 */
	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		registration.taskExecutor().corePoolSize(4).maxPoolSize(8);
		registration.interceptors(presenceChannelInterceptor());
	}

	@Bean
	public HttpSessionIdHandshakeInterceptor httpSessionIdHandshakeInterceptor() {
		return new HttpSessionIdHandshakeInterceptor();
	}

	@Bean
	public PresenceChannelInterceptor presenceChannelInterceptor() {
		return new PresenceChannelInterceptor();
	}

}
