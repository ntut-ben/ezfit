package shopping.service;

import java.util.List;

import shopping.model.Product;
import shopping.model.ProductCategory;

public interface ProductService {
	public List<Product> getProductsAll();

	public List<Product> getProductsByCategory(ProductCategory category);

	public Product getProductById(Integer id);

	public void saveOrUpdate(Product product);
}
