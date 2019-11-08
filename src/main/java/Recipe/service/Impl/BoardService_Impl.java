package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.BoardBean;
import Recipe.model.RecipeBean;
import Recipe.repository.BoardDao;
import Recipe.repository.RecipeDao;
import Recipe.service.BoardService;

@Service
public class BoardService_Impl implements BoardService {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	BoardDao dao;

	@Autowired
	public void setBoardDao(BoardDao dao) {
		this.dao = dao;
	}

	RecipeDao rd;

	@Autowired
	public void setRecipeDao(RecipeDao recipeDao) {
		this.rd = recipeDao;
	}

	public BoardService_Impl() {
	}

//	新增留言
	@Override
	@Transactional
	public void insertBoard(BoardBean board) {
		int n = 0;
		RecipeBean rb = null;
		dao.insertBoard(board);
		rb = board.getRecipe();
		rb.setChat(rd.searchChat(rb));
		++n;
		System.out.println("新增" + n + "筆Board");
	}

	@Override
	@Transactional
	public void insertBoard(String recipeId, String memberId, String detail) {
		int n = 0;
		RecipeBean rb = null;
		BoardBean bb = new BoardBean();
		bb.setRecipe(rd.getRecipeByRecipeId(recipeId));
		bb.setMember(rd.getMemberBeanByMemberId(memberId));
		bb.setDetail(detail);
		dao.insertBoard(bb);
		rb = rd.getRecipeByRecipeId(recipeId);
		rb.setChat(rd.searchChat(recipeId));
		++n;
		System.out.println("新增" + n + "筆Board");
	}

//	刪除
	@Override
	@Transactional
	public void deleteBoard(BoardBean board) {
		int n = 0;
		RecipeBean rb = null;
		rb = board.getRecipe();
		dao.deleteBoard(board);
		rb.setChat(rd.searchChat(rb));
		++n;
		System.out.println("刪除" + n + "筆Board");
	}

	@Override
//	@Transactional
	public void deleteBoard(String pk) {
		int n = 0;
		RecipeBean rb = null;
		BoardBean bb = null;
		bb = dao.getBean(Integer.valueOf(pk));
		rb = bb.getRecipe();
		dao.deleteBoard(bb);
		rb.setChat(rd.searchChat(rb));
		rd.updateRecipe(rb);
		++n;
		System.out.println("刪除" + n + "筆Board");
	}

//	查詢
	@Override
	@Transactional
	public List<BoardBean> selectBoard(String recipeId) {
		int n = 0;
		List<BoardBean> list = new ArrayList<>();
		list = dao.selectBoard(recipeId);
		n = list.size();
		System.out.println("查詢到" + recipeId + "食譜有" + n + "筆留言");
		return list;
	}

	@Override
	@Transactional
	public List<BoardBean> selectBoard(RecipeBean recipe) {
		int n = 0;
		List<BoardBean> list = new ArrayList<>();
		list = dao.selectBoard(recipe);
		n = list.size();
		System.out.println("查詢到" + recipe.getRecipeName() + "食譜有" + n + "筆留言");
		return list;
	}

//	修改
	@Override
	@Transactional
	public void updateBoard(String pk, String detail) {
		int n = 0;
		dao.updateBoard(pk, detail);
		++n;
		System.out.println("已更新" + n + "筆留言內容");
	}

	@Override
	@Transactional
	public void updateBoard(BoardBean bb, String detail) {
		int n = 0;
		dao.updateBoard(bb, detail);
		++n;
		System.out.println("已更新" + n + "筆留言內容");
	}

}
