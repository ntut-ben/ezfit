package shopping.repository;

import java.util.List;

import javax.sql.DataSource;

import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;

public interface IngredientProductDao {

//	回傳共幾筆資料
	public List<IngredientProduct> getIngredientProductsAll();

	public List<IngredientProduct> getIngredientProductsByCategory(ProductCategory category);

	List<IngredientProduct> getIngredientProductBySearch(DataSource ds, String search);

	public void insertFakeData(List<IngredientProduct> ingredientProducts);

	public IngredientProduct getIngredientProductById(Integer id);

	public Integer getIngredientProductByName(DataSource ds, String name);
}
