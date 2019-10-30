package shopping.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;
import shopping.repository.GroupBuyDao;

public class GroupBuyDaoImpl implements GroupBuyDao {

	SessionFactory factory;

	public GroupBuyDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public GroupBuyBean createGroupBuy(GroupBuyBean groupBuyBean) {

		factory.getCurrentSession().persist(groupBuyBean);
		MemberBean memberBean = groupBuyBean.getMemberBean();
		String hql = "From GroupBuyBean where memberBean =:FK_MemberID ORDER BY id DESC";
		GroupBuyBean bean = (GroupBuyBean) factory.getCurrentSession().createQuery(hql)
				.setParameter("FK_MemberID", memberBean).setMaxResults(1).getSingleResult();
		return bean;

	}

	@Override
	public GroupBuyBean queryGroupBuyById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupBuyBean queryGroupBuyByAlias(String alias) {
		Session session = factory.getCurrentSession();
		String hql = "From GroupBuyBean where groupAlias =:groupAlias";
		GroupBuyBean bean = (GroupBuyBean) factory.getCurrentSession().createQuery(hql)
				.setParameter("groupAlias", alias).getSingleResult();
		return bean;
	}

}
