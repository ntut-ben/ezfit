package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.PlaneProduct;
import shopping.repository.PlaneProductDao;
import shopping.service.PlaneProductService;

@Service
public class PlaneProductServiceImpl implements PlaneProductService {
	PlaneProductDao planeProductDao;

	@Autowired
	public PlaneProductServiceImpl(PlaneProductDao planeProductDao) {
		this.planeProductDao = planeProductDao;
	}

	@Override
	@Transactional
	public void insertFakeData(List<PlaneProduct> planeProducts) {
		planeProductDao.insertFakeData(planeProducts);
	}

	@Override
	@Transactional
	public List<PlaneProduct> getAllPlaneProducts() {
		return planeProductDao.getAllPlaneProducts();
	}

}
