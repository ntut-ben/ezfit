package Recipe.service;

import java.util.List;

import Recipe.model.KeyWordBean;

public interface KeyWordService {
	
//	新增關鍵字
	void insertKeyWord(String key);
	
//	回傳十筆熱門關鍵字
	List<KeyWordBean> hotSearch();
	
}