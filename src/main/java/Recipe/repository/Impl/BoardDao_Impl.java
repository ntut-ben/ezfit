package Recipe.repository.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Recipe.model.BoardBean;
import Recipe.model.RecipeBean;
import Recipe.repository.BoardDao;
import Recipe.repository.RecipeDao;

@Repository
public class BoardDao_Impl implements BoardDao {
	SessionFactory factory;
	BoardBean board = null;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	RecipeDao rd;

	@Autowired
	public void setRecipeDao(RecipeDao recipeDao) {
		this.rd = recipeDao;
	}

	public BoardDao_Impl() {
	}

//	新增
	@Override
	public int insertBoard(BoardBean board) {
		int n = 0;
		this.board = board;
		Session session = factory.getCurrentSession();
		n = (int) session.save(board);
		return n;
	}

//	刪除
	@Override
	public int deleteBoard(BoardBean board) {
		int n = 0;
		Session session = factory.getCurrentSession();
		this.board = board;
		session.delete(board);
		++n;
		return n;
	}

	@Override
	public int deleteBoard(String pk) {
		int n = 0;
		Session session = factory.getCurrentSession();
		board = session.get(BoardBean.class, Integer.valueOf(pk));
		session.delete(board);
		++n;
		return n;
	}

//	查詢
	@Override
	@SuppressWarnings("unchecked")
	public List<BoardBean> selectBoard(String recipeId) {
		Session session = factory.getCurrentSession();
		List<BoardBean> list = new ArrayList<>();
		String hql = "FROM BoardBean BB WHERE BB.recipe = :id ORDER BY BB.boardCreateTime ASC";
		list = session.createQuery(hql).setParameter("id", rd.getRecipeByRecipeId(recipeId)).getResultList();

		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BoardBean> selectBoard(RecipeBean recipe) {
		Session session = factory.getCurrentSession();
		List<BoardBean> list = new ArrayList<>();
		String hql = "FROM BoardBean BB WHERE BB.recipe = :id ORDER BY BB.boardCreateTime ASC";
		list = session.createQuery(hql).setParameter("id", recipe).getResultList();

		return list;
	}

//	修改detail
	@Override
	public int updateBoard(String pk, String detail) {
		int n = 0;
		Session session = factory.getCurrentSession();
		board = session.get(BoardBean.class, Integer.valueOf(pk));
		board.setDetail(detail);
		session.saveOrUpdate(board);
		++n;
		return n;
	}

	@Override
	public int updateBoard(BoardBean bb, String detail) {
		int n = 0;
		Session session = factory.getCurrentSession();
		board = session.get(BoardBean.class, bb.getBoardId());
		board.setDetail(detail);
		session.saveOrUpdate(board);
		++n;
		return n;
	}

	@Override
	public BoardBean getBean(int pk) {
		Session session = factory.getCurrentSession();
		BoardBean bb;
		bb = session.get(BoardBean.class, pk);
		return bb;
	}

}
