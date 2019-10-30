package shopping.service;

import shopping.model.GroupBuyBean;

public interface GroupBuyService {
	GroupBuyBean createGroupBuy(GroupBuyBean groupBuyBean);

	GroupBuyBean queryGroupBuyById(Integer id);

	GroupBuyBean queryGroupBuyByAlias(String alias);
}
