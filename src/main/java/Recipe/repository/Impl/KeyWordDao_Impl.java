package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.KeyWordBean;
import Recipe.repository.KeyWordDao;

@Repository
public class KeyWordDao_Impl implements KeyWordDao {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	KeyWordBean kw;

	public KeyWordDao_Impl() {
	}

	@Override
	public KeyWordBean getKeyWordByKey(String key) {
		Session session = factory.getCurrentSession();
		String hql = "FROM KeyWordBean K WHERE K.keyWord = :key";
		try {
			kw = (KeyWordBean) session.createQuery(hql).setParameter("key", key).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return kw;
	}

	@Override
	public int insertKeyWord(String key) {
		int n = 0;
		Session session = factory.getCurrentSession();
		kw = new KeyWordBean();
		kw.setKeyWord(key);
		kw.setCount(1);
		session.save(kw);
		return n;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KeyWordBean> checkKeyWord(String key) {
		List<KeyWordBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM KeyWordBean K WHERE K.keyWord = :key ORDER BY K.count DESC ";
		list = session.createQuery(hql).setParameter("key", key).setMaxResults(10).getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<KeyWordBean> checkKeyWord() {
		List<KeyWordBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		String hql = "FROM KeyWordBean K ORDER BY K.count DESC ";
		list = session.createQuery(hql).setMaxResults(10).getResultList();
		return list;

	}

	@Override
	public int updateKeyWord(String key) {
		int n = 0;
		Session session = factory.getCurrentSession();
		kw = getKeyWordByKey(key);
		kw.setCount(kw.getCount() + 1);
		try {
			session.save(kw);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n;
	}

}
