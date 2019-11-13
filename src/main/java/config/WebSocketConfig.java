package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import websocket.handler.WebSocketHandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan("websocket")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic", "/user");
		registry.setApplicationDestinationPrefixes("/ws/v1");
		registry.setUserDestinationPrefix("/user/");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket-cr").withSockJS().setInterceptors(new WebSocketHandshakeInterceptor());
	}
}
