package shopping.repository;

import java.util.List;

import shopping.model.ProductCategory;

public interface ProductCategoryDao {

	public List<ProductCategory> getAllProcutCategorys();

	public ProductCategory getProductCategoryByCategory(String category);

	public void insertFakeData(List<ProductCategory> productCategories);

}
