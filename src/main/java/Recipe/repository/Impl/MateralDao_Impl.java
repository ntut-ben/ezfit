package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.MateralBean;
import Recipe.model.RecipeBean;
import Recipe.repository.MateralDao;
import shopping.model.IngredientProduct;

@Repository
public class MateralDao_Impl implements MateralDao {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	RecipeBean recipe = null;
	IngredientProduct ingredientProduct = null;

	public MateralDao_Impl() {
	}

//	用recipeId找RecipeBean，用在新增method，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增recipeId這個欄位
	@Override
	public RecipeBean getRecipeByRecipeId(String s) {
		RecipeBean recipe = new RecipeBean();
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM RecipeBean R WHERE R.recipeId = :pk";
			Query query = session.createQuery(hql);
			query.setParameter("pk", Integer.valueOf(s));
			recipe = (RecipeBean) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recipe;
	}

	@Override
	public RecipeBean getRecipeByRecipeId(RecipeBean rb) {
		RecipeBean recipe = new RecipeBean();
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM RecipeBean R WHERE R.recipeId = :pk";
			Query query = session.createQuery(hql);
			query.setParameter("pk", rb.getRecipeId());
			recipe = (RecipeBean) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recipe;
	}

//	用IngredientProductId找IngredientProduct，用在新增materal，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增IngredientProductId這個欄位

	@Override
	public IngredientProduct getIngProductByIngId(String s) {
		IngredientProduct ingredientProduct = new IngredientProduct();
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM IngredientProduct I WHERE I.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", Integer.valueOf(s));
			ingredientProduct = (IngredientProduct) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientProduct;
	}

	@Override
	public IngredientProduct getIngProductByIngId(IngredientProduct tp) {
		IngredientProduct ingredientProduct = new IngredientProduct();
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM IngredientProduct I WHERE I.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", tp.getId());
			ingredientProduct = (IngredientProduct) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientProduct;
	}

//=================================================================================================

//	=====新增Materal=====
	@Override
	public int insertMateral(MateralBean materal) {
		Session session = factory.getCurrentSession();
		int n = 0;
		try {
			session.save(materal);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	======刪除Materal=====
	@Override
	public int deleteMateral(String materalId) {
		int n = 0;
		Session session = factory.getCurrentSession();
		try {
			MateralBean materal = session.get(MateralBean.class, Integer.valueOf(materalId));
			session.delete(materal);

//			String hql = "DELETE FROM MateralBean MB WHERE MB.materal_Id = :id";
//			n = session.createQuery(hql).setParameter("id", Integer.valueOf(materalId)).executeUpdate();

//			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n;
	}

	@Override
	public int deleteMateral(MateralBean materal) {
		int n = 0;
		Session session = factory.getCurrentSession();
		try {
//			以下作法會刪掉其他不相干的東西
//			MateralBean materal = session.get(MateralBean.class, Integer.valueOf(materalId));
//			session.delete(materal);
			String hql = "DELETE FROM MateralBean MB WHERE MB.materal_Id = :id";
			n = session.createQuery(hql).setParameter("id", materal.getMateral_Id()).executeUpdate();

//			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n;
	}

//	=====依據食譜查詢Materal=====
//use	bean
	@SuppressWarnings("unchecked")
	@Override
	public List<MateralBean> selectMateralByRecipe(String recipeId) {
		List<MateralBean> list = new ArrayList<>();
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM MateralBean MB WHERE MB.recipe = :recipe";
			list = session.createQuery(hql).setParameter("recipe", getRecipeByRecipeId(recipeId)).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MateralBean> selectMateralByRecipe(RecipeBean recipe) {
		List<MateralBean> list = new ArrayList<>();
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM MateralBean MB WHERE MB.recipe = :recipe";
			list = session.createQuery(hql).setParameter("recipe", recipe).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 關於查詢功能use String return set<RecipeBean>
//	BTW 可能因為lazy loading之故，故先回傳List<MateralBean>
//	SET在service處理， 處理細節直接COPY CREATE TABLE
	@SuppressWarnings("unchecked")
	@Override
	public List<MateralBean> selectRecipeByMateralName(String materalName) {
		List<MateralBean> list = new ArrayList<>();
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM MateralBean MB WHERE MB.materalName like :name";
			list = session.createQuery(hql).setParameter("name", "%" + materalName + "%").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int deleteMateralByRecipeId(String recipeId) {
		int n = 0;
		try {
			Session session = factory.getCurrentSession();
			String hql = "DELETE FROM MateralBean MB WHERE MB.recipe = :id";
			RecipeBean id = new RecipeBean();
			id.setRecipeId(Integer.valueOf(recipeId));
			n = session.createQuery(hql).setParameter("id", id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

}
