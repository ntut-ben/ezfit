package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.GoodBean;
import Recipe.model.RecipeBean;
import Recipe.repository.GoodDao;
import Recipe.repository.MateralDao;
import createAccount.model.MemberBean;

@Repository
public class GoodDao_Impl implements GoodDao {
	SessionFactory factory;
	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	MemberBean member = null;
	RecipeBean recipe = null;
	GoodBean good = null;

	public GoodDao_Impl() {
	}

//	新增資料
	@Override
	public int insertGood(String recipeId, String memberId) {
		int n = 0;
		Session session = factory.getCurrentSession();
		try {
			MateralDao md = new MateralDao_Impl();
			RecipeDao_Impl rd = new RecipeDao_Impl();
			good = new GoodBean(null, md.getRecipeByRecipeId(recipeId), rd.getMemberBeanByMemberId(memberId));
			session.save(good);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int insertGood(RecipeBean recipeId, MemberBean memberId) {
		int n = 0;
		Session session = factory.getCurrentSession();
		try {
			good = new GoodBean(null, recipeId, memberId);
			session.save(good);
			++n;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

//	查詢資料(只查一筆，主要給傳資料給按鈕，判斷是否有按讚過) 新增資料一定要寫好，不能有重複資料
	@SuppressWarnings("unchecked")
	@Override
	public List<GoodBean> selectGood(String recipeId, String memberId) {
		Session session = factory.getCurrentSession();
		List<GoodBean> list = new ArrayList<>();
		MateralDao md = new MateralDao_Impl();
		RecipeDao_Impl rd = new RecipeDao_Impl();
		try {
			String hql = "FROM GoodBean GB WHERE GB.recipe = :recipe AND GB.member = :member";
			list = session.createQuery(hql).setParameter("recipe", md.getRecipeByRecipeId(recipeId))
					.setParameter("member", rd.getMemberBeanByMemberId(memberId)).getResultList();
			if (list.size() > 1)
				throw new Exception("查到重覆資料，但讚不允許有重複資料");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GoodBean> selectGood(RecipeBean recipeId, MemberBean memberId) {
		Session session = factory.getCurrentSession();
		List<GoodBean> list = new ArrayList<>();
		try {
			String hql = "FROM GoodBean GB WHERE GB.recipe = :recipe AND GB.member = :member";
			list = session.createQuery(hql).setParameter("recipe", recipeId).setParameter("member", memberId)
					.getResultList();
			if (list.size() > 1)
				throw new Exception("查到重覆資料，但讚不允許有重複資料");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	刪除資料
	@Override
	public int deleteGood(String recipeId, String memberId) {
		int n = 0;
		Session session = factory.getCurrentSession();
		List<GoodBean> list = new ArrayList<>();
		try {
			list = selectGood(recipeId, memberId);
			for (GoodBean good : list) {
				session.delete(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int deleteGood(RecipeBean recipeId, MemberBean memberId) {
		int n = 0;
		Session session = factory.getCurrentSession();
		List<GoodBean> list = new ArrayList<>();
		try {
			list = selectGood(recipeId, memberId);
			for (GoodBean good : list) {
				session.delete(good);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return n;
	}

}
