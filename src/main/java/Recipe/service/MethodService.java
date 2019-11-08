package Recipe.service;

import java.util.List;

import Recipe.model.MethodBean;
import Recipe.model.RecipeBean;

public interface MethodService {
//	用食譜找做法
	List<MethodBean> showMethod(String recipeId);
	List<MethodBean> showMethod(RecipeBean recipe);
	
//	查詢單筆
	MethodBean selectMethodById(String methodId);
	
//	新增/修改Method(個別)
//	==新增
	void insertOrUpdateMethod(MethodBean method);
//	==修改
	void updateMethod(List<MethodBean> methodlist);
	
//	新增/修改Method(組)
	void insertMethod(List<MethodBean> method);

//	刪除 
//	==單筆
	void deleteMethod(String methodId);
//	==依食譜刪
	void deleteMethodByRecipe(String recipeId);

}