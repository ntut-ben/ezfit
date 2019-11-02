package shopping.service;

import shopping.model.GroupBuyBean;

public interface GroupBuyService {
	String createGroupBuy(GroupBuyBean groupBuyBean);

	GroupBuyBean queryGroupBuyById(Integer id);

	GroupBuyBean queryGroupBuyByAlias(String alias);

	void saveOrUpdate(GroupBuyBean groupBuyBean);
}
