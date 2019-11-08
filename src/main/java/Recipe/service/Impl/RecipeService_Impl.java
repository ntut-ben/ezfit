package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.RecipeBean;
import Recipe.repository.RecipeDao;
import Recipe.service.RecipeService;
import createAccount.model.MemberBean;

@Service
public class RecipeService_Impl implements RecipeService {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	RecipeDao recipeDao;

	@Autowired
	public void setRecipeDao(RecipeDao recipeDao) {
		this.recipeDao = recipeDao;
	}

	public RecipeService_Impl() {
	}

//	由傳入的字串(RecipeId)找到對應的 RecipeBean
	@Override
	@Transactional
	public RecipeBean getRecipeByRecipeId(String recipeId) {
		RecipeBean recipe = null;
		recipe = recipeDao.getRecipeByRecipeId(recipeId);
		recipe.setGood(recipeDao.searchGood(recipe));
		recipe.setSave(recipeDao.searchSave(recipe));
		recipe.setChat(recipeDao.searchChat(recipe));
		return recipe;
	}

//	查詢食譜
	@Override
	@Transactional
	public List<RecipeBean> selectRecipe(String memberId, boolean published) {
		int n = 0;
		List<RecipeBean> list = new ArrayList<>();
		list = recipeDao.selectRecipe(memberId, published);
		n = list.size();
		System.out.println("查詢到" + n + "筆Recipe紀錄");
//			將Recipe 的讚數(good)、收藏數(followed)、留言數(chat)加入食譜
		for (RecipeBean rb : list) {
			rb.setGood(recipeDao.searchGood(rb));
			rb.setSave(recipeDao.searchSave(rb));
			rb.setChat(recipeDao.searchChat(rb));
		}
		return list;
	}

	@Override
	@Transactional
	public List<RecipeBean> selectRecipe(MemberBean member, boolean published) {
		int n = 0;
		List<RecipeBean> list = new ArrayList<>();
		list = recipeDao.selectRecipe(member, published);
		n = list.size();
		System.out.println("查詢到" + n + "筆Recipe紀錄");
//			將Recipe 的讚數(good)、收藏數(followed)加入食譜
		for (RecipeBean rb : list) {
			rb.setGood(recipeDao.searchGood(rb));
			rb.setSave(recipeDao.searchSave(rb));
		}
		return list;
	}

//	用在關鍵字查詢，會搜尋食譜名，作者名
	@Override
	@Transactional
	public Set<RecipeBean> selectRecipeByRecipeNameOrMemberName(String recipeName) {
		int n = 0;
		List<RecipeBean> list = new ArrayList<>();
		try {
			list = recipeDao.selectRecipeByRecipeName(recipeName);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		此處將LIST換到SET
		Set<RecipeBean> recipeSet = new HashSet<>();

		for (RecipeBean rb : list) {
			rb.setGood(recipeDao.searchGood(rb));
			rb.setSave(recipeDao.searchSave(rb));
			recipeSet.add(rb);
		}

		n = recipeSet.size();
		System.out.println("有" + n + "個食譜有用到 :" + recipeName);
		return recipeSet;
	}

//	熱門食譜
	@Override
	@Transactional
	public List<RecipeBean> searchHotRecipe() {
		List<RecipeBean> list = new ArrayList<>();
		try {
			list = recipeDao.searchHotRecipe();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

//	刪除食譜
	@Override
	@Transactional
	public int deleteRecipe(String recipeId) {
		int n = 0;
		n = recipeDao.deleteRecipe(recipeId);
		System.out.println("刪除" + n + "筆Recipe紀錄");
		return n;
	}

	@Override
	@Transactional
	public void deleteRecipe(RecipeBean recipeId) {
		int n = 0;
		n = recipeDao.deleteRecipe(recipeId);
		System.out.println("刪除" + n + "筆Recipe紀錄");
	}

//	新增食譜
	@Override
	@Transactional
	public void insertRecipe(RecipeBean recipe) {
		int n = 0;
		recipe.setSave(0);
		n = recipeDao.insertRecipe(recipe);
		System.out.println("新增" + n + "一筆Recipe紀錄");
	}

//	修改食譜
	@Override
	@Transactional
	public void updateRecipe(RecipeBean recipe) {
		int n = 0;
		recipeDao.updateRecipe(recipe);
		++n;
		System.out.println("更新" + n + "筆Recipe資料");
	}

	@Override
	@Transactional
	public List<RecipeBean> weekNew(Date monday, Date sunday) {
		List<RecipeBean> list = new ArrayList<>();
		list = recipeDao.weekNew(monday, sunday);
		return list;
	}

	@Override
	@Transactional
	public MemberBean getMemberByMemberId(String memberId) {
		MemberBean mb = new MemberBean();
		mb = recipeDao.getMemberBeanByMemberId(memberId);
		return mb;
	}

	@Override
	@Transactional
	public List<RecipeBean> selectRecipePerYear(String memberId, boolean published, String year) {
		int n = 0;
		List<RecipeBean> list = new ArrayList<>();
		list = recipeDao.selectRecipePerYear(memberId, published, year);
		n = list.size();
		System.out.println("查詢到" + n + "筆Recipe紀錄");
//			將Recipe 的讚數(good)、收藏數(followed)加入食譜
		for (RecipeBean rb : list) {
			rb.setGood(recipeDao.searchGood(rb));
			rb.setSave(recipeDao.searchSave(rb));
		}
		return list;
	}

}
