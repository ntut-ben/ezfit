package Recipe.service;

import java.util.List;
import java.util.Set;

import Recipe.model.MateralBean;
import Recipe.model.RecipeBean;
import shopping.model.IngredientProduct;

public interface MateralService {

	// 用recipeId找RecipeBean，用在新增materal，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增recipeId這個欄位
	RecipeBean getRecipeByRecipeId(String s);

	RecipeBean getRecipeByRecipeId(RecipeBean rb);
	


	// 用IngredientProductId找IngredientProduct，用在新增materal，輸入字串(IngredientProductId)，回傳IngredientProduct型態，來讓Hibernate新增IngredientProductId這個欄位
	IngredientProduct getIngProductByIngId(String s);

	IngredientProduct getIngProductByIngId(IngredientProduct ip);

	//	=====新增Materal=====
	void insertMateral(MateralBean materal);

	//	======刪除Materal=====
	void deleteMateral(String materalId);

	void deleteMateralByRecipeId(String recipeId);
	
	void deleteMateral(MateralBean materal);

	//	=====依據食譜查詢Materal=====
	List<MateralBean> selectMateralByRecipe(String recipeId);

	List<MateralBean> selectMateralByRecipe(RecipeBean recipe);

	//	==========用materalName去找有用到該食材的食譜==================
	//	主要應用在搜尋，會把查詢結果裝在SET中，避免食譜重複
	Set<RecipeBean> selectRecipeByMateral(String materalName);

}