package shopping.service.impl;

import shopping.model.CartItem;
import shopping.repository.PlaneItemDaoImpl;
import shopping.repository.planeItemDao;
import shopping.service.PlaneItemService;

public class PlaneItemServiceImpl implements PlaneItemService {

	planeItemDao planeitemDao;

	public PlaneItemServiceImpl() {
		planeitemDao = new PlaneItemDaoImpl();
	}

	@Override
	public void delete(CartItem cartItem) {
		planeitemDao.delete(cartItem);
	}

}
