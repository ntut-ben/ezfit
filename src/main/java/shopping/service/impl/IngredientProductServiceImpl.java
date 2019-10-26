package shopping.service.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;
import shopping.repository.impl.IngredientProductDaoImpl;
import shopping.repository.impl.ProductCategoryDaoImpl;
import shopping.service.IngredientProductService;

public class IngredientProductServiceImpl implements IngredientProductService {
	IngredientProductDaoImpl ingredientDao;
	ProductCategoryDaoImpl categoryDao;

	public IngredientProductServiceImpl() {
		ingredientDao = new IngredientProductDaoImpl();
		categoryDao = new ProductCategoryDaoImpl();
	}

	@Override
	public List<IngredientProduct> getAllIngredientProducts() {
		List<IngredientProduct> ingredientProducts = null;
		try {
			ingredientProducts = ingredientDao.getIngredientProductsAll();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return ingredientProducts;
	}

	@Override
	public List<IngredientProduct> getIngredientProductByCategory(String category) {
		List<IngredientProduct> ingredientProducts = null;
		try {
			ProductCategory productCategory = categoryDao.getProductCategoryByCategory(category);
			ingredientProducts = ingredientDao.getIngredientProductsByCategory(productCategory);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return ingredientProducts;
	}

	public void insertFakeData(List<IngredientProduct> ingredientProducts) {
		try {
			ingredientDao.insertFakeData(ingredientProducts);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public IngredientProduct getIngredientProductById(Integer id) {
		IngredientProduct ingredientProduct = new IngredientProduct();
		try {
			ingredientProduct = ingredientDao.getIngredientProductById(id);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return ingredientProduct;
	}
}
