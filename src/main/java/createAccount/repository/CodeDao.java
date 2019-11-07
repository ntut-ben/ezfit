package createAccount.repository;

import createAccount.model.CodeBean;

public interface CodeDao {

	int saveCode(CodeBean cb);

	boolean emailExists(String email);

	String queryCode(String email);

}