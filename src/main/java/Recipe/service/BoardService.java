package Recipe.service;

import java.util.List;

import Recipe.model.BoardBean;
import Recipe.model.RecipeBean;

public interface BoardService {

	//	新增留言
	void insertBoard(BoardBean board);

	void insertBoard(String recipeId, String memberId, String detail);

	//	刪除
	void deleteBoard(BoardBean board);

	void deleteBoard(String pk);

	//	查詢
	List<BoardBean> selectBoard(String recipeId);

	List<BoardBean> selectBoard(RecipeBean recipe);

	//	修改
	void updateBoard(String pk, String detail);

	void updateBoard(BoardBean bb, String detail);

}