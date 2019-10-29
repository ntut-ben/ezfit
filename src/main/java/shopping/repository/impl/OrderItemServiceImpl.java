package shopping.repository.impl;

import shopping.model.OrderItemBean;
import shopping.repository.OrderItemDao;
import shopping.repository.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {

	OrderItemDao orderItemDao;

	public OrderItemServiceImpl() {
		orderItemDao = new OrderItemDaoImpl();
	}

	@Override
	public OrderItemBean query(Integer orderItemId) {

		return orderItemDao.query(orderItemId);
	}

}
