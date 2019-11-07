package createAccount.repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import createAccount.model.MemberBean;

@Repository
public class MemberDaoImpl implements MemberDao {
	SessionFactory factory;

	@Autowired
	public MemberDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public int saveMember(MemberBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(mb);
		n++;
		return n;
	}

	@Override
	public int updateMemInfo(MemberBean memberbean) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(memberbean);
		n++;
		return n;
	}

	@Override
	public boolean emailExists(String email) {
		boolean exist = false;
		MemberBean mb = null;
		String hql = "FROM MemberBean WHERE email = :email";
		Session session = factory.getCurrentSession();
		try {
			mb = (MemberBean) session.createQuery(hql).setParameter("email", email).getSingleResult();
		} catch (NoResultException e) {

		} catch (NonUniqueResultException e) {
			exist = true;
		}
		if (mb != null) {
			exist = true;
		}
		return exist;
	}

	@Override
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

	@Override
	public String queryPassword(String email) {
		String hql = "select password FROM MemberBean WHERE email = :email";
		Session session = factory.getCurrentSession();
		String password = (String) session.createQuery(hql).setParameter("email", email).getSingleResult();
		return password;
	}

	@Override
	public MemberBean queryMB(String email) {
		String hql = "FROM MemberBean WHERE email = :email";
		Session session = factory.getCurrentSession();
		MemberBean mb = (MemberBean) session.createQuery(hql).setParameter("email", email).getSingleResult();
		return mb;
	}

}
