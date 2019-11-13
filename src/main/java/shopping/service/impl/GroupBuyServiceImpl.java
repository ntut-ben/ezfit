package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;
import shopping.repository.GroupBuyDao;
import shopping.service.GroupBuyService;

@Service
public class GroupBuyServiceImpl implements GroupBuyService {

	GroupBuyDao groupBuyDaoImpl;

	@Autowired
	public GroupBuyServiceImpl(GroupBuyDao groupBuyDaoImpl) {
		this.groupBuyDaoImpl = groupBuyDaoImpl;
	}

	@Override
	@Transactional
	public String createGroupBuy(GroupBuyBean groupBuyBean) {

		return groupBuyDaoImpl.createGroupBuy(groupBuyBean);
	}

	@Override
	@Transactional
	public GroupBuyBean queryGroupBuyById(Integer id) {

		return groupBuyDaoImpl.queryGroupBuyById(id);
	}

	@Override
	@Transactional
	public List<GroupBuyBean> queryGroupBuyByMember(MemberBean memberBean) {
		// TODO Auto-generated method stub
		return groupBuyDaoImpl.queryGroupBuyByMember(memberBean);
	}

	@Override
	@Transactional
	public GroupBuyBean queryGroupBuyByAlias(String alias) {

		return groupBuyDaoImpl.queryGroupBuyByAlias(alias);
	}

	@Override
	@Transactional
	public void saveOrUpdate(GroupBuyBean groupBuyBean) {
		groupBuyDaoImpl.saveOrUpdate(groupBuyBean);
	}

	@Override
	@Transactional
	public void joinGroupBuy(GroupBuyBean groupBuyBean) {
		groupBuyDaoImpl.joinGroupBuy(groupBuyBean);

	}

	@Override
	@Transactional
	public void checkoutGroupBuy(Integer id, MemberBean memberBean) {
		groupBuyDaoImpl.checkoutGroupBuy(id, memberBean);
	}

}
