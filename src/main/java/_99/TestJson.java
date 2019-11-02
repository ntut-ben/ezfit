package _99;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;
import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginService;
import login.service.LoginServiceImpl;
import shopping.model.CartItem;
import shopping.service.impl.CartItemServiceImpl;

public class TestJson {
	public static void main(String args[]) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		LoginService loginServiceImpl = new LoginServiceImpl();
		CartItemServiceImpl cartItemServiceImpl = new CartItemServiceImpl();
		ToJson<CartItem> toJson = new ToJson<CartItem>();
		Transaction tx = null;
		List<CartItem> results = null;
		MemberBean mb = null;
		String json = "";
		try {
			tx = session.beginTransaction();
			mb = loginServiceImpl.checkIDPassword("a44567812@gmail.com", "1qaz@WSX");
			System.out.println(mb);
			results = cartItemServiceImpl.checkAllItems(mb);
			json = toJson.getArrayJson(results);
			System.out.println(json);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}

	}
}
