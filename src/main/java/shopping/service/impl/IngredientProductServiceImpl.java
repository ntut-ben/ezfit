package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;
import shopping.repository.IngredientProductDao;
import shopping.repository.ProductCategoryDao;
import shopping.service.IngredientProductService;

@Service
public class IngredientProductServiceImpl implements IngredientProductService {
	IngredientProductDao ingredientDaoImpl;
	ProductCategoryDao categoryDaoImpl;

	@Autowired
	public IngredientProductServiceImpl(IngredientProductDao ingredientDaoImpl, ProductCategoryDao categoryDaoImpl) {
		this.ingredientDaoImpl = ingredientDaoImpl;
		this.categoryDaoImpl = categoryDaoImpl;
	}

	@Transactional
	@Override
	public List<IngredientProduct> getAllIngredientProducts() {
		List<IngredientProduct> ingredientProducts = null;
		try {
			ingredientProducts = ingredientDaoImpl.getIngredientProductsAll();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return ingredientProducts;
	}
	
	@Transactional
	@Override
	public List<IngredientProduct> getIngredientProductByCategory(String category) {
		List<IngredientProduct> ingredientProducts = null;
		try {
			ProductCategory productCategory = categoryDaoImpl.getProductCategoryByCategory(category);
			ingredientProducts = ingredientDaoImpl.getIngredientProductsByCategory(productCategory);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return ingredientProducts;
	}

	@Transactional
	@Override
	public void insertFakeData(List<IngredientProduct> ingredientProducts) {
		try {
			ingredientDaoImpl.insertFakeData(ingredientProducts);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	@Transactional
	public IngredientProduct getIngredientProductById(Integer id) {
		IngredientProduct ingredientProduct = new IngredientProduct();
		try {
			ingredientProduct = ingredientDaoImpl.getIngredientProductById(id);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return ingredientProduct;
	}
}
