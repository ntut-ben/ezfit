package shopping.service;

import java.util.List;

import shopping.model.CuisineProduct;

public interface CuisineProductService {

	public List<CuisineProduct> getAllCuisineProducts();

	public List<CuisineProduct> getCuisineProductByCategory(String category);

	public CuisineProduct getCuisineProductById(Integer id);

	public void insertFakeData(List<CuisineProduct> cuisineProducts);

	public void saveOrUpdate(CuisineProduct cuisineProduct);
}
