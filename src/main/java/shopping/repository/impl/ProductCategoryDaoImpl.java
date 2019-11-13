package shopping.repository.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shopping.model.ProductCategory;
import shopping.repository.ProductCategoryDao;

@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao {
	SessionFactory factory;
	ProductCategory productCategory = null;

	@Autowired
	public ProductCategoryDaoImpl(SessionFactory factory) {
		this.factory = factory;
	}

	public List<ProductCategory> getAllProcutCategorys() {
		List<ProductCategory> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = " FROM ProductCategory pc";
			Query query = session.createQuery(hql);
			results = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public ProductCategory getProductCategoryByCategory(String category) {
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM ProductCategory PC where PC.category=?0";
			Query query = session.createQuery(hql);
			query.setParameter(0, category);
			productCategory = (ProductCategory) query.getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return productCategory;
	}

	public void insertFakeData(List<ProductCategory> productCategories) {
		try {
			Session session = factory.getCurrentSession();
			for (Iterator iterator = productCategories.iterator(); iterator.hasNext();) {
				ProductCategory productCategory = (ProductCategory) iterator.next();
				session.persist(productCategory);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
