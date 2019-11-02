package shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.OrderItemBean;
import shopping.repository.OrderItemDao;
import shopping.repository.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	OrderItemDao orderItemDao;

	@Autowired
	public OrderItemServiceImpl(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	@Override
	@Transactional
	public OrderItemBean query(Integer orderItemId) {

		return orderItemDao.query(orderItemId);
	}

}
