package shopping.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import createAccount.model.MemberBean;
import shopping.model.CartItem;
import shopping.model.GroupBuyBean;
import shopping.model.Product;
import shopping.repository.CartItemDao;

@Repository
public class CartItemDaoImpl implements CartItemDao {

	SessionFactory factory;

	@Autowired
	public CartItemDaoImpl(SessionFactory factory) {
		this.factory = factory;
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
	public void delete(Integer id, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItem = null;
		String hql = "From CartItem WHERE memberBean = :memberBean and id = :id";
		cartItem = (CartItem) session.createQuery(hql).setParameter("memberBean", memberBean).setParameter("id", id)
				.getSingleResult();

		session.delete(cartItem);

	}

	@Override
	public List<CartItem> checkAllItems(MemberBean memberBean) {
		List<CartItem> results = null;
		List<CartItem> cartItems = new ArrayList<CartItem>();
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM CartItem WHERE memberBean = :memberBean";
			results = session.createQuery(hql).setParameter("memberBean", memberBean).getResultList();
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				CartItem cartItem = (CartItem) iterator.next();
				if (cartItem.getGroupBuyBean() == null) {
					cartItems.add(cartItem);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartItems;
	}

	@Override
	public CartItem checkItem(Product productBean, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItemPersist = null;
		try {
			String hql = "FROM CartItem WHERE product = :product and memberBean = :memberBean and groupBuyBean is null ";
			cartItemPersist = (CartItem) session.createQuery(hql).setParameter("product", productBean)
					.setParameter("memberBean", memberBean).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("此會員購物車沒有該筆商品");
		}
		return cartItemPersist;
	}

	@Override
	public CartItem checkItem(Integer cartId, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItemPersist = null;
		try {
			String hql = "FROM CartItem WHERE id = :id and memberBean = :memberBean";
			cartItemPersist = (CartItem) session.createQuery(hql).setParameter("id", cartId)
					.setParameter("memberBean", memberBean).getSingleResult();

		} catch (NoResultException e) {
			System.out.println("此會員購物車沒有該筆商品");
		}
		return cartItemPersist;
	}

	@Override
	public List<CartItem> checkAllItems(GroupBuyBean groupBuyBean, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		List<CartItem> cartItems = null;
		try {
			String hql = "FROM CartItem WHERE groupBuyBean = :groupBuyBean and memberBean = :memberBean";
			cartItems = session.createQuery(hql).setParameter("groupBuyBean", groupBuyBean)
					.setParameter("memberBean", memberBean).getResultList();
//			if (cartItemPersist != null) {
//				session.evict(cartItemPersist);
//			}
		} catch (NoResultException e) {
			System.out.println("此會員購物車沒有該筆商品");
		}
		return cartItems;
	}

	@Override
	public CartItem checkItem(Product productBean, MemberBean memberBean, GroupBuyBean groupBuyBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItemPersist = null;
		try {
			String hql = "FROM CartItem WHERE product = :product and memberBean = :memberBean and groupBuyBean=:groupBuyBean";
			cartItemPersist = (CartItem) session.createQuery(hql).setParameter("product", productBean)
					.setParameter("memberBean", memberBean).setParameter("groupBuyBean", groupBuyBean)
					.getSingleResult();
//			if (cartItemPersist != null) {
//				session.evict(cartItemPersist);
//			}
		} catch (NoResultException e) {
			System.out.println("此會員購物車沒有該筆商品");
		}
		return cartItemPersist;
	}

	@Override
	public void delete(Product productBean, MemberBean memberBean, GroupBuyBean groupBuyBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItem = null;
		String hql = "From CartItem WHERE memberBean = :memberBean and product = :product and groupBuyBean=: groupBuyBean";
		cartItem = (CartItem) session.createQuery(hql).setParameter("memberBean", memberBean)
				.setParameter("groupBuyBean", groupBuyBean).setParameter("product", productBean).getSingleResult();
		session.delete(cartItem);

	}

	@Override
	public void remove(Product productBean, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		CartItem cartItem = null;
		String hql = "From CartItem WHERE memberBean = :memberBean and product = :product and groupBuyBean is null";
		cartItem = (CartItem) session.createQuery(hql).setParameter("memberBean", memberBean)
				.setParameter("product", productBean).getSingleResult();
		session.delete(cartItem);
	}

}
