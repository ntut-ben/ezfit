package createAccount.repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import createAccount.model.CodeBean;

@Repository
public class CodeDaoImpl implements CodeDao {

	SessionFactory factory;

	@Autowired
	public CodeDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public int saveCode(CodeBean cb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.save(cb);
		n++;
		return n;
	}

	@Override
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

	@Override
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
