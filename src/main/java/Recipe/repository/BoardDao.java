package Recipe.repository;

import java.util.List;

import Recipe.model.BoardBean;
import Recipe.model.RecipeBean;

public interface BoardDao {


	//	新增
	int insertBoard(BoardBean board);

	//	刪除
	int deleteBoard(BoardBean board);

	int deleteBoard(String pk);

	//	查詢
	List<BoardBean> selectBoard(String recipeId);

	List<BoardBean> selectBoard(RecipeBean recipe);

	//	修改detail
	int updateBoard(String pk, String detail);

	int updateBoard(BoardBean bb, String detail);
	
//	用PK拿BoardBean
	BoardBean getBean(int pk);

}