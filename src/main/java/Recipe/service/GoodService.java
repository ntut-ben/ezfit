package Recipe.service;

import Recipe.model.GoodBean;
import Recipe.model.RecipeBean;
import createAccount.model.MemberBean;

public interface GoodService {

	//	查詢資料
	GoodBean selectGood(String recipeId, String memberId);

	GoodBean selectGood(RecipeBean recipeId, MemberBean memberId);

	//	刪除
	void deleteGood(String recipeId, String memberId);

	void deleteGood(RecipeBean recipeId, MemberBean memberId);

	//	新增
	void insertGood(String recipeId, String memberId);

}