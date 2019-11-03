package shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

}
