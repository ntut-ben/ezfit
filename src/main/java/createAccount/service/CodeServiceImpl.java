package createAccount.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.CodeBean;
import createAccount.repository.CodeDaoImpl;

@Service
public class CodeServiceImpl {

	@Autowired
	CodeDaoImpl dao;
	@Autowired
	SessionFactory factory;

	public CodeServiceImpl(CodeDaoImpl dao, SessionFactory factory) {
		this.dao = dao;
		this.factory = factory;
	}

	@Transactional
	public int saveCode(CodeBean cb) {

		int n = 0;
		try {
			n = dao.saveCode(cb);
			n++;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return n;
	}

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

	@Transactional
	public String queryCode(String email) {
		String code = null;
		try {
			code = dao.queryCode(email);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return code;
	}
}
