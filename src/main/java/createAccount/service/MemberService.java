package createAccount.service;

import createAccount.model.MemberBean;

public interface MemberService {

	// 新增會員到Member
	int saveMember(MemberBean mb);

	// 檢查Email是否已存在
	boolean emailExists(String email);

}