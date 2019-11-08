package Recipe.repository;

import java.util.List;

import Recipe.model.FollowedRecipeBean;
import createAccount.model.MemberBean;

public interface FollowedRecipeDao {

	// ==========刪除==========
	int deleteFollowedRecipe(String s);

	int deleteFollowedRecipe(FollowedRecipeBean fb);
	// =======================新增=======================

	int insertFollowedRecipe(FollowedRecipeBean followed);

	// ==========查詢==========
	// 查詢我追蹤的食譜
	// 需要在Service將LIST轉乘SET<RecipeBean>
	List<FollowedRecipeBean> selectMyFollowedRecipe(String ownerId);

	List<FollowedRecipeBean> selectMyFollowedRecipe(MemberBean mb);

//	需要準備只查詢一筆，給按鈕判斷該食譜是否有被追蹤
	String checkFollowed(String followerId, String recipeId);
	
//	用PK拿FB
	FollowedRecipeBean getBean(int pk);

}