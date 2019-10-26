package shopping.repository;

import createAccount.model.MemberBean;
import shopping.model.OrderBean;

public interface OrderDao {
	public void save(OrderBean orderBean);

	public OrderBean query(MemberBean memberBean);
}
