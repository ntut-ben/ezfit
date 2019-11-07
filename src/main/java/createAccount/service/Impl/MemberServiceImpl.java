package createAccount.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.MemberBean;
import createAccount.repository.MemberDao;
import createAccount.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	MemberDao dao;

	@Autowired
	public MemberServiceImpl(MemberDao dao) {
		this.dao = dao;
	}

	// 新增會員到Member
	@Override
	@Transactional
	public int saveMember(MemberBean mb) {

		int n = 0;
		try {
			dao.saveMember(mb);
			n++;
		} catch (Exception e) {

			throw new RuntimeException(e);
		}
		return n;
	}

	// 更改密碼
	@Override
	@Transactional
	public int updateMemInfo(MemberBean memberbean) {

		int n = 0;
		try {
			dao.updateMemInfo(memberbean);
			n++;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return n;
	}

	// 檢查Email是否已存在
	@Override
	@Transactional
	public boolean emailExists(String email) {
		boolean result = false;

		try {
			result = dao.emailExists(email);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;

	}

}
