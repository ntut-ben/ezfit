package login.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.MemberBean;
import createAccount.repository.MemeberDao;

@Service
public class LoginServiceImpl implements LoginService {
	MemeberDao dao;

	@Autowired
	public LoginServiceImpl(MemeberDao dao) {
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

}
