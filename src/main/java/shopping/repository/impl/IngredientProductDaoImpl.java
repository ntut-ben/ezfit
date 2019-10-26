package shopping.repository.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import shopping.model.CuisineProduct;
import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;
import shopping.repository.IngredientProductDao;

public class IngredientProductDaoImpl implements IngredientProductDao {
	private SessionFactory factory;

	public IngredientProductDaoImpl() {
		factory = HibernateUtils.getSessionFactory();
	}

//	回傳共幾筆資料
	public List<IngredientProduct> getIngredientProductsAll() {
		List<IngredientProduct> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM IngredientProduct IP";
			Query query = session.createQuery(hql);
			results = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public List<IngredientProduct> getIngredientProductsByCategory(ProductCategory category) {
		List<IngredientProduct> results = null;
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM IngredientProduct IP WHERE IP.productCategory = :productCategory";
			results = session.createQuery(hql).setParameter("productCategory", category).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public void insertFakeData(List<IngredientProduct> ingredientProducts) {
		Session session = factory.getCurrentSession();
		try {
			for (Iterator iterator = ingredientProducts.iterator(); iterator.hasNext();) {
				IngredientProduct ingredientProduct = (IngredientProduct) iterator.next();

				session.persist(ingredientProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IngredientProduct getIngredientProductById(Integer id) {
		Session session = factory.getCurrentSession();
		IngredientProduct ingredientProduct = new IngredientProduct();
		try {
//			String hql = "FROM IngredientProduct IP WHERE id = :id";
//			Query query = session.createQuery(hql).setParameter("id", id);
			ingredientProduct = session.get(IngredientProduct.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientProduct;
	}
}
