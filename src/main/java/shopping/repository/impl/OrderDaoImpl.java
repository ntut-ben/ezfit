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
import shopping.model.GroupBuyBean;
import shopping.model.OrderBean;
import shopping.repository.OrderDao;

@Repository
public class OrderDaoImpl implements OrderDao {

	SessionFactory factory;

	@Autowired
	public OrderDaoImpl(SessionFactory factory) {
		this.factory = factory;
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
		orderBean = (OrderBean) session.createQuery("from OrderBean where memberBean =:FK_MemberID ORDER BY id DESC")
				.setParameter("FK_MemberID", memberBean).setMaxResults(1).getSingleResult();
		return orderBean;
	}

	@Override
	public List<OrderBean> queryOrderBeans(MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		List<OrderBean> beans = new ArrayList<OrderBean>();
		List<OrderBean> notHandleBeans = null;

		String hql = "from OrderBean where memberBean =:FK_MemberID";

		notHandleBeans = session.createQuery(hql).setParameter("FK_MemberID", memberBean).getResultList();
		for (Iterator iterator = notHandleBeans.iterator(); iterator.hasNext();) {
			OrderBean orderBean = (OrderBean) iterator.next();
			if (orderBean.getGroupBuyBean() == null) {
				beans.add(orderBean);
			} else {
				if (orderBean.getGroupBuyBean().getStatus() != 0) {
					beans.add(orderBean);
				}
			}
		}
		return beans;
	}

	@Override
	public OrderBean query(Integer orderId, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		OrderBean orderBean = null;
		orderBean = (OrderBean) session.createQuery("from OrderBean where memberBean =:FK_MemberID and id =:id")
				.setParameter("FK_MemberID", memberBean).setParameter("id", orderId).getSingleResult();
		return orderBean;
	}

	@Override
	public OrderBean query(GroupBuyBean groupBuyBean, MemberBean memberBean) {
		Session session = factory.getCurrentSession();
		OrderBean orderBean = null;
		orderBean = (OrderBean) session
				.createQuery("from OrderBean where memberBean =:FK_MemberID and groupBuyBean =:groupBuyBean")
				.setParameter("FK_MemberID", memberBean).setParameter("groupBuyBean", groupBuyBean).getSingleResult();
		return orderBean;
	}
}
