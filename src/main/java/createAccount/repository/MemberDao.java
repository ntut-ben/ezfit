package createAccount.repository;

import createAccount.model.MemberBean;

public interface MemberDao {

	int saveMember(MemberBean mb);

	int updateMemInfo(MemberBean memberbean);

	boolean emailExists(String email);

	MemberBean checkIDPassword(String email, String password);

	String queryPassword(String email);

	MemberBean queryMB(String email);

}