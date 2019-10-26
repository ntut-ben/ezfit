package createAccount.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import createAccount.model.MemberBean;
import createAccount.repository.MemberDaoImpl;

public class MemberServiceImpl {

	MemberDaoImpl dao;
	SessionFactory factory;

	public MemberServiceImpl() {
		this.dao = new MemberDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	// 新增會員到Member
	public int saveMember(MemberBean mb) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		int n = 0;
		try {
			tx = session.beginTransaction();
			dao.saveMember(mb);
			n++;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return n;
	}

	//檢查Email是否已存在
	public boolean emailExists(String email) {
		boolean result = false;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			result = dao.emailExists(email);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return result;

	}

}
