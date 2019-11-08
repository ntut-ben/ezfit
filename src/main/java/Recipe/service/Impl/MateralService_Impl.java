package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.MateralBean;
import Recipe.model.RecipeBean;
import Recipe.repository.MateralDao;
import Recipe.service.MateralService;
import shopping.model.IngredientProduct;

@Service
public class MateralService_Impl implements MateralService {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	MateralDao materalDao;

	@Autowired
	public void setMateralDao(MateralDao materalDao) {
		this.materalDao = materalDao;
	}

	public MateralService_Impl() {
	}

	// 用recipeId找RecipeBean，用在新增materal，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增recipeId這個欄位
	@Override
	@Transactional
	public RecipeBean getRecipeByRecipeId(String s) {
		RecipeBean recipe = null;
		recipe = materalDao.getRecipeByRecipeId(s);
		return recipe;
	}

	@Override
	@Transactional
	public RecipeBean getRecipeByRecipeId(RecipeBean rb) {
		RecipeBean recipe = null;
		recipe = materalDao.getRecipeByRecipeId(rb);
		return recipe;
	}

	// 用IngredientProductId找IngredientProduct，用在新增materal，輸入字串(IngredientProductId)，回傳IngredientProduct型態，來讓Hibernate新增IngredientProductId這個欄位
	@Override
	@Transactional
	public IngredientProduct getIngProductByIngId(String s) {
		IngredientProduct ingredientProduct = null;
		ingredientProduct = materalDao.getIngProductByIngId(s);
		return ingredientProduct;
	}

	@Override
	@Transactional
	public IngredientProduct getIngProductByIngId(IngredientProduct ip) {
		IngredientProduct ingredientProduct = null;
		ingredientProduct = materalDao.getIngProductByIngId(ip);
		return ingredientProduct;
	}

//	=====新增Materal=====
	@Override
	@Transactional
	public void insertMateral(MateralBean materal) {
		materalDao.insertMateral(materal);
		System.out.println("新增一筆Materal");
	}

//	======刪除Materal=====
	@Override
	@Transactional
	public void deleteMateral(String materalId) {
		materalDao.deleteMateral(materalId);
		System.out.println("刪除一筆Materal");
	}

	@Override
	@Transactional
	public void deleteMateral(MateralBean materal) {
		materalDao.deleteMateral(materal);
		System.out.println("刪除一筆Materal");
	}

//	=====依據食譜查詢Materal=====
	@Override
	@Transactional
	public List<MateralBean> selectMateralByRecipe(String recipeId) {
		int n = 0;
		List<MateralBean> list = new ArrayList<>();
		list = materalDao.selectMateralByRecipe(recipeId);
		n = list.size();
		System.out.println("該食譜所需的materal有" + n + "筆");
		return list;
	}

	@Override
	@Transactional
	public List<MateralBean> selectMateralByRecipe(RecipeBean recipe) {
		int n = 0;
		List<MateralBean> list = new ArrayList<>();
		list = materalDao.selectMateralByRecipe(recipe);
		n = list.size();
		System.out.println("該食譜所需的materal有" + n + "筆");
		return list;
	}

//	==========用materalName去找有用到該食材的食譜==================
//	主要應用在搜尋，會把查詢結果裝在SET中，避免食譜重複
	@Override
	@Transactional
	public Set<RecipeBean> selectRecipeByMateral(String materalName) {
		int n = 0;
		List<MateralBean> list = new ArrayList<>();
		list = materalDao.selectRecipeByMateralName(materalName);
//		此處將LIST換到SET
		Set<RecipeBean> recipeSet = new HashSet<>();
		for (MateralBean mb : list) {
			recipeSet.add(mb.getRecipe());
		}
		n = recipeSet.size();
		System.out.println("有" + n + "個食譜有用到 :" + materalName);
		return recipeSet;
	}

	@Override
	@Transactional
	public void deleteMateralByRecipeId(String recipeId) {
		int n = materalDao.deleteMateralByRecipeId(recipeId);
		System.out.println("刪除食譜ID為" + recipeId + "的方法" + n + "筆");
	}

}