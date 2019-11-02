package shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shopping.model.Product;
import shopping.model.ProductCategory;
import shopping.repository.ProductDao;
import shopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductDao productDaoImpl;

	@Autowired
	public ProductServiceImpl(ProductDao productDaoImpl) {
		this.productDaoImpl = productDaoImpl;
	}

	@Override
	@Transactional
	public List<Product> getProductsAll() {
		return productDaoImpl.getProductsAll();
	}

	@Override
	@Transactional
	public List<Product> getProductsByCategory(ProductCategory category) {
		return productDaoImpl.getProductsByCategory(category);
	}

	@Override
	@Transactional
	public Product getProductById(Integer id) {
		return productDaoImpl.getProductById(id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Product product) {
		productDaoImpl.saveOrUpdate(product);
	}

}
