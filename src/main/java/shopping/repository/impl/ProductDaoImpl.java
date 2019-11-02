package shopping.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shopping.model.Product;
import shopping.model.ProductCategory;
import shopping.repository.ProductDao;

@Repository
public class ProductDaoImpl implements ProductDao {
	SessionFactory factory;
	ProductCategory productCategory = null;

	@Autowired
	public ProductDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	@Override
	public List<Product> getProductsAll() {
		List<Product> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM Product";
			Query query = session.createQuery(hql);
			results = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<Product> getProductsByCategory(ProductCategory category) {
		List<Product> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM Product  WHERE productCategory = :productCategory";
			results = session.createQuery(hql).setParameter("productCategory", category).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public Product getProductById(Integer id) {
		Session session = factory.getCurrentSession();
		Product product = new Product();
		try {
			product = session.get(Product.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void saveOrUpdate(Product product) {
		Session session = factory.getCurrentSession();
		try {
			session.saveOrUpdate(product);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
