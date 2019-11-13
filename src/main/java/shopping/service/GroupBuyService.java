package shopping.service;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;

public interface GroupBuyService {
	String createGroupBuy(GroupBuyBean groupBuyBean);

	GroupBuyBean queryGroupBuyById(Integer id);

	List<GroupBuyBean> queryGroupBuyByMember(MemberBean memberBean);

	GroupBuyBean queryGroupBuyByAlias(String alias);

	void saveOrUpdate(GroupBuyBean groupBuyBean);

	void joinGroupBuy(GroupBuyBean groupBuyBean);

	void checkoutGroupBuy(Integer id, MemberBean memberBean);

}
