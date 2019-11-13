package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.GoodBean;
import Recipe.model.RecipeBean;
import Recipe.repository.GoodDao;
import Recipe.repository.MateralDao;
import Recipe.repository.RecipeDao;
import Recipe.service.GoodService;
import createAccount.model.MemberBean;

@Service
public class GoodService_Impl implements GoodService {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	GoodDao dao;

	@Autowired
	public void setGoodDao(GoodDao dao) {
		this.dao = dao;
	}

	RecipeDao rd;

	@Autowired
	public void setRecipeDao(RecipeDao recipeDao) {
		this.rd = recipeDao;
	}

	MateralDao md;

	@Autowired
	public void setMateralDao(MateralDao materalDao) {
		this.md = materalDao;
	}

	public GoodService_Impl() {
	}

//	查詢資料
	@Override
	@Transactional
	public GoodBean selectGood(String recipeId, String memberId) {
		GoodBean good = new GoodBean();
		good = dao.selectGood(recipeId, memberId).get(0);
		System.out.println("成功查到一筆good(按讚的食譜)");
		return good;
	}

	@Override
	@Transactional
	public GoodBean selectGood(RecipeBean recipeId, MemberBean memberId) {
		GoodBean good = new GoodBean();
		good = dao.selectGood(recipeId, memberId).get(0);
		System.out.println("成功查到一筆good(按讚的食譜)");
		return good;
	}

//	刪除
	@Override
	@Transactional
	public void deleteGood(String recipeId, String memberId) {
		@SuppressWarnings("unused")
		GoodBean good = new GoodBean();
		RecipeBean rb = new RecipeBean();
		List<GoodBean> list = new ArrayList<>();
			list = dao.selectGood(recipeId, memberId);
			if (list.size() == 1) {
				good = list.get(0);
				dao.deleteGood(recipeId, memberId);
				int goodNum = rd.searchGood(recipeId);
				rb = rd.getRecipeByRecipeId(recipeId);
				rb.setGood(goodNum);
				rd.updateRecipe(rb);
				System.out.println("成功刪除一筆good(收回讚)");
			} else {
				System.out.println("查無資料，故無法刪除，或程式錯誤，導致有多筆結果");
			}

	}

	@Override
	@Transactional
	public void deleteGood(RecipeBean recipeId, MemberBean memberId) {
		@SuppressWarnings("unused")
		GoodBean good = new GoodBean();
		List<GoodBean> list = new ArrayList<>();
			list = dao.selectGood(recipeId, memberId);
			if (list.size() == 1) {
				good = list.get(0);
				dao.deleteGood(recipeId, memberId);
				int goodNum = rd.searchGood(recipeId);
				recipeId.setGood(goodNum);
				rd.updateRecipe(recipeId);
				System.out.println("成功刪除一筆good(收回讚)");
			} else {
				System.out.println("查無資料，故無法刪除，或程式錯誤，導致有多筆結果");
			}
	}

//	新增
	@Override
	@Transactional
	public void insertGood(String recipeId, String memberId) {
		@SuppressWarnings("unused")
		GoodBean good = new GoodBean();
		int n = 0;
			good = new GoodBean(null, md.getRecipeByRecipeId(recipeId), rd.getMemberBeanByMemberId(memberId));
			n = dao.insertGood(recipeId, memberId);
			System.out.println("新增" + n + "筆Good");
			RecipeBean rb = rd.getRecipeByRecipeId(recipeId);
			rb.setGood(rd.searchGood(recipeId));

	}

}