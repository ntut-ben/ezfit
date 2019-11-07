package createAccount.service;

import createAccount.model.CodeBean;

public interface CodeService {

	int saveCode(CodeBean cb);

	boolean emailExists(String email);

	String queryCode(String email);

}