package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.MethodBean;
import Recipe.model.RecipeBean;
import Recipe.repository.MethodDao;
import Recipe.service.MethodService;

@Service
public class MethodService_Impl implements MethodService {

	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	MethodDao methodDao;

	@Autowired
	public void setMethodDao(MethodDao methodDao) {
		this.methodDao = methodDao;
	}

	MethodBean method = null;

	public MethodService_Impl() {
	}

	@Override
	@Transactional
	public List<MethodBean> showMethod(String recipeId) {
		List<MethodBean> methodlist = new ArrayList<MethodBean>();
		methodlist = methodDao.showMethod(recipeId);
		return methodlist;
	}

	@Override
	@Transactional
	public List<MethodBean> showMethod(RecipeBean recipe) {
		List<MethodBean> methodlist = new ArrayList<MethodBean>();
		methodlist = methodDao.showMethod(recipe);
		return methodlist;
	}

	@Override
	@Transactional
	public void insertOrUpdateMethod(MethodBean method) {
		methodDao.insertOrUpdateMethod(method);
		System.out.println("更新Method");
	}

	@Override
	@Transactional
	public void updateMethod(List<MethodBean> methodlist) {
		methodDao.updateMethod(methodlist);
		System.out.println("依據Recipe更新Method，完成!!!");
	}

	@Override
	@Transactional
	public void insertMethod(List<MethodBean> method) {
		methodDao.updateMethod(method);
		System.out.println("依據Recipe新增Method，完成!!!");
	}

	@Override
	@Transactional
	public void deleteMethod(String methodId) {
		methodDao.deleteMethod(methodId);
		System.out.println("刪除單筆Method，完成!!!");
	}

	@Override
	@Transactional
	public void deleteMethodByRecipe(String recipeId) {
		int n = 0;
		n = methodDao.deleteMethodByRecipe(recipeId);
		System.out.println("依據Recipe刪除Method，完成刪除" + n + "筆!!!");
	}

//	查詢單筆
	@Override
	@Transactional
	public MethodBean selectMethodById(String methodId) {
		int n = 0;
		method = methodDao.selectMethodById(methodId);
		++n;
		System.out.println("查詢到" + n + "筆method!!!");
		return method;
	}

}
