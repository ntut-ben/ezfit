package Recipe.repository;

import java.util.List;

import Recipe.model.KeyWordBean;

public interface KeyWordDao {

	KeyWordBean getKeyWordByKey(String key);

	int insertKeyWord(String key);

	List<KeyWordBean> checkKeyWord(String key);

	List<KeyWordBean> checkKeyWord();
	
	int updateKeyWord(String key);

}