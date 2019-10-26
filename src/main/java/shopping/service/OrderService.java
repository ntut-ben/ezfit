package shopping.service;

import createAccount.model.MemberBean;
import shopping.model.OrderBean;

public interface OrderService {
	public void save(OrderBean orderBean);

	public OrderBean query(MemberBean memberBean);
}
