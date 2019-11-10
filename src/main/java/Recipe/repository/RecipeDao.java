package Recipe.repository;

import java.util.Date;
import java.util.List;

import Recipe.model.RecipeBean;
import createAccount.model.MemberBean;

public interface RecipeDao {

	//	用recipeId找RecipeBean，用在新增method，輸入字串(recipeId)，回傳RecipeBean型態，來讓Hibernate新增recipeId這個欄位
	RecipeBean getRecipeByRecipeId(String s);

	MemberBean getMemberBeanByMemberId(String s);

	MemberBean getMemberBeanByMemberName(String memberName);
	
//	查詢食譜讚數
	int searchGood(String recipeId);
	int searchGood(RecipeBean recipe);
	
//	查詢熱門食譜
	List<RecipeBean> searchHotRecipe();
	
	
//	查詢食譜收藏數
	int searchSave(String recipeId);
	int searchSave(RecipeBean recipe);
	
//	查詢食譜留言數
	int searchChat(String recipeId);
	int searchChat(RecipeBean recipe);
	
	
	
	
	
	//	=====傳入會員 找食譜=====
	List<RecipeBean> selectRecipe(MemberBean member,boolean bool);

	List<RecipeBean> selectRecipe(String member,boolean bool);

	//	=====傳入字串(RecipeName 找食譜)===== return set<RecipeBean>關於查詢功能，SET將在Service處理
//	用人名找
	List<RecipeBean> selectRecipeByOwnerName(String ownerName);

	List<RecipeBean> selectRecipeByRecipeName(String recipeName);
	
	//	=======刪除食譜==========
	int deleteRecipe(String recipeId);

	int deleteRecipe(RecipeBean recipeId);

	//	========新增食譜========
	int insertRecipe(RecipeBean recipe);

	//	========修改食譜========
	int updateRecipe(RecipeBean recipe);
	
//	查詢本週新食譜
	List<RecipeBean> weekNew(Date monday,Date sunday);
	
//	查詢某年某會員的食譜
	List<RecipeBean> selectRecipePerYear(String memberId,boolean published,String year);

}