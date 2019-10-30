package shopping.service.impl;

import shopping.model.GroupBuyBean;
import shopping.repository.GroupBuyDao;
import shopping.repository.impl.GroupBuyDaoImpl;
import shopping.service.GroupBuyService;

public class GroupBuyServiceImpl implements GroupBuyService {

	GroupBuyDao groupBuyDao;

	public GroupBuyServiceImpl() {
		groupBuyDao = new GroupBuyDaoImpl();
	}

	@Override
	public GroupBuyBean createGroupBuy(GroupBuyBean groupBuyBean) {

		return groupBuyDao.createGroupBuy(groupBuyBean);
	}

	@Override
	public GroupBuyBean queryGroupBuyById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupBuyBean queryGroupBuyByAlias(String alias) {

		return groupBuyDao.queryGroupBuyByAlias(alias);
	}

}
