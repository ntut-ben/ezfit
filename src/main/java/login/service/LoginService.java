package login.service;

import createAccount.model.MemberBean;

public interface LoginService {

	MemberBean checkIDPassword(String email, String password);

}