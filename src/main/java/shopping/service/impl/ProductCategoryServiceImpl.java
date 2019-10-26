package shopping.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;
import shopping.repository.impl.ProductCategoryDaoImpl;
import shopping.service.ProductCategoryService;

public class ProductCategoryServiceImpl implements ProductCategoryService {
	private SessionFactory factory;
	private ProductCategoryDaoImpl categoryDao;

	public ProductCategoryServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		categoryDao = new ProductCategoryDaoImpl();
	}

	@Override
	public List<ProductCategory> getAllProcutCategorys() {
		Session session = factory.getCurrentSession();
		List<ProductCategory> categories = null;
		try {
			categories = categoryDao.getAllProcutCategorys();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return categories;
	}

	@Override
	public ProductCategory getProcutCategoryByCategory(String category) {
		Session session = factory.getCurrentSession();
		ProductCategory productCategory = null;
		try {
			productCategory = categoryDao.getProductCategoryByCategory(category);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return productCategory;
	}

	@Override
	public void insertFakeData(List<ProductCategory> productCategories) {
		Session session = factory.getCurrentSession();
		try {
			categoryDao.insertFakeData(productCategories);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
