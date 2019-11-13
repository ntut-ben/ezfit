package Recipe.repository;

import java.util.List;

import Recipe.model.GoodBean;
import Recipe.model.RecipeBean;
import createAccount.model.MemberBean;

public interface GoodDao {

	//	新增資料
	int insertGood(String recipeId, String memberId);

	int insertGood(RecipeBean recipeId, MemberBean memberId);

	//	查詢資料(只查一筆，主要給傳資料給按鈕，判斷是否有按讚過) 新增資料一定要寫好，不能有重複資料
	List<GoodBean> selectGood(String recipeId, String memberId);

	List<GoodBean> selectGood(RecipeBean recipeId, MemberBean memberId);

	//	刪除資料
	int deleteGood(String recipeId, String memberId);

	int deleteGood(RecipeBean recipeId, MemberBean memberId);

}