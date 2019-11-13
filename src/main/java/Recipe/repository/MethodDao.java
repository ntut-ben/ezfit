package Recipe.repository;

import java.util.List;

import Recipe.model.MethodBean;
import Recipe.model.RecipeBean;

public interface MethodDao {

//	 給食譜ID找食譜的製作方法
	public List<MethodBean> showMethod(String recipeId);

	List<MethodBean> showMethod(RecipeBean recipe);

//	新增/修改Method(個別)
	public int insertOrUpdateMethod(MethodBean method);

//	修改Method(組)
	public int updateMethod(List<MethodBean> methodlist);

//	新增Method(組)
	public int insertMethod(List<MethodBean> method);

//	刪除Method(個別)
	public int deleteMethod(String methodId);

	public int deleteMethod(MethodBean methodId);

//	刪除Method(組)
	public int deleteMethodByRecipe(String recipeId);

	public int deleteMethodByRecipe(RecipeBean recipeId);

//	查詢單筆
	public MethodBean selectMethodById(String methodId);

}
