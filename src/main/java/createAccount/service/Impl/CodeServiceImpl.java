package createAccount.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import createAccount.model.CodeBean;
import createAccount.repository.CodeDao;
import createAccount.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {

	CodeDao dao;

	@Autowired
	public CodeServiceImpl(CodeDao dao) {
		this.dao = dao;
	}

	@Override
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

	@Override
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
