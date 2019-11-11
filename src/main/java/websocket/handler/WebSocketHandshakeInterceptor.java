package websocket.handler;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import createAccount.model.MemberBean;

public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		// websocket握手建立前调用，获取httpsession
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequset = (ServletServerHttpRequest) request;

			// 这里从request中获取session,获取不到不创建，可以根据业务处理此段
			HttpSession httpSession = servletRequset.getServletRequest().getSession(false);

			MemberBean memberBean = (MemberBean) httpSession.getAttribute("LoginOK");

			if (memberBean != null) {
				// 这里打印一下session id 方便等下对比和springMVC获取到httpsession是不是同一个

				// 获取到httpsession后，可以根据自身业务，操作其中的信息，这里只是单纯的和websocket进行关联
				attributes.put("HTTP_SESSION", httpSession);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// websocket握手建立后调用
		System.out.println("websocket连接握手成功");
	}

}
