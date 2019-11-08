package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.MethodBean;
import Recipe.model.RecipeBean;
import Recipe.repository.MethodDao;
import _00.utils.GlobalString;


@Repository
public class MethodDao_Impl implements MethodDao {
	String recipeId;
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public MethodDao_Impl() {
	}

//	給食譜ID找食譜的製作方法
	@SuppressWarnings("unchecked")
	@Override
	public List<MethodBean> showMethod(String recipeId) {
		Session session = factory.getCurrentSession();
		List<MethodBean> methodlist = new ArrayList<MethodBean>();
		try {
			String hql = "FROM MethodBean MB WHERE MB.recipe = :id";
			RecipeBean id = new RecipeBean();
			id.setRecipeId(Integer.valueOf(recipeId));
			methodlist = session.createQuery(hql).setParameter("id", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return methodlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MethodBean> showMethod(RecipeBean recipe) {
		Session session = factory.getCurrentSession();
		List<MethodBean> methodlist = new ArrayList<MethodBean>();
		try {
			String hql = "FROM MethodBean MB WHERE MB.recipe = :id";
//			id.setRecipeId(Integer.valueOf(recipeId));
			methodlist = session.createQuery(hql).setParameter("id", recipe).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return methodlist;
	}

//	單筆查詢
	@Override
	public MethodBean selectMethodById(String methodId) {

		Session session = factory.getCurrentSession();
		MethodBean method = new MethodBean();
		try {
			String hql = "FROM MethodBean MB WHERE MB.id = :id";
//			id.setRecipeId(Integer.valueOf(recipeId));
			method = (MethodBean) session.createQuery(hql).setParameter("id", Integer.valueOf(methodId))
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return method;
	}

//	====================新增/修改method===============
//	修改單筆 GlobalString.JPG 要修改成傳入圖片的型態字串

	@Override
	public int insertOrUpdateMethod(MethodBean method) {
		int n = 0;

		Session session = factory.getCurrentSession();
		if (method.getId() != null) {
			MethodBean mb = session.get(MethodBean.class, method.getId());
			try {
				if (method.getRecipe() == null) {
					method.setRecipe(mb.getRecipe());
				}
//		if(method.getPic()==null) {
//			method.setPic(mb.getPic());
//		}
//				method.setFileName(mb.getId() + GlobalString.JPG);
				session.evict(mb);
				session.saveOrUpdate(method);
				++n;

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("更新Method" + n + "筆資料");

		} else {
			session.save(method);
			++n;
			System.out.println("更新Method" + n + "筆資料");
		}

		return n;
	}

//	多筆修改
	@Override
	public int updateMethod(List<MethodBean> methodlist) {
		int n = 0;

		Session session = factory.getCurrentSession();
		MethodBean mb = null;
		try {
			for (MethodBean method : methodlist) {
//				error because getId()= null;
				mb = session.get(MethodBean.class, method.getId());
				if (method.getRecipe() == null) {
					method.setRecipe(mb.getRecipe());
				}
				if (method.getFileName() == null) {
					method.setFileName(mb.getFileName());
				}
				session.evict(mb);
				session.saveOrUpdate(method);
				++n;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("更新Method" + n + "筆資料");

		return n;
	}

//	新增整組資料(傳入Method)GlobalString.JPG 要修改成傳入圖片的型態字串
	@Override
	public int insertMethod(List<MethodBean> method) {

//		Session session = factory.getCurrentSession();
		int n = 0;
		try {
			for (MethodBean met : method) {
				met.setFileName(met.getId() + GlobalString.JPG);
				insertOrUpdateMethod(met);
//			session.saveOrUpdate(met);
				++n;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Method新增" + n + "筆資料");
		return n;
	}

//==============刪除Method==================

//	刪除單筆
	@Override
	public int deleteMethod(String methodId) {
		Session session = factory.getCurrentSession();
		int n = 0;
		try {
			String hql = "DELETE FROM MethodBean MB WHERE MB.id = :id";
			n = session.createQuery(hql).setParameter("id", Integer.valueOf(methodId)).executeUpdate();
			System.out.println("成功刪除" + n + "筆");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int deleteMethod(MethodBean methodId) {
		Session session = factory.getCurrentSession();
		int n = 0;
		try {
			String hql = "DELETE FROM MethodBean MB WHERE MB.id = :id";
			n = session.createQuery(hql).setParameter("id", methodId.getId()).executeUpdate();
			System.out.println("成功刪除" + n + "筆");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	刪除多筆
	@Override
	public int deleteMethodByRecipe(String recipeId) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			String hql = "DELETE FROM MethodBean MB WHERE MB.recipe = :recipeId";
			RecipeBean id = new RecipeBean();
			id.setRecipeId(Integer.valueOf(recipeId));
			n = session.createQuery(hql).setParameter("recipeId", id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int deleteMethodByRecipe(RecipeBean recipeId) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			String hql = "DELETE FROM MethodBean MB WHERE MB.recipe = :recipeId";
			n = session.createQuery(hql).setParameter("recipeId", recipeId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

}
