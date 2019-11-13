package websocket.service.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import websocket.component.CrMessage;
import websocket.repository.MessageDao;
import websocket.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	MessageDao messageDao;
	SessionFactory factory;

	@Autowired
	public MessageServiceImpl(MessageDao messageDao, SessionFactory factory) {
		this.messageDao = messageDao;
		this.factory = factory;
	}

	@Override
	public void saveMessage(CrMessage crMessage) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			messageDao.saveMessage(crMessage, session);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}

	}

	@Override
	public List<CrMessage> loadMessage(String crid) {
		Session session = factory.openSession();
		List<CrMessage> crMessages = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			crMessages = messageDao.loadMessage(crid, session);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			session.close();
		}
		return crMessages;
	}

}
