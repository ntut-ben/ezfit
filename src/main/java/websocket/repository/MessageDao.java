package websocket.repository;

import java.util.List;

import org.hibernate.Session;

import websocket.component.CrMessage;

public interface MessageDao {
	void saveMessage(CrMessage crMessage, Session session);

	List<CrMessage> loadMessage(String crid, Session session);
}
