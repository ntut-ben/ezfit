package shopping.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import createAccount.model.MemberBean;
import shopping.model.OrderBean;
import shopping.repository.OrderDao;

public class OrderDaoImpl implements OrderDao {

	SessionFactory factory;

	public OrderDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void save(OrderBean orderBean) {
		Session session = factory.getCurrentSession();
		try {
			session.persist(orderBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public OrderBean query(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		OrderBean orderBean = null;
		orderBean = (OrderBean) session.createQuery(
				"from OrderBean where memberBean =:FK_MemberID ORDER BY id DESC")
				.setParameter("FK_MemberID", memberBean).setMaxResults(1).getSingleResult();
		return orderBean;
	}

}
