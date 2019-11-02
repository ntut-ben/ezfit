package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.CuisineProduct;
import shopping.model.ProductCategory;
import shopping.repository.CuisineProductDao;
import shopping.repository.ProductCategoryDao;
import shopping.service.CuisineProductService;

@Service
public class CuisineProductServiceImpl implements CuisineProductService {

	CuisineProductDao cuisineProductDaoImpl;
	ProductCategoryDao categoryDaoImpl;

	@Autowired
	public CuisineProductServiceImpl(CuisineProductDao cuisineProductDaoImpl, ProductCategoryDao categoryDaoImpl) {
		this.cuisineProductDaoImpl = cuisineProductDaoImpl;
		this.categoryDaoImpl = categoryDaoImpl;
	}

	@Override
	@Transactional
	public List<CuisineProduct> getAllCuisineProducts() {

		List<CuisineProduct> cuisineProducts = null;
		try {
			cuisineProducts = cuisineProductDaoImpl.getCuisineProductProductsAll();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return cuisineProducts;
	}

	@Override
	@Transactional
	public List<CuisineProduct> getCuisineProductByCategory(String category) {

		List<CuisineProduct> cuisineProducts = null;
		try {
			ProductCategory productCategory = categoryDaoImpl.getProductCategoryByCategory(category);
			cuisineProducts = cuisineProductDaoImpl.getCuisineProductProductsByCategory(productCategory);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return cuisineProducts;
	}

	@Override
	@Transactional
	public void insertFakeData(List<CuisineProduct> cuisineProducts) {

		try {
			cuisineProductDaoImpl.insertFakeData(cuisineProducts);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	@Transactional
	public CuisineProduct getCuisineProductById(Integer id) {
		CuisineProduct cuisineProduct = new CuisineProduct();
		try {
			cuisineProduct = cuisineProductDaoImpl.getCuisineProductById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cuisineProduct;
	}

	@Override
	@Transactional
	public void saveOrUpdate(CuisineProduct cuisineProduct) {

		try {
			cuisineProductDaoImpl.saveOrUpdate(cuisineProduct);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
