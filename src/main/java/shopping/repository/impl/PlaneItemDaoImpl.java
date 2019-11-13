package shopping.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import shopping.model.CartItem;
import shopping.model.PlaneItem;
import shopping.repository.planeItemDao;

@Repository
public class PlaneItemDaoImpl implements planeItemDao {

	SessionFactory factory;

	public PlaneItemDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public void delete(CartItem cartItem) {
		Session session = factory.getCurrentSession();
		List<PlaneItem> planeItems = null;
		String hql = "From PlaneItem WHERE FK_CartItemID =:cartItem";
		planeItems = session.createQuery(hql).setParameter("cartItem", cartItem).getResultList();
		session.delete(planeItems);
	}

}
