package createAccount.repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import createAccount.model.CodeBean;

public class CodeDaoImpl {
	SessionFactory factory;

	public CodeDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	public int saveCode(CodeBean cb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(cb);
		n++;
		return n;
	}

	public boolean emailExists(String email) {
		boolean exist = false;
		CodeBean cb = null;
		String hql = "FROM CodeBean WHERE email = :email";
		Session session = factory.getCurrentSession();
		try {
			cb = (CodeBean) session.createQuery(hql).setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			
		} catch (NonUniqueResultException e) {
			exist = true;
		}
		if (cb != null) {
			exist = true;
		}
		return exist;
	}

	public String queryCode(String email) {
		String code = null;
		String hql = "select code FROM CodeBean WHERE email = :email";
		Session session = factory.getCurrentSession();
		try {
			code = (String) session.createQuery(hql).setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {
			;
		} catch (NonUniqueResultException e) {
			;
		}
		return code;
	}

}
