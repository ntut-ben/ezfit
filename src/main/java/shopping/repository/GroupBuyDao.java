package shopping.repository;

import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;

public interface GroupBuyDao {

	GroupBuyBean createGroupBuy(GroupBuyBean groupBuyBean);

	GroupBuyBean queryGroupBuyById(Integer id);

	GroupBuyBean queryGroupBuyByAlias(String alias);

}
