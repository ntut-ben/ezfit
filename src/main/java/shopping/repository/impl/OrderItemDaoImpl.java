package shopping.repository.impl;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import shopping.model.OrderItemBean;
import shopping.repository.OrderItemDao;

public class OrderItemDaoImpl implements OrderItemDao {
	SessionFactory factory;

	public OrderItemDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public OrderItemBean query(Integer orderItemId) {
		OrderItemBean orderItemBean = null;
		try {
			Session session = factory.getCurrentSession();
			orderItemBean = session.get(OrderItemBean.class, orderItemId);
		} catch (NoResultException e) {
			System.out.println("沒有該筆訂單明細");
		}

		return orderItemBean;
	}

}
