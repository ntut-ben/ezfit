package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.FollowedRecipeBean;
import Recipe.model.RecipeBean;
import Recipe.repository.FollowedRecipeDao;
import Recipe.repository.RecipeDao;
import Recipe.service.FollowedRecipeService;
import createAccount.model.MemberBean;

@Service
public class FollowedRecipeService_Impl implements FollowedRecipeService {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	FollowedRecipeDao dao;

	@Autowired
	public void setFollowedRecipeDao(FollowedRecipeDao dao) {
		this.dao = dao;
	}

	RecipeDao rd;

	@Autowired
	public void setRecipeDao(RecipeDao recipeDao) {
		this.rd = recipeDao;
	}

	public FollowedRecipeService_Impl() {
	}

	// ==========刪除==========
	@Override
	@Transactional
	public void deleteFollowedRecipe(String s) {
		RecipeBean recipe = null;
		int n = 0;
		recipe = dao.getBean(Integer.valueOf(s)).getRecipe();
		n = dao.deleteFollowedRecipe(s);
		int save = rd.searchSave(recipe);
		recipe.setSave(save);
		rd.updateRecipe(recipe);
		System.out.println("已刪除" + n + "筆FollowedRecipe紀錄");
	}

	@Override
	@Transactional
	public void deleteFollowedRecipe(FollowedRecipeBean fb) {
		RecipeBean recipe = null;
		int n = 0;
		recipe = fb.getRecipe();
		n = dao.deleteFollowedRecipe(fb);
		int save = rd.searchSave(recipe);
		recipe.setSave(save);
		rd.updateRecipe(recipe);
		System.out.println("已刪除" + n + "筆FollowedRecipe紀錄");
	}

	// =======================新增=======================

	@Override
	@Transactional
	public void insertFollowedRecipe(FollowedRecipeBean fb) {
		RecipeBean recipe = null;
		int n = 0;
		n = dao.insertFollowedRecipe(fb);
		recipe = fb.getRecipe();
		int save = rd.searchSave(recipe);
		recipe.setSave(save);
		rd.updateRecipe(recipe);
		System.out.println("已新增" + n + "筆FollowedRecipe紀錄");
	}

	@Override
	@Transactional
	public FollowedRecipeBean insertFollowedRecipe(String recipe, String member) {
		FollowedRecipeBean fb = null;
		int n = 0;

		RecipeBean rb = new RecipeBean();
		fb = new FollowedRecipeBean(null, rd.getRecipeByRecipeId(recipe), rd.getMemberBeanByMemberId(member));
		n = dao.insertFollowedRecipe(fb);
		int save = rd.searchSave(recipe);
		rb = rd.getRecipeByRecipeId(recipe);
		rb.setSave(save);
		rd.updateRecipe(rb);
		System.out.println("已新增" + n + "筆FollowedRecipe紀錄");
		return fb;
	}

	// ==========查詢==========
	// 並將LIST轉乘SET<RecipeBean>
	@Override
	@Transactional
	public Set<RecipeBean> SelectMyFollowedRecipe(String ownerId) {
		Set<RecipeBean> followedrecipe = new HashSet<>();
		List<FollowedRecipeBean> list = new ArrayList<>();
		int n = 0;
		list = dao.selectMyFollowedRecipe(ownerId);
		for (FollowedRecipeBean rb : list) {
			followedrecipe.add(rb.getRecipe());
		}
		n = followedrecipe.size();
		System.out.println("使用者: " + ownerId + "有 " + n + " 筆追蹤的食譜");
		return followedrecipe;
	}

	@Override
	@Transactional
	public Set<RecipeBean> SelectMyFollowedRecipe(MemberBean mb) {
		Set<RecipeBean> followedrecipe = new HashSet<>();
		List<FollowedRecipeBean> list = new ArrayList<>();
		int n = 0;
		list = dao.selectMyFollowedRecipe(mb);
		for (FollowedRecipeBean rb : list) {
			followedrecipe.add(rb.getRecipe());
		}
		n = followedrecipe.size();
		String ownerId = mb.getPkey().toString();
		System.out.println("使用者: " + ownerId + "有 " + n + " 筆追蹤的食譜");
		return followedrecipe;
	}

//	確認是否有追蹤
	@Override
	@Transactional
	public String checkFollowed(String followerId, String recipeId) {
		String checked = "notFound";
			checked = dao.checkFollowed(followerId, recipeId);
		return checked;
	}

}
