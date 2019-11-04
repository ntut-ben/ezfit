package shopping.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;
import shopping.repository.IngredientProductDao;

@Repository
public class IngredientProductDaoImpl implements IngredientProductDao {
	private SessionFactory factory;

	@Autowired
	public IngredientProductDaoImpl(SessionFactory factory) {
		this.factory = factory;
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

//	@Override
//	public List<IngredientProduct> getIngredientProductBySearch(String search) {
//		List<IngredientProduct> results = null;
//		Session session = factory.getCurrentSession();
//		try {
//			String sql = "select a.id,a.name,a.price,a.fileName,b.unit from product as a  join IngredientProduct "
//					+ "as b on (a.id=b.id) where match (a.name , a.introduction) against "
//					+ "(?) and FK_ProductCategory between 4 and 10;";
//
//			results = session.createSQLQuery(sql).setParameter(1, search).getResultList();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return results;
//	}

	@Override
	public List<IngredientProduct> getIngredientProductBySearch(DataSource ds, String search) {
		List<IngredientProduct> ingredientProducts = null;
		try {
			Connection connection = ds.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(getQuerySqlFor(search));
			System.out.println(rs);
			ingredientProducts = getMaterial(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingredientProducts;
	}

	private List<IngredientProduct> getMaterial(ResultSet rs) {
		List<IngredientProduct> ingredientProducts = new ArrayList<IngredientProduct>();
		try {
			while (rs.next()) {

				IngredientProduct ingredientProduct = new IngredientProduct();
				ingredientProduct.setId(rs.getInt("id"));
				ingredientProduct.setName(rs.getString("name"));
				ingredientProduct.setUnit(rs.getString("unit"));
				ingredientProduct.setFileName(rs.getString("fileName"));
				ingredientProduct.setPrice(rs.getInt("price"));

				ingredientProducts.add(ingredientProduct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ingredientProducts;
	}

	private String getQuerySqlFor(String search) {
		// TODO Auto-generated method stub
		return "select a.id,a.name,a.price,a.fileName,b.unit from product as a  join IngredientProduct "
				+ "as b on (a.id=b.id) where match (a.name) against ('" + search
				+ "') and FK_ProductCategory between 4 and 10;";
	}
}
