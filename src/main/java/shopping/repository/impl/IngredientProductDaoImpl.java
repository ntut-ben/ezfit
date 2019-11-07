package shopping.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			ingredientProduct = session.get(IngredientProduct.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientProduct;
	}

	@Override
	public List<IngredientProduct> getIngredientProductBySearch(DataSource ds, String search) {
		List<IngredientProduct> ingredientProducts = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			String sql = "SELECT a.id,a.name,a.price,a.fileName,b.unit , MATCH (a.name) AGAINST (?) AS RELEVANCE  FROM product AS a JOIN IngredientProduct  AS b ON (a.id=b.id) WHERE  FK_ProductCategory BETWEEN 4 AND 10 HAVING RELEVANCE > 0.5;";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, search);
			rs = stmt.executeQuery();
			ingredientProducts = getMaterial(rs);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ingredientProducts;
	}

// 包裝rs (商城搜尋)
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

	@Override
	public Integer getIngredientProductByName(DataSource ds, String name) {

		Integer id = null;
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			String sql = "SELECT a.id,a.name,a.price,a.fileName,b.unit , MATCH (a.name) AGAINST (?) AS RELEVANCE  FROM product AS a JOIN IngredientProduct  AS b ON (a.id=b.id) WHERE  FK_ProductCategory BETWEEN 4 AND 10 HAVING RELEVANCE > 0.8 ORDER BY RELEVANCE DESC LIMIT 1;";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs != null) {
				rs.absolute(1); // Navigate to first row
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
}
