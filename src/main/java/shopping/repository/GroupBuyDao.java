package shopping.repository;

import shopping.model.GroupBuyBean;

public interface GroupBuyDao {

	String createGroupBuy(GroupBuyBean groupBuyBean);

	GroupBuyBean queryGroupBuyById(Integer id);

	GroupBuyBean queryGroupBuyByAlias(String alias);

	void saveOrUpdate(GroupBuyBean groupBuyBean);
}
