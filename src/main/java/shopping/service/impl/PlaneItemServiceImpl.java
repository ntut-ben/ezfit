package shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.CartItem;
import shopping.repository.planeItemDao;
import shopping.service.PlaneItemService;

@Service
public class PlaneItemServiceImpl implements PlaneItemService {

	planeItemDao planeitemDao;

	@Autowired
	public PlaneItemServiceImpl(planeItemDao planeitemDao) {
		this.planeitemDao = planeitemDao;
	}

	@Override
	@Transactional
	public void delete(CartItem cartItem) {
		planeitemDao.delete(cartItem);
	}

}
