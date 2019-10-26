package shopping.repository;

import java.util.List;

import shopping.model.CuisineProduct;
import shopping.model.ProductCategory;

public interface CuisineProductDao {

	public List<CuisineProduct> getCuisineProductProductsAll();

	public List<CuisineProduct> getCuisineProductProductsByCategory(ProductCategory category);

	public void insertFakeData(List<CuisineProduct> cuisineProducts);

	public CuisineProduct getCuisineProductById(Integer id);

	public void saveOrUpdate(CuisineProduct cuisineProduct);

}