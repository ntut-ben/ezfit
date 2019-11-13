package Recipe.service;

import java.util.Set;

import Recipe.model.FollowedRecipeBean;
import Recipe.model.RecipeBean;
import createAccount.model.MemberBean;

public interface FollowedRecipeService {

	// ==========刪除==========
	void deleteFollowedRecipe(String s);

	void deleteFollowedRecipe(FollowedRecipeBean fb);

	//	==========新增==========
	void insertFollowedRecipe(FollowedRecipeBean fb);
	FollowedRecipeBean insertFollowedRecipe(String recipe, String member);
	// ==========查詢==========
	//	並將LIST轉乘SET<RecipeBean>
	Set<RecipeBean> SelectMyFollowedRecipe(String ownerId);

	Set<RecipeBean> SelectMyFollowedRecipe(MemberBean mb);
	
	String checkFollowed(String followerId,String recipeId);

}