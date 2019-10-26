package login.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import createAccount.model.MemberBean;
import createAccount.repository.MemberDaoImpl;

public class LoginServiceImpl {
	MemberDaoImpl dao;
	SessionFactory factory;

	public LoginServiceImpl() {
		dao = new MemberDaoImpl();
		factory = HibernateUtils.getSessionFactory();
	}

	public MemberBean checkIDPassword(String email, String password) {
		MemberBean mb = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {

			mb = dao.checkIDPassword(email, password);
	
		} catch (Exception ex) {
		
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return mb;
	}

}
