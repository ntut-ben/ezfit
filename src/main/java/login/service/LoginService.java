package login.service;

import createAccount.model.MemberBean;

public interface LoginService {

	MemberBean checkIDPassword(String email, String password);

	String queryPassword(String email);

	MemberBean queryMB(String email);

}