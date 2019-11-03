package shopping.repository.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;
import shopping.repository.GroupBuyDao;

@Repository
public class GroupBuyDaoImpl implements GroupBuyDao {

	SessionFactory factory;

	public GroupBuyDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public String createGroupBuy(GroupBuyBean groupBuyBean) {

		factory.getCurrentSession().persist(groupBuyBean);
		String myHash = null;

		MemberBean memberBean = groupBuyBean.getMemberBean();
		String hql = "From GroupBuyBean where memberBean =:FK_MemberID ORDER BY id DESC";
		GroupBuyBean bean = (GroupBuyBean) factory.getCurrentSession().createQuery(hql)
				.setParameter("FK_MemberID", memberBean).setMaxResults(1).getSingleResult();

		Integer idHash = bean.getId().toString().hashCode();
		Integer groupNameHash = bean.getGroupName().toString().hashCode();
		Integer combainHash = (idHash.toString() + groupNameHash.toString()).hashCode();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(combainHash.toString().getBytes());
			byte[] digest = md.digest();
			myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
			bean.setGroupAlias(myHash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;
	}

	@Override
	public GroupBuyBean queryGroupBuyById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupBuyBean queryGroupBuyByAlias(String alias) {
		GroupBuyBean bean = null;
		try {
			String hql = "From GroupBuyBean where groupAlias =:groupAlias";
			bean = (GroupBuyBean) factory.getCurrentSession().createQuery(hql).setParameter("groupAlias", alias)
					.getSingleResult();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public void saveOrUpdate(GroupBuyBean groupBuyBean) {
		factory.getCurrentSession().saveOrUpdate(groupBuyBean);
	}

	@Override
	public void joinGroupBuy(GroupBuyBean groupBuyBean) {
		factory.getCurrentSession().persist(groupBuyBean);

	}

	@Override
	public void checkoutGroupBuy(Integer id, MemberBean memberBean) {
		GroupBuyBean bean = null;
		try {
			String hql = "From GroupBuyBean where id =:id and memberBean=:memberBean";
			bean = (GroupBuyBean) factory.getCurrentSession().createQuery(hql).setParameter("id", id)
					.setParameter("memberBean", memberBean).getSingleResult();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setStatus(1);
	}

	@Override
	public List<GroupBuyBean> queryGroupBuyByMember(MemberBean memberBean) {
		List<GroupBuyBean> beans = null;
		try {
			String hql = "From GroupBuyBean where memberBean =:memberBean";
			beans = factory.getCurrentSession().createQuery(hql).setParameter("memberBean", memberBean).getResultList();
		} catch (NoResultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return beans;
	}
}
