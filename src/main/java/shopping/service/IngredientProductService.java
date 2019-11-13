package shopping.service;

import java.util.List;

import javax.sql.DataSource;

import shopping.model.IngredientProduct;

public interface IngredientProductService {

	public List<IngredientProduct> getAllIngredientProducts();

	public List<IngredientProduct> getIngredientProductByCategory(String category);

	public List<IngredientProduct> getIngredientProductBySearch(DataSource ds ,String search);

	public IngredientProduct getIngredientProductById(Integer id);

	public void insertFakeData(List<IngredientProduct> ingredientProducts);
	
	public Integer getIngredientProductByName(DataSource ds, String name);
}
