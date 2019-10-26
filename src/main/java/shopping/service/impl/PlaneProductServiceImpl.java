package shopping.service.impl;

import java.util.List;

import shopping.model.PlaneProduct;
import shopping.repository.PlaneProductDao;
import shopping.repository.ProductDao;
import shopping.repository.impl.PlaneProductDaoImpl;
import shopping.repository.impl.ProductDaoImpl;
import shopping.service.PlaneProductService;

public class PlaneProductServiceImpl implements PlaneProductService {
	PlaneProductDao planeProductDao;

	public PlaneProductServiceImpl() {
		this.planeProductDao = new PlaneProductDaoImpl();
	}

	@Override
	public void insertFakeData(List<PlaneProduct> planeProducts) {
		planeProductDao.insertFakeData(planeProducts);
	}

	@Override
	public List<PlaneProduct> getAllPlaneProducts() {
		return planeProductDao.getAllPlaneProducts();
	}

}
