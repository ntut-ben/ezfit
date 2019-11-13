package websocket.service;

import java.util.List;

import websocket.component.CrMessage;

public interface MessageService {

	void saveMessage(CrMessage crMessage);

	List<CrMessage> loadMessage(String crid);
}
