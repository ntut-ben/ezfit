package createAccount.repository;

import createAccount.model.MemberBean;

public interface MemeberDao {

	int saveMember(MemberBean mb);

	boolean emailExists(String email);

	MemberBean checkIDPassword(String email, String password);

}