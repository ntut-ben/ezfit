package shopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginServiceImpl;
import shopping.model.CartItem;
import shopping.model.OrderBean;
import shopping.model.OrderItemBean;
import shopping.model.Product;
import shopping.service.impl.CartItemServiceImpl;
import shopping.service.impl.OrderServiceImpl;
import shopping.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class ShopCart
 */
@WebServlet("/ShopCart.do")
public class ShopCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		HttpSession session = request.getSession(false);
		CartItemServiceImpl cartItemServiceImpl = new CartItemServiceImpl();
		LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		String account = null, pwd = null, itemId = null, productQTY = null, action = null, json = null;
		OrderBean orderBean = new OrderBean();
		Integer totalAmount = 0;
		String subscriberCity = null, subscriberDistrict = null, subscriberAddress = null, subscriberEmail = null,
				subscriberPhone = null, shippingCity = null, shippingDistrict = null, shippingAddress = null,
				shippingPhone = null, subscriberName = null, shippingName = null;

		ToJson<CartItem> toJson = new ToJson<CartItem>();
		CartItem cartItem = new CartItem();
		CartItem cartItemEvict = null;
		MemberBean mb = null;
		Product product = null;
		List<OrderItemBean> orderItemBeans = new ArrayList<OrderItemBean>();
		List<CartItem> cartItems = new ArrayList<CartItem>();
		int qty = 0;
		action = request.getParameter("action");
		if (request.getContentType().equals("application/json;charset=UTF-8")) {

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String iString = bufferedReader.readLine();
			while (iString != null) {
				stringBuffer.append(iString);
				iString = bufferedReader.readLine();
			}
			Gson gson = new Gson();
			Map<String, String[]> breakfasts = gson.fromJson(stringBuffer.toString(), Map.class);
			System.out.println("---------------------------------");
			Set<Entry<String, String[]>> test = breakfasts.entrySet();
			for (Iterator iterator = test.iterator(); iterator.hasNext();) {
				Entry<String, String[]> entry = (Entry<String, String[]>) iterator.next();
				System.out.println(entry.getKey() + ":" + entry.getValue());

			}
		}

		// Session 取出Member Bean物件
		mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();

		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginServiceImpl.checkIDPassword(account, pwd);
		}

		if (action.equals("add")) {
			// 取出目標商品資訊
			itemId = request.getParameter("itemId");
			productQTY = request.getParameter("quantity");

			// 如果itemId and productQTY 不為空值
			if ((itemId != null && productQTY != null)
					|| (!itemId.trim().equals("") && !productQTY.trim().equals(""))) {
				// 取出商品
				product = productServiceImpl.getProductById(Integer.parseInt(itemId));
				// 檢查存貨
				Integer checkStock = product.getStock() - Integer.parseInt(productQTY);

				cartItemEvict = cartItemServiceImpl.checkItem(product, mb);

				if (cartItemEvict != null) {
					Integer updateQty = cartItemEvict.getQty() + (Integer.parseInt(productQTY));
					Integer updateSubtotal = updateQty * product.getPrice();

					cartItemEvict.setQty(updateQty);
					cartItemEvict.setSubTotal(updateSubtotal);
					cartItemServiceImpl.saveOrUpdate(cartItemEvict);

				} else {
					if (checkStock >= 0) {
						Integer subTotal = product.getPrice() * Integer.parseInt(productQTY);
						cartItem.setMemberBean(mb);
						cartItem.setProduct(product);
						cartItem.setQty(Integer.parseInt(productQTY));
						cartItem.setSubTotal(subTotal);
						cartItemServiceImpl.saveOrUpdate(cartItem);
					} else {
						json = "{\"status\":\"false\"}";
						PrintWriter out = response.getWriter();
						out.print(json);
						out.close();
					}
				}
				json = "{\"status\":\"ok\"}";
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
			}
		} else if (action.equals("planeAdd")) {
			// 取出目標商品資訊
			itemId = request.getParameter("itemId");
			productQTY = request.getParameter("quantity");

		} else if (action.equals("delete")) {
			// 透過商品ID從購物車移除該品項
			itemId = request.getParameter("itemId");
			cartItemServiceImpl.delete(Integer.parseInt(itemId), mb);
		} else if (action.equals("modify")) {
			// 透過商品ID從購物車修改該品項
			itemId = request.getParameter("itemId");
			qty = Integer.parseInt(request.getParameter("qty"));
			if (qty < 1) {
			} else {
				cartItem = cartItemServiceImpl.modifyQTY(Integer.parseInt(itemId), mb, qty);
				json = toJson.getJson(cartItem);
				PrintWriter out = response.getWriter();
				out.print(json);
				out.close();
			}

		} else if (action.equals("bill")) {

			subscriberName = request.getParameter("subscriberName");
			shippingName = request.getParameter("name");
			subscriberCity = request.getParameter("subscribercity");
			subscriberDistrict = request.getParameter("subscriberdistrict");
			subscriberAddress = request.getParameter("subscriberAddress");
			subscriberEmail = request.getParameter("subscriberEmail");
			subscriberPhone = request.getParameter("subscriberPhone");
			shippingCity = request.getParameter("county");
			shippingDistrict = request.getParameter("district");
			shippingAddress = request.getParameter("address");
			shippingPhone = request.getParameter("phone");

			orderBean.setShippingAddress(shippingAddress);
			orderBean.setShippingCity(shippingCity);
			orderBean.setShippingDistrict(shippingDistrict);
			orderBean.setShippingName(shippingName);
			orderBean.setShippingPhone(shippingPhone);
			orderBean.setSubscriberAddress(subscriberAddress);
			orderBean.setSubscriberCity(subscriberCity);
			orderBean.setSubscriberDistrict(subscriberDistrict);
			orderBean.setSubscriberEmail(subscriberEmail);
			orderBean.setSubscriberName(subscriberName);
			orderBean.setSubscriberPhone(subscriberPhone);

			if (mb != null) {
				cartItems = cartItemServiceImpl.checkAllItems(mb);
				for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
					cartItem = (CartItem) iterator.next();
					OrderItemBean orderItemBean = new OrderItemBean();
					orderItemBean.setQty(cartItem.getQty());
					orderItemBean.setSubTotal(cartItem.getSubTotal());
					orderItemBean.setProduct(cartItem.getProduct());
					orderItemBeans.add(orderItemBean);
					totalAmount += cartItem.getSubTotal();
				}
				orderBean.setTotalAmount(totalAmount);
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				orderBean.setOrderItemBeans(orderItemBeans);
				orderBean.setCreateTime(ts);
				orderServiceImpl.save(orderBean);
				int count = cartItemServiceImpl.delete(mb);

				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/orderDetail.html"));
			}

		}

	}

}
