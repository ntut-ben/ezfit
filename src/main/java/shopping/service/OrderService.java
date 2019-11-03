package shopping.service;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;
import shopping.model.OrderBean;

public interface OrderService {
	public void save(OrderBean orderBean);

	public List<OrderBean> queryOrderBeans(MemberBean memberBean);

	public OrderBean query(Integer orderId, MemberBean memberBean);

	public OrderBean query(MemberBean memberBean);

	public OrderBean query(GroupBuyBean groupBuyBean, MemberBean memberBean);

}
