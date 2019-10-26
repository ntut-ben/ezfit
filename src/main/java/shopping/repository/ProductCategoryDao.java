package shopping.repository;

import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import shopping.model.IngredientProduct;
import shopping.model.ProductCategory;

public interface ProductCategoryDao {

	public List<ProductCategory> getAllProcutCategorys();

	public ProductCategory getProductCategoryByCategory(String category);

	public void insertFakeData(List<ProductCategory> productCategories);

}
