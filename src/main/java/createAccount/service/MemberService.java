package createAccount.service;

import createAccount.model.MemberBean;

public interface MemberService {

	// 新增會員到Member
	int saveMember(MemberBean mb);

	// 更改密碼
	int updateMemInfo(MemberBean memberbean);

	// 檢查Email是否已存在
	boolean emailExists(String email);

}