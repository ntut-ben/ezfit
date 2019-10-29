package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginServiceImpl;
import shopping.model.CartItem;
import shopping.model.OrderItemBean;
import shopping.repository.OrderItemService;
import shopping.repository.impl.OrderItemServiceImpl;
import shopping.service.ProductService;
import shopping.service.impl.CartItemServiceImpl;
import shopping.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class CheckShopCart
 */
@WebServlet("/CheckShopCart.do")
public class CheckShopCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);

		CartItemServiceImpl cartItemServiceImpl = new CartItemServiceImpl();
		LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
		OrderItemService orderService = new OrderItemServiceImpl();
		ProductService productService = new ProductServiceImpl();
		String account = null, pwd = null, json = null;
		ToJson<CartItem> toJson = new ToJson<CartItem>();
		List<CartItem> cartItems = null;
		CartItem cartItem = null;
		OrderItemBean orderItemBean = null;
		HttpSession session = request.getSession(false);
		String cartItemId = request.getParameter("cartItemId");
		String orderItemId = request.getParameter("orderItemId");

		// Session 取出Member Bean物件
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();

		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginServiceImpl.checkIDPassword(account, pwd);
		}

		if (mb != null) {
			if (cartItemId != null || orderItemId != null) {
				if (cartItemId != null && !cartItemId.equals("false")) {
					cartItem = cartItemServiceImpl.checkItem(Integer.parseInt(cartItemId), mb);
					if (cartItem != null) {
						if (cartItem.getProduct().getClass().getName().equals("shopping.model.PlaneProduct")) {
							json = toJson.getJson(cartItem);
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(json);
							System.out.println(json);
							out.close();
							return;
						}
					}
				} else if (orderItemId != null && !orderItemId.equals("false")) {
					orderItemBean = orderService.query(Integer.parseInt(orderItemId));
					if (orderItemBean != null) {
						if (orderItemBean.getProduct().getClass().getName().equals("shopping.model.PlaneProduct")) {
							json = new ToJson<OrderItemBean>().getJson(orderItemBean);
							response.setContentType("application/json");
							PrintWriter out = response.getWriter();
							out.print(json);
							System.out.println(json);
							out.close();
							return;
						}
					}
				}

			}
			// 取出該mb的購物車
			cartItems = cartItemServiceImpl.checkAllItems(mb);
			if (cartItems.size() > 0) {
				json = toJson.getArrayJson(cartItems);
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();

			}
		}

	}

}
