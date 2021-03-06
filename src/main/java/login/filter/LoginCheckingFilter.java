package login.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import createAccount.model.MemberBean;

@WebFilter(urlPatterns = { "/*" }, initParams = { @WebInitParam(name = "mustLogin1", value = "/cartList"),
		@WebInitParam(name = "mustLogin2", value = "/api/shopCart/add"),
		@WebInitParam(name = "mustLogin3", value = "/memberPage/memberPage"),
		@WebInitParam(name = "mustLogin4", value = "/api/shopCart/*"),
		@WebInitParam(name = "mustLogin5", value = "/groupBuying"),
		@WebInitParam(name = "mustLogin6", value = "/api/GroupBuy/*"),
		@WebInitParam(name = "mustLogin7", value = "/publish_recipe"),
//		@WebInitParam(name = "mustLogin8", value = "/board/addNewChat"),
		@WebInitParam(name = "mustLogin9", value = "/recipe_page"),
		@WebInitParam(name = "mustLogin10", value = "/my_page"),
		@WebInitParam(name = "mustLogin11", value = "/getMember"),
		




})
public class LoginCheckingFilter implements Filter {
	List<String> url = new ArrayList<String>();
	String servletPath;
	String contextPath;
	String requestURI;

	public void init(FilterConfig fConfig) throws ServletException {
		Enumeration<String> e = fConfig.getInitParameterNames();
		while (e.hasMoreElements()) {
			String path = e.nextElement();
			url.add(fConfig.getInitParameter(path));
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isRequestedSessionIdValid = false;
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			servletPath = req.getServletPath();
			contextPath = req.getContextPath();
			requestURI = req.getRequestURI();
			isRequestedSessionIdValid = req.isRequestedSessionIdValid();
			if (mustLogin()) {
				Boolean isAjax = false;
				if (checkLogin(req)) {
					// 需要登入，但已經登入
					chain.doFilter(request, response);
				} else {
					// 需要登入，尚未登入，所以送回登入畫面
					HttpSession session = req.getSession();

					if (!isRequestedSessionIdValid) {
						session.setAttribute("timeOut", "使用逾時，請重新登入");
						session.setAttribute("requestURI", requestURI);
					} else {
						// 記住原本的"requestURI"，稍後如果登入成功，系統可以自動轉入
						// 原本要執行的程式。
						session.setAttribute("requestURI", requestURI);
					}
					isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
					if (!isAjax) {
						resp.sendRedirect(contextPath + "/login/login");
						return;
					} else {

						String redirectURL = resp.encodeRedirectURL(req.getContextPath() + "/login/login");

						StringBuilder sb = new StringBuilder();
						session.setAttribute("requestURI", req.getHeader("referer"));
						System.out.println(req.getHeader("referer"));
						sb.append("redirect");
						resp.setHeader("redirect", redirectURL);
						resp.setCharacterEncoding("UTF-8");
						resp.setContentType("text/html");
						PrintWriter pw = response.getWriter();
						pw.println(sb.toString());
						pw.flush();
					}

				}
			} else { // 不需要登入，直接去執行他要執行的程式
				chain.doFilter(request, response);
			}
		} else {
			throw new ServletException("Request/Response 型態錯誤(極不可能發生)");
		}
	}

	// 判斷Session物件內是否含有識別字串為LoginOK的屬性物件，如果有，表示已經登入，否則尚未登入
	private boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();

		MemberBean loginToken = (MemberBean) session.getAttribute("LoginOK");
		if (loginToken == null) {
			return false;
		} else {
			return true;
		}
	}

	// 如果請求的ServletPath的前導字是以某個必須登入才能使用之資源的路徑，那就必須登入。
	private boolean mustLogin() {
		boolean login = false;
		for (String sURL : url) {
			if (sURL.endsWith("*")) {
				sURL = sURL.substring(0, sURL.length() - 1); // 除去掉最後一個字元的剩餘字串
				if (servletPath.startsWith(sURL)) {
					login = true;
					break;
				}
			} else {
				if (servletPath.equals(sURL)) {
					login = true;
					break;
				}
			}
		}
		return login;
	}

	public void destroy() {
	}
}
