package shopping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import shopping.model.CuisineProduct;
import shopping.model.IngredientProduct;
import shopping.service.impl.CuisineProductServiceImpl;
import shopping.service.impl.IngredientProductServiceImpl;
import shopping.service.impl.ProductCategoryServiceImpl;

public class HelloWorld {

	public static void main(String[] args) {
		CuisineProduct cuisineProduct = new CuisineProduct();
		List<IngredientProduct> ingredientProducts = new ArrayList<IngredientProduct>();
		IngredientProductServiceImpl ingredientProductServiceImpl = new IngredientProductServiceImpl();
		CuisineProductServiceImpl cuisineProductServiceImpl = new CuisineProductServiceImpl();

		try {
			cuisineProduct = cuisineProductServiceImpl.getCuisineProductById(21);
			ingredientProducts = ingredientProductServiceImpl.getIngredientProductByCategory("vegetable");
			cuisineProduct.setIngredientProducts(new HashSet(ingredientProducts));
			System.out.println(cuisineProduct);
			cuisineProductServiceImpl.saveOrUpdate(cuisineProduct);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
