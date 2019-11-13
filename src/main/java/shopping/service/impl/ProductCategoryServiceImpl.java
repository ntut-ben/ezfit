package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.ProductCategory;
import shopping.repository.ProductCategoryDao;
import shopping.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryDao categoryDao;

	@Autowired
	public ProductCategoryServiceImpl(ProductCategoryDao categoryDao) {

		this.categoryDao = categoryDao;
	}

	@Override
	@Transactional
	public List<ProductCategory> getAllProcutCategorys() {

		List<ProductCategory> categories = null;
		try {
			categories = categoryDao.getAllProcutCategorys();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return categories;
	}

	@Override
	@Transactional
	public ProductCategory getProcutCategoryByCategory(String category) {

		ProductCategory productCategory = null;
		try {
			productCategory = categoryDao.getProductCategoryByCategory(category);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return productCategory;
	}

	@Override
	@Transactional
	public void insertFakeData(List<ProductCategory> productCategories) {

		try {
			categoryDao.insertFakeData(productCategories);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
