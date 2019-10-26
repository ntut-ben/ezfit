package shopping.repository.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import shopping.model.CuisineProduct;
import shopping.model.ProductCategory;
import shopping.repository.CuisineProductDao;

public class CuisineProductDaoImpl implements CuisineProductDao {

	SessionFactory factory;

	public CuisineProductDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public List<CuisineProduct> getCuisineProductProductsAll() {
		List<CuisineProduct> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM CuisineProduct";
			Query query = session.createQuery(hql);
			results = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public List<CuisineProduct> getCuisineProductProductsByCategory(ProductCategory category) {
		List<CuisineProduct> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM CuisineProduct  WHERE productCategory = :productCategory";
			results = session.createQuery(hql).setParameter("productCategory", category).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public void insertFakeData(List<CuisineProduct> cuisineProducts) {
		Session session = factory.getCurrentSession();
		try {
			for (Iterator iterator = cuisineProducts.iterator(); iterator.hasNext();) {
				CuisineProduct cuisineProduct = (CuisineProduct) iterator.next();
				session.persist(cuisineProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public CuisineProduct getCuisineProductById(Integer id) {
		Session session = factory.getCurrentSession();
		CuisineProduct cuisineProduct = new CuisineProduct();
		try {
			cuisineProduct = session.get(CuisineProduct.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuisineProduct;
	}

	public void saveOrUpdate(CuisineProduct cuisineProduct) {
		Session session = factory.getCurrentSession();
		try {
			session.saveOrUpdate(cuisineProduct);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
