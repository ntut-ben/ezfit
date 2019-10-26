package shopping.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import shopping.model.CuisineProduct;
import shopping.model.ProductCategory;
import shopping.repository.impl.CuisineProductDaoImpl;
import shopping.repository.impl.IngredientProductDaoImpl;
import shopping.repository.impl.ProductCategoryDaoImpl;
import shopping.service.CuisineProductService;

public class CuisineProductServiceImpl implements CuisineProductService {
	SessionFactory factory;
	CuisineProductDaoImpl cuisineProductDaoImpl;
	ProductCategoryDaoImpl categoryDao;

	public CuisineProductServiceImpl() {
		factory = HibernateUtils.getSessionFactory();
		cuisineProductDaoImpl = new CuisineProductDaoImpl();
		categoryDao = new ProductCategoryDaoImpl();
	}

	@Override
	public List<CuisineProduct> getAllCuisineProducts() {
		Session session = factory.getCurrentSession();
		List<CuisineProduct> cuisineProducts = null;
		try {
			cuisineProducts = cuisineProductDaoImpl.getCuisineProductProductsAll();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return cuisineProducts;
	}

	@Override
	public List<CuisineProduct> getCuisineProductByCategory(String category) {
		Session session = factory.getCurrentSession();
		List<CuisineProduct> cuisineProducts = null;
		try {
			ProductCategory productCategory = categoryDao.getProductCategoryByCategory(category);
			cuisineProducts = cuisineProductDaoImpl.getCuisineProductProductsByCategory(productCategory);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return cuisineProducts;
	}

	@Override
	public void insertFakeData(List<CuisineProduct> cuisineProducts) {
		Session session = factory.getCurrentSession();
		try {
			cuisineProductDaoImpl.insertFakeData(cuisineProducts);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
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
	public void saveOrUpdate(CuisineProduct cuisineProduct) {

		Session session = factory.getCurrentSession();
		try {
			cuisineProductDaoImpl.saveOrUpdate(cuisineProduct);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
