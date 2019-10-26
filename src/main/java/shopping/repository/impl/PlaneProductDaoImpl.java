package shopping.repository.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import _00.utils.HibernateUtils;
import shopping.model.PlaneProduct;
import shopping.repository.PlaneProductDao;

public class PlaneProductDaoImpl implements PlaneProductDao {

	SessionFactory factory;

	public PlaneProductDaoImpl() {
		this.factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void insertFakeData(List<PlaneProduct> planeProducts) {
		try {
			Session session = factory.getCurrentSession();
			for (Iterator iterator = planeProducts.iterator(); iterator.hasNext();) {
				PlaneProduct PlaneProduct = (PlaneProduct) iterator.next();
				session.persist(PlaneProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<PlaneProduct> getAllPlaneProducts() {
		List<PlaneProduct> planeProducts = null;
		try {
			Session session = factory.getCurrentSession();
			String hql = "From PlaneProduct";
			planeProducts = session.createQuery(hql).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return planeProducts;
	}

}
