package shopping.service;

import java.util.List;

import shopping.model.ProductCategory;

public interface ProductCategoryService {

	public List<ProductCategory> getAllProcutCategorys();

	public ProductCategory getProcutCategoryByCategory(String category);

	public void insertFakeData(List<ProductCategory> productCategories);

}
