package createAccount.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import createAccount.model.CodeBean;
import createAccount.repository.CodeDaoImpl;

public class CodeServiceImpl {

	CodeDaoImpl dao;
	SessionFactory factory;

	public CodeServiceImpl() {
		this.dao = new CodeDaoImpl();
		this.factory = HibernateUtils.getSessionFactory();
	}

	public int saveCode(CodeBean cb) {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		int n = 0;
		try {
			tx = session.beginTransaction();
			n = dao.saveCode(cb);
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

	public String queryCode(String email) {
		String code = null;
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			code = dao.queryCode(email);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e);
		}
		return code;
	}
}
