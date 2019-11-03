package shopping.repository;

import java.util.List;

import createAccount.model.MemberBean;
import shopping.model.OrderBean;

public interface OrderDao {
	public void save(OrderBean orderBean);

	public OrderBean query(MemberBean memberBean);

	public OrderBean query(Integer orderId, MemberBean memberBean);

	public List<OrderBean> queryOrderBeans(MemberBean memberBean);
}
