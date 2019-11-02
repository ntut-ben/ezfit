package shopping.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginService;
import login.service.LoginServiceImpl;
import shopping.model.GroupBuyBean;
import shopping.service.GroupBuyService;
import shopping.service.impl.GroupBuyServiceImpl;

/**
 * Servlet implementation class CheckShopCart
 */
@WebServlet("/GroupBuy.do")
public class GroupBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestHandler(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestHandler(request, response);
	}

	private void requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		GroupBuyService groupBuyService = new GroupBuyServiceImpl();
		LoginService loginServiceImpl = new LoginServiceImpl();
		String account = null, pwd = null, action = null, groupName = null, initiatorName = null, initiatorPhone = null,
				country = null, district = null, zipcode = null, address = null, deadLine = null, group = null;
		HttpSession session = request.getSession(false);
		Date deadLineSQL = null;
		action = request.getParameter("action");
		groupName = request.getParameter("groupName");
		initiatorName = request.getParameter("initiatorName");
		initiatorPhone = request.getParameter("initiatorPhone");
		country = request.getParameter("county");
		district = request.getParameter("district");
		zipcode = request.getParameter("zipcode");
		address = request.getParameter("address");
		deadLine = request.getParameter("deadLine");
		group = request.getParameter("group");
		// Parse Date
		if (deadLine != null) {
			Calendar cal = Calendar.getInstance();
			String token[] = deadLine.split("-");
			int year = Integer.parseInt(token[0]);
			int month = Integer.parseInt(token[1]);
			int date = Integer.parseInt(token[2]);
			cal.set(year, (month - 1), date);
			deadLineSQL = new Date(cal.getTimeInMillis());
		}

		// Session 取出Member Bean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();

		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginServiceImpl.checkIDPassword(account, pwd);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		if (action.trim() != "" && action != null && !action.equals("false")) {
			if (action.equals("create")) {
				Enumeration<String> enumeration = request.getParameterNames();
				// 判斷是否有資料少填，回覆錯誤
				while (enumeration.hasMoreElements()) {
					String string = (String) enumeration.nextElement();
					if (request.getParameter(string) == null) {
						response.sendError(HttpServletResponse.SC_NOT_FOUND);
						return;
					}
				}
				// 新增團購資訊

				GroupBuyBean groupBuyBean = new GroupBuyBean();
				groupBuyBean.setStatus(0);
				groupBuyBean.setDeadLine(deadLineSQL);
				groupBuyBean.setGroupName(groupName);
				groupBuyBean.setInitiatorName(initiatorName);
				groupBuyBean.setShippingPhone(initiatorPhone);
				groupBuyBean.setShippingCity(country);
				groupBuyBean.setShippingDistrict(district);
				groupBuyBean.setShippingZipCode(zipcode);
				groupBuyBean.setShippingAddress(address);
				groupBuyBean.setMemberBean(mb);
				groupBuyBean.setRole(0);
				groupBuyBean = groupBuyService.createGroupBuy(groupBuyBean);

				Integer idHash = groupBuyBean.getId().toString().hashCode();
				Integer groupNameHash = groupBuyBean.getGroupName().toString().hashCode();
				Integer combainHash = (idHash.toString() + groupNameHash.toString()).hashCode();
				MessageDigest md;
				try {
					md = MessageDigest.getInstance("MD5");
					md.update(combainHash.toString().getBytes());
					byte[] digest = md.digest();
					String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
					groupBuyBean.setGroupAlias(myHash);
					response.sendRedirect(response.encodeRedirectURL("groupBuying.jsp?group=" + myHash));
					return;
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
			} else if (action.equals("join")) {
				Enumeration<String> enumeration = request.getParameterNames();
				// 判斷是否有資料少填，回覆錯誤
				while (enumeration.hasMoreElements()) {
					String string = (String) enumeration.nextElement();
					if (request.getParameter(string) == null) {
						response.sendError(HttpServletResponse.SC_NOT_FOUND);
						return;
					}
				}

				GroupBuyBean groupBuyBean = new GroupBuyBean();
				// 依照group alias 取出 團購bean
				GroupBuyBean groupBuyBeanInit = groupBuyService.queryGroupBuyByAlias(group);
				Set<GroupBuyBean> groupBuyBeans = groupBuyBeanInit.getJoiner();

				// avoid Initiator
				if (groupBuyBeanInit.getMemberBean().equals(mb)) {
					response.sendRedirect(
							response.encodeRedirectURL("shopMaterial.html?category=all&page=1&group=" + group));
					return;
				}

				// 檢查會員(Joiner)是否已經參加此團購
				if (groupBuyBeans.size() > 0) {
					for (Iterator iterator = groupBuyBeans.iterator(); iterator.hasNext();) {
						GroupBuyBean joiners = (GroupBuyBean) iterator.next();
						if (joiners.getMemberBean().equals(mb)) {
							response.sendRedirect(
									response.encodeRedirectURL("shopMaterial.html?category=all&page=1&group=" + group));
							return;
						} else {
							groupBuyBean.setInitiator(groupBuyBeanInit);
							groupBuyBean.setRole(1);
							groupBuyBean.setMemberBean(mb);
							groupBuyService.createGroupBuy(groupBuyBean);
							response.sendRedirect(
									response.encodeRedirectURL("shopMaterial.html?category=all&page=1&group=" + group));
							return;
						}
					}
				} else {
					groupBuyBean.setInitiator(groupBuyBeanInit);
					groupBuyBean.setRole(1);
					groupBuyBean.setMemberBean(mb);
					groupBuyService.createGroupBuy(groupBuyBean);
					response.sendRedirect(
							response.encodeRedirectURL("shopMaterial.html?category=all&page=1&group=" + group));
					return;
				}

			} else if (action.equals("query")) {
				Enumeration<String> enumeration = request.getParameterNames();
				// 判斷是否有資料少填，回覆錯誤
				while (enumeration.hasMoreElements()) {
					String string = (String) enumeration.nextElement();
					if (request.getParameter(string) == null) {
						response.sendError(HttpServletResponse.SC_NOT_FOUND);
						return;
					}
				}
				GroupBuyBean groupBuyBeanInit = groupBuyService.queryGroupBuyByAlias(group);
				if (groupBuyBeanInit == null) {
					response.setContentType("application/json");
					response.getWriter().println("{\"valid\":\"false\"}");
					response.setStatus(200);
					response.getWriter().close();
					return;
				} else {
					response.setContentType("application/json");
					ToJson<GroupBuyBean> toJson = new ToJson<GroupBuyBean>();
//					response.getWriter().println("{\"valid\":\"true\"}");
					response.getWriter().println(toJson.getJson(groupBuyBeanInit));
					response.setStatus(200);
					response.getWriter().close();
					return;
				}
			}

		}

	}

}
