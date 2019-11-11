package websocket.controller;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import createAccount.model.MemberBean;
import createAccount.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import shopping.service.GroupBuyService;
import websocket.component.CrAlertException;
import websocket.component.CrMessage;
import websocket.component.CrMessageService;
import websocket.service.MessageService;

/**
 * @author prinswu
 * @version v1.0
 * @since v1.0 2018/11/26
 */
@Controller
@Slf4j
public class ChatRoomWebSocketController {

	@Autowired
	MemberService memberService;

	@Autowired
	GroupBuyService groupBuyService;

	@Autowired
	MessageService messageService;

	@Autowired
	CrMessageService crMessageService;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	// 從database拿取消息
	@MessageMapping("/connect/{roomId}")
	public CrMessage connectWs(CrMessage crMessage, @DestinationVariable("roomId") String roomId,
			SimpMessageHeaderAccessor headerAccessor) throws CrAlertException {
		List<CrMessage> crMessages = null;
		String mrid = crMessage.getCrid();
		String uid = crMessage.getSender();
		HttpSession session = (HttpSession) headerAccessor.getSessionAttributes().get("HTTP_SESSION");
		MemberBean memberBean = (MemberBean) session.getAttribute("LoginOK");
		crMessageService.connectChatRoom(mrid, uid);
		crMessages = messageService.loadMessage(mrid);
		if (crMessages != null) {
			Collections.reverse(crMessages);
			for (Iterator iterator = crMessages.iterator(); iterator.hasNext();) {
				CrMessage crMessage2 = (CrMessage) iterator.next();
				messagingTemplate.convertAndSend("/topic/room/" + roomId, crMessage2);
			}
		}

		return null;
	}

	@MessageMapping("/room/{roomId}")
	public CrMessage[] sendMrMessage(CrMessage crMessage, @DestinationVariable("roomId") String roomId)
			throws CrAlertException {
		crMessage = crMessageService.sendCrMessage(crMessage);
		messageService.saveMessage(crMessage);
		System.out.println(crMessage.getCrn());
		messagingTemplate.convertAndSend("/topic/popup/" + roomId, crMessage);
		return new CrMessage[] { crMessage };
	}

}
