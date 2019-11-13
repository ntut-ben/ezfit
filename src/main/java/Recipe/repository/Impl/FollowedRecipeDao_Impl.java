package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.FollowedRecipeBean;
import Recipe.model.RecipeBean;
import Recipe.repository.FollowedRecipeDao;
import Recipe.repository.RecipeDao;
import createAccount.model.MemberBean;

@Repository
public class FollowedRecipeDao_Impl implements FollowedRecipeDao {
	SessionFactory factory;
	RecipeBean recipe = null;
	MemberBean member = null;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	RecipeDao rd;

	@Autowired
	public void setRecipeDao(RecipeDao recipeDao) {
		this.rd = recipeDao;
	}
	
	public FollowedRecipeDao_Impl() {
	}

//	==========刪除==========
	@Override
	public int deleteFollowedRecipe(String s) {
		int n = 0;
		Session session = factory.getCurrentSession();
		FollowedRecipeBean followed = new FollowedRecipeBean();
		followed = session.get(FollowedRecipeBean.class, Integer.valueOf(s));
		session.delete(followed);
		++n;
		return n;
	}

	@Override
	public int deleteFollowedRecipe(FollowedRecipeBean fb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		FollowedRecipeBean followed = new FollowedRecipeBean();
//		followed = session.get(FollowedRecipeBean.class, fb.getPk());
		session.delete(followed);
		++n;
		return n;
	}
//	=======================新增=======================

	@Override
	public int insertFollowedRecipe(FollowedRecipeBean followed) {
		Session session = factory.getCurrentSession();
		int n = 0;
		try {
			session.save(followed);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	==========查詢==========	
//	查詢我追蹤的食譜
//	需要在Service將LIST轉乘LIST<RecipeBean>
	@SuppressWarnings("unchecked")
	@Override
	public List<FollowedRecipeBean> selectMyFollowedRecipe(String ownerId) {
		Session session = factory.getCurrentSession();
		List<FollowedRecipeBean> list = new ArrayList<>();
		int n = 0;
		String hql = "FROM FollowedRecipeBean FB WHERE FB.member = :id";
		try {
			list = session.createQuery(hql).setParameter("id", rd.getMemberBeanByMemberId(ownerId)).getResultList();
			n = list.size();
			System.out.println("共查到" + n + "筆以蒐藏食譜");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FollowedRecipeBean> selectMyFollowedRecipe(MemberBean mb) {
		Session session = factory.getCurrentSession();
		List<FollowedRecipeBean> list = new ArrayList<>();
		int n = 0;
		String hql = "FROM FollowedRecipeBean FB WHERE FB.member = :id";
		try {
			list = session.createQuery(hql).setParameter("id", mb.getPkey()).getResultList();
			n = list.size();
			System.out.println("共查到" + n + "筆以蒐藏食譜");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	需要準備只查詢一筆，給按鈕判斷該食譜是否有被追蹤
	@SuppressWarnings("unchecked")
	@Override
	public String checkFollowed(String followerId, String recipeId) {
		String check = "notFound";
		Session session = factory.getCurrentSession();
		List<FollowedRecipeBean> list = new ArrayList<>();
		String hql = "FROM FollowedRecipeBean FB WHERE FB.recipe = :recipeId AND FB.member = :followerId";
		try {
			list = session.createQuery(hql).setParameter("recipeId", rd.getRecipeByRecipeId(recipeId))
					.setParameter("followerId", rd.getMemberBeanByMemberId(followerId)).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
			if(list.size()>0) {
				check = String.valueOf(list.get(0).getPk());
			}else {
				check = "notFound";
			}
		
		return check;
	}


	@Override
	public FollowedRecipeBean getBean(int pk) {
		Session session = factory.getCurrentSession();
		FollowedRecipeBean fb = session.get(FollowedRecipeBean.class, pk);
		
		return fb;
	}

}
