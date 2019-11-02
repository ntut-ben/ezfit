package shopping.repository.impl;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shopping.model.OrderItemBean;
import shopping.repository.OrderItemDao;

@Repository
public class OrderItemDaoImpl implements OrderItemDao {
	SessionFactory factory;

	@Autowired
	public OrderItemDaoImpl(SessionFactory factory) {
		this.factory = factory;
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
