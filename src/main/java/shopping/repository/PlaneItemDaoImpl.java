package shopping.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import shopping.model.CartItem;
import shopping.model.PlaneItem;

public class PlaneItemDaoImpl implements planeItemDao {

	SessionFactory factory;

	public PlaneItemDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
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
