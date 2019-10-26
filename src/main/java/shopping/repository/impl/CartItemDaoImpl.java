package shopping.repository.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.Product;
import shopping.repository.CartItemDao;

public class CartItemDaoImpl implements CartItemDao {

	SessionFactory factory;

	public CartItemDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void save(CartItem cartItem) {
		Session session = factory.getCurrentSession();
		try {
			session.persist(cartItem);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CartItem update(CartItem cartItemEvict) {
		Session session = factory.getCurrentSession();
		try {
			session.saveOrUpdate(cartItemEvict);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItemEvict;
	}

	@Override
	public CartItem checkItem(CartItem cartItem) {
		Session session = factory.getCurrentSession();
		CartItem cartItemPersist = null;
		try {
			Product product = cartItem.getProduct();
			MemberBean memberBean = cartItem.getMemberBean();

			String hql = "FROM CartItem WHERE product = :product and memberBean = :memberBean";
			cartItemPersist = (CartItem) session.createQuery(hql).setParameter("product", product)
					.setParameter("memberBean", memberBean).getSingleResult();
			if (cartItemPersist != null) {
				session.evict(cartItemPersist);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return cartItemPersist;
	}

	@Override
	public int delete(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		String hql = "delete From CartItem WHERE memberBean = :memberBean";
		return session.createQuery(hql).setParameter("memberBean", memberBean).executeUpdate();
	}

	@Override
	public void delete(Product productBean, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItem = null;
		String hql = "From CartItem WHERE memberBean = :memberBean and product = :product";
		cartItem = (CartItem) session.createQuery(hql).setParameter("memberBean", memberBean)
				.setParameter("product", productBean).getSingleResult();
		session.delete(cartItem);
	}

	@Override
	public List<CartItem> checkAllItems(MemberBean memberBean) {
		List<CartItem> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM CartItem WHERE memberBean = :memberBean";
			results = session.createQuery(hql).setParameter("memberBean", memberBean).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;

	}

	@Override
	public CartItem checkItem(Product productBean, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItemPersist = null;
		try {
			String hql = "FROM CartItem WHERE product = :product and memberBean = :memberBean";
			cartItemPersist = (CartItem) session.createQuery(hql).setParameter("product", productBean)
					.setParameter("memberBean", memberBean).getSingleResult();
			if (cartItemPersist != null) {
				session.evict(cartItemPersist);
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return cartItemPersist;
	}

}
