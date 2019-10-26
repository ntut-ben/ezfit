package createAccount.repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import createAccount.model.MemberBean;

public class MemberDaoImpl {
	SessionFactory factory;

	public MemberDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	public boolean emailExists(String email) {
		boolean exist = false;
		MemberBean cb = null;
		String hql = "FROM MemberBean WHERE email = :email";
		Session session = factory.getCurrentSession();
		try {
			cb = (MemberBean) session.createQuery(hql).setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {

		} catch (NonUniqueResultException e) {
			exist = true;
		}
		if (cb != null) {
			exist = true;
		}
		return exist;
	}

	public MemberBean checkIDPassword(String email, String password) {
		MemberBean mb = null;
		String hql = "FROM MemberBean WHERE email = :email AND password = :password";
		Session session = factory.getCurrentSession();
		try {
			mb = (MemberBean) session.createQuery(hql).setParameter("email", email).setParameter("password", password)
					.getSingleResult();
		} catch (NoResultException e) {
			;
		} catch (NonUniqueResultException e) {
			;
		}
		return mb;
	}

}
