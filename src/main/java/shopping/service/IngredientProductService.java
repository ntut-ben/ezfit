package shopping.service;

import java.util.List;

import shopping.model.IngredientProduct;

public interface IngredientProductService {

	public List<IngredientProduct> getAllIngredientProducts();

	public List<IngredientProduct> getIngredientProductByCategory(String category);

	public IngredientProduct getIngredientProductById(Integer id);

	public void insertFakeData(List<IngredientProduct> ingredientProducts);
}
