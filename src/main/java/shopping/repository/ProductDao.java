package shopping.repository;

import java.util.List;

import shopping.model.Product;
import shopping.model.ProductCategory;

public interface ProductDao {

//	回傳共幾筆資料
	public List<Product> getProductsAll();

	public List<Product> getProductsByCategory(ProductCategory category);

	public Product getProductById(Integer id);

	public void saveOrUpdate(Product product);

}