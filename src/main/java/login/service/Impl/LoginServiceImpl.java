package login.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.MemberBean;
import createAccount.repository.MemberDao;
import login.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	MemberDao dao;

	@Autowired
	public LoginServiceImpl(MemberDao dao) {
		this.dao = dao;
	}

	@Override
	@Transactional
	public MemberBean checkIDPassword(String email, String password) {
		MemberBean mb = null;
		try {

			mb = dao.checkIDPassword(email, password);

		} catch (Exception ex) {

			ex.printStackTrace();
			throw new RuntimeException(ex);
		}

		return mb;
	}

	@Override
	@Transactional
	public String queryPassword(String email) {
		String password;

		try {

			password = dao.queryPassword(email);

		} catch (Exception ex) {

			throw new RuntimeException(ex);
		}
		return password;
	}

	@Override
	@Transactional
	public MemberBean queryMB(String email) {
		MemberBean mb;

		try {

			mb = dao.queryMB(email);

		} catch (Exception ex) {

			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return mb;
	}

}
