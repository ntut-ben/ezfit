package shopping.repository;

import shopping.model.OrderItemBean;

public interface OrderItemService {
	public OrderItemBean query(Integer orderItemId);
}
