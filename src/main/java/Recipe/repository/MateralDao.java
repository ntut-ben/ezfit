package Recipe.repository;

import java.util.List;

import Recipe.model.MateralBean;
import Recipe.model.RecipeBean;
import shopping.model.IngredientProduct;

public interface MateralDao {

	//	用recipeId找RecipeBean，用在新增method，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增recipeId這個欄位
	RecipeBean getRecipeByRecipeId(String s);

	RecipeBean getRecipeByRecipeId(RecipeBean rb);

	IngredientProduct getIngProductByIngId(String s);

	IngredientProduct getIngProductByIngId(IngredientProduct tp);

	//	=====新增Materal=====
	int insertMateral(MateralBean materal);

	//	======刪除Materal=====
	int deleteMateral(String materalId);

	int deleteMateral(MateralBean materal);
	
	int deleteMateralByRecipeId(String recipeId);

	//	=====依據食譜查詢Materal=====
	//use	bean
	List<MateralBean> selectMateralByRecipe(String recipeId);

	List<MateralBean> selectMateralByRecipe(RecipeBean recipe);

	// 關於查詢功能use String return set<RecipeBean>
	//	BTW 可能因為lazy loading之故，故先回傳List<MateralBean>
	//	SET在service處理， 處理細節直接COPY CREATE TABLE
	List<MateralBean> selectRecipeByMateralName(String materalName);

}