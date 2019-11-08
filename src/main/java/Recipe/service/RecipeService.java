package Recipe.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import Recipe.model.RecipeBean;
import createAccount.model.MemberBean;

public interface RecipeService {

	//	由傳入的字串(RecipeId)找到對應的 RecipeBean
	RecipeBean getRecipeByRecipeId(String recipeId);
	MemberBean getMemberByMemberId(String memberId);

	//	查詢食譜
	List<RecipeBean> selectRecipe(String memberId,boolean published);

	List<RecipeBean> selectRecipe(MemberBean member,boolean published);
	
	List<RecipeBean> selectRecipePerYear(String memberId,boolean published,String year);

//	查詢本週新食譜
	List<RecipeBean> weekNew(Date monday,Date sunday);
	
	//	用在關鍵字查詢
	Set<RecipeBean> selectRecipeByRecipeNameOrMemberName(String recipeName);
	
	//	熱門食譜
	List<RecipeBean> searchHotRecipe();

	//	刪除食譜
	int deleteRecipe(String recipeId);

	void deleteRecipe(RecipeBean recipeId);

	//	新增食譜
	void insertRecipe(RecipeBean recipe);

	//	修改食譜
	void updateRecipe(RecipeBean recipe);

}