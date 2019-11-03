package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.MemberBean;
import shopping.model.GroupBuyBean;
import shopping.model.OrderBean;
import shopping.repository.OrderDao;
import shopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	OrderDao orderDaoImpl;

	@Autowired
	public OrderServiceImpl(OrderDao orderDaoImpl) {
		this.orderDaoImpl = orderDaoImpl;
	}

	@Override
	@Transactional
	public void save(OrderBean orderBean) {
		orderDaoImpl.save(orderBean);
	}

	@Override
	@Transactional
	public OrderBean query(MemberBean memberBean) {
		return orderDaoImpl.query(memberBean);
	}

	@Override
	@Transactional
	public List<OrderBean> queryOrderBeans(MemberBean memberBean) {
		return orderDaoImpl.queryOrderBeans(memberBean);
	}

	@Override
	@Transactional
	public OrderBean query(Integer orderId, MemberBean memberBean) {
		return orderDaoImpl.query(orderId, memberBean);
	}

	@Override
	public OrderBean query(GroupBuyBean groupBuyBean, MemberBean memberBean) {
		return orderDaoImpl.query(groupBuyBean, memberBean);
	}

}
