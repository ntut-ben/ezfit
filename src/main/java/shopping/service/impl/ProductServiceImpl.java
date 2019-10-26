package shopping.service.impl;

import java.util.List;

import shopping.model.Product;
import shopping.model.ProductCategory;
import shopping.repository.impl.ProductDaoImpl;
import shopping.service.ProductService;

public class ProductServiceImpl implements ProductService {

	ProductDaoImpl productDaoImpl;

	public ProductServiceImpl() {
		productDaoImpl = new ProductDaoImpl();
	}

	@Override
	public List<Product> getProductsAll() {
		return productDaoImpl.getProductsAll();
	}

	@Override
	public List<Product> getProductsByCategory(ProductCategory category) {
		return productDaoImpl.getProductsByCategory(category);
	}

	@Override
	public Product getProductById(Integer id) {
		return productDaoImpl.getProductById(id);
	}

	@Override
	public void saveOrUpdate(Product product) {
		productDaoImpl.saveOrUpdate(product);
	}

}
