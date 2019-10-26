package shopping.service.impl;

import createAccount.model.MemberBean;
import shopping.model.OrderBean;
import shopping.repository.impl.OrderDaoImpl;
import shopping.service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDaoImpl orderDaoImpl;

	public OrderServiceImpl() {
		this.orderDaoImpl = new OrderDaoImpl();
	}

	@Override
	public void save(OrderBean orderBean) {
		orderDaoImpl.save(orderBean);
	}

	@Override
	public OrderBean query(MemberBean memberBean) {
		return orderDaoImpl.query(memberBean);
	}

}
