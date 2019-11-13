package websocket.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import websocket.component.CrMessage;
import websocket.repository.MessageDao;

@Repository
public class MessageDaoImpl implements MessageDao {

	SessionFactory factory;

	@Autowired
	public MessageDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void saveMessage(CrMessage crMessage, Session session) {

		session.persist(crMessage);
	}

	@Override
	public List<CrMessage> loadMessage(String crid, Session session) {

		List<CrMessage> crMessages = null;
		String hql = "From CrMessage where crid = :crid ORDER BY id DESC";
		crMessages = session.createQuery(hql).setParameter("crid", crid).setMaxResults(10).getResultList();
		return crMessages;
	}

}
