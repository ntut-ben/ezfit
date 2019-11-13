package shopping.repository;

import shopping.model.OrderItemBean;

public interface OrderItemDao {

	public OrderItemBean query(Integer orderItemId);
}
