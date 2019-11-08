package Recipe.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Recipe.model.KeyWordBean;
import Recipe.repository.KeyWordDao;
import Recipe.service.KeyWordService;

@Service
public class KeyWordService_Impl implements KeyWordService {
	SessionFactory factory;

	@Autowired
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	KeyWordDao dao;

	@Autowired
	public void setKeyWordDao(KeyWordDao dao) {
		this.dao = dao;
	}

	public KeyWordService_Impl() {
	}

//	新增關鍵字
	@Override
	@Transactional
	public void insertKeyWord(String key) {
		List<KeyWordBean> list = new ArrayList<>();
		list = dao.checkKeyWord(key);
		if (list.size() > 0) {
			dao.updateKeyWord(key);
		} else {
			dao.insertKeyWord(key);
		}
		System.out.println("關鍵字處理完畢");
	}

	@Override
	@Transactional
	public List<KeyWordBean> hotSearch() {
		List<KeyWordBean> list = new ArrayList<>();
		int n = 0;
		list = dao.checkKeyWord();
		n = list.size();
		System.out.println("查詢關鍵字共" + n + "筆");
		return list;
	}

}
