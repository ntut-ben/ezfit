package _00.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import _00.utils.HibernateUtils;

/**
 * Servlet Filter implementation class ServiceController
 */
@WebFilter({ "/ListIngredient.do", "/MemberLogin.do", "/ShopCart.do", "/CheckShopCart.do", "/login/loginServlet.do",
		"/OrderProcess.do", "/MealBox.do", "/PlaneProductRetrieve.do", "/GroupBuy.do" })
public class ServiceController implements Filter {

	SessionFactory factory;

	public ServiceController() {

	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			chain.doFilter(request, response);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		factory = HibernateUtils.getSessionFactory();
	}

}
