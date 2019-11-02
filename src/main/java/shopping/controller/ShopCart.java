package shopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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
import login.service.LoginService;
import login.service.LoginServiceImpl;
import shopping.model.CartItem;
import shopping.model.CuisineProduct;
import shopping.model.GroupBuyBean;
import shopping.model.OrderBean;
import shopping.model.OrderItemBean;
import shopping.model.OrderPlaneItem;
import shopping.model.PlaneItem;
import shopping.model.Product;
import shopping.service.CuisineProductService;
import shopping.service.GroupBuyService;
import shopping.service.impl.CartItemServiceImpl;
import shopping.service.impl.CuisineProductServiceImpl;
import shopping.service.impl.GroupBuyServiceImpl;
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
		final Integer mealboxCount = 1;
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		HttpSession session = request.getSession(false);

		CartItemServiceImpl cartItemServiceImpl = new CartItemServiceImpl();
		LoginService loginServiceImpl = new LoginServiceImpl();
		GroupBuyService groupBuyService = new GroupBuyServiceImpl();
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		CuisineProductService cuisineProductService = new CuisineProductServiceImpl();
		String account = null, pwd = null, itemId = null, productQTY = null, action = null, json = null;
		OrderBean orderBean = new OrderBean();
		Date shipDate = null;
		Integer totalAmount = 0;
		String subscriberCity = null, subscriberDistrict = null, subscriberAddress = null, subscriberEmail = null,
				subscriberPhone = null, shippingCity = null, shippingDistrict = null, shippingAddress = null,
				shippingPhone = null, subscriberName = null, shippingName = null, subscriberZipCode = null,
				shippingZipCode = null, group = null;
		ToJson<CartItem> toJson = new ToJson<CartItem>();
		CartItem cartItem = new CartItem();
		CartItem cartItemEvict = null;
		MemberBean mb = null;
		Product product = null;
		CuisineProduct cuisineProduct = null;
		Integer productId = null;
		List<OrderItemBean> orderItemBeans = new ArrayList<OrderItemBean>();
		List<CartItem> cartItems = new ArrayList<CartItem>();
		List<Integer> breakfasts = new ArrayList<Integer>(), lunches = new ArrayList<Integer>(),
				dinners = new ArrayList<Integer>();
		int qty = 0;
		action = request.getParameter("action");

		// 從work-to-plane 來的結帳資訊handler
		if (request.getContentType().equals("application/json;charset=UTF-8")) {
			// 從Request inputstream 讀取 fornt-end json data
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(request.getInputStream(), "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String iString;
			while ((iString = bufferedReader.readLine()) != null) {
				stringBuffer.append(iString);
				iString = bufferedReader.readLine();
			}
			// Gson pares Json Data
			Gson gson = new Gson();
			Map<String, String[]> mealbox = gson.fromJson(stringBuffer.toString(), Map.class);
			Set<Entry<String, String[]>> mealboxDetail = mealbox.entrySet();

			// 依據Key 取出相對應的value
			for (Iterator iterator = mealboxDetail.iterator(); iterator.hasNext();) {
				Entry<String, ArrayList<String>> entry = (Entry<String, ArrayList<String>>) iterator.next();
				List<String> list = entry.getValue();
				if (entry.getKey().equals("breakfasts")) {
					for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
						Double mealId = (Double) iterator2.next();
						breakfasts.add(mealId.intValue());
					}
				} else if (entry.getKey().equals("lunches")) {
					for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
						Double mealId = (Double) iterator2.next();
						lunches.add(mealId.intValue());
					}
				} else if (entry.getKey().equals("dinners")) {
					for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
						Double mealId = (Double) iterator2.next();
						dinners.add(mealId.intValue());
					}
				} else if (entry.getKey().equals("action")) {
					for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
						action = (String) iterator2.next();
					}
				} else if (entry.getKey().equals("ship")) {
					for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
						String ship = (String) iterator2.next();
						Calendar cal = Calendar.getInstance();
						String token[] = ship.split("-");
						int year = Integer.parseInt(token[0]);
						int month = Integer.parseInt(token[1]);
						int date = Integer.parseInt(token[2]);
						cal.set(year, (month - 1), date);
						shipDate = new Date(cal.getTimeInMillis());
					}
				} else if (entry.getKey().equals("productId")) {
					for (Iterator iterator2 = list.iterator(); iterator2.hasNext();) {
						productId = ((Double) iterator2.next()).intValue();
					}
				}
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
			ArrayList<PlaneItem> items = new ArrayList<PlaneItem>();
			if (breakfasts != null) {
				for (Iterator iterator = breakfasts.iterator(); iterator.hasNext();) {
					Integer integer = (Integer) iterator.next();
					cuisineProduct = cuisineProductService.getCuisineProductById(integer);
					PlaneItem item = new PlaneItem();
					item.setCuisineProduct(cuisineProduct);
					items.add(item);
				}
			}
			if (lunches != null) {
				for (Iterator iterator = lunches.iterator(); iterator.hasNext();) {
					Integer integer = (Integer) iterator.next();
					cuisineProduct = cuisineProductService.getCuisineProductById(integer);
					PlaneItem item = new PlaneItem();
					item.setCuisineProduct(cuisineProduct);
					items.add(item);
				}
			}
			if (dinners != null) {
				for (Iterator iterator = dinners.iterator(); iterator.hasNext();) {
					Integer integer = (Integer) iterator.next();
					cuisineProduct = cuisineProductService.getCuisineProductById(integer);
					PlaneItem item = new PlaneItem();
					item.setCuisineProduct(cuisineProduct);
					items.add(item);
				}

				product = productServiceImpl.getProductById(productId);
				cartItemEvict = cartItemServiceImpl.checkItem(product, mb);
				if (cartItemEvict != null) {
//					cartItemServiceImpl.delete(cartItemEvict.getProduct().getId(), mb);
					List<PlaneItem> planeItems = cartItemEvict.getPlaneItems();
					for (int i = 0; i < planeItems.size(); i++) {
						PlaneItem item = planeItems.get(i);
						PlaneItem newItem = items.get(i);
						item.setCuisineProduct(newItem.getCuisineProduct());
						planeItems.set(i, item);
					}
					Integer subTotal = product.getPrice();
					cartItemEvict.setMemberBean(mb);
					cartItemEvict.setProduct(product);
					cartItemEvict.setQty(mealboxCount);
					cartItemEvict.setSubTotal(subTotal);
					cartItemEvict.setPlaneItems(planeItems);
					cartItemEvict.setShipDate(shipDate);
//					cartItemServiceImpl.saveOrUpdate(cartItemEvict);
				} else {
					Integer subTotal = product.getPrice();
					cartItem.setMemberBean(mb);
					cartItem.setProduct(product);
					cartItem.setQty(mealboxCount);
					cartItem.setSubTotal(subTotal);
					cartItem.setPlaneItems(items);
					cartItem.setShipDate(shipDate);
					cartItemServiceImpl.saveOrUpdate(cartItem);
				}
			}

		} else if (action.equals("groupAdd")) {
			// 取出目標商品資訊
			itemId = request.getParameter("itemId");
			productQTY = request.getParameter("quantity");
			group = request.getParameter("group");
			GroupBuyBean groupBuyBean = groupBuyService.queryGroupBuyByAlias(group);
			// 如果itemId and productQTY 不為空值
			if ((itemId != null && productQTY != null && groupBuyBean != null)
					|| (!itemId.trim().equals("") && !productQTY.trim().equals(""))) {
				// 取出商品
				product = productServiceImpl.getProductById(Integer.parseInt(itemId));
				// 檢查存貨
				Integer checkStock = product.getStock() - Integer.parseInt(productQTY);

				cartItemEvict = cartItemServiceImpl.checkItem(product, mb, groupBuyBean);

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
						cartItem.setGroupBuyBean(groupBuyBean);
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
			shippingZipCode = request.getParameter("zipcode");
			subscriberZipCode = request.getParameter("subscriberzipCode");

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
			orderBean.setSubscriberZipCode(subscriberZipCode);
			orderBean.setShippingZipCode(shippingZipCode);

			if (mb != null) {
				cartItems = cartItemServiceImpl.checkAllItems(mb);
				for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
					OrderItemBean orderItemBean = new OrderItemBean();

					cartItem = (CartItem) iterator.next();
					if (cartItem.getPlaneItems() != null) {
						List<OrderPlaneItem> orderPlaneItems = new ArrayList<OrderPlaneItem>();
						List<PlaneItem> planeItems = cartItem.getPlaneItems();
						for (Iterator iterator2 = planeItems.iterator(); iterator2.hasNext();) {
							PlaneItem planeItem = (PlaneItem) iterator2.next();
							OrderPlaneItem orderPlaneItem = new OrderPlaneItem();
							orderPlaneItem.setCuisineProduct(planeItem.getCuisineProduct());
							orderPlaneItems.add(orderPlaneItem);
						}
						orderItemBean.setShipDate(cartItem.getShipDate());
						orderItemBean.setPlaneItems(orderPlaneItems);
						totalAmount += 90;
					}

					orderItemBean.setQty(cartItem.getQty());
					orderItemBean.setSubTotal(cartItem.getSubTotal());
					orderItemBean.setProduct(cartItem.getProduct());
					orderItemBeans.add(orderItemBean);
					totalAmount += cartItem.getSubTotal();
					cartItemServiceImpl.delete(cartItem.getProduct().getId(), mb);
				}
				orderBean.setTotalAmount(totalAmount);
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				orderBean.setOrderItemBeans(orderItemBeans);
				orderBean.setCreateTime(ts);
				orderBean.setMemberBean(mb);
				orderServiceImpl.save(orderBean);
//				int count = cartItemServiceImpl.delete(mb);

				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/orderDetail.html"));
				return;
			}

		} else if (action.equals("groupBill")) {
			group = request.getParameter("group");

			GroupBuyBean groupBuyBean = groupBuyService.queryGroupBuyByAlias(group);
			System.out.println(group);
			System.out.println(groupBuyBean);
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
			shippingZipCode = request.getParameter("zipcode");
			subscriberZipCode = request.getParameter("subscriberzipCode");

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
			orderBean.setSubscriberZipCode(subscriberZipCode);
			orderBean.setShippingZipCode(shippingZipCode);

			if (mb != null && groupBuyBean != null) {
				cartItems = cartItemServiceImpl.checkAllItems(groupBuyBean, mb);
				for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
					OrderItemBean orderItemBean = new OrderItemBean();

					cartItem = (CartItem) iterator.next();

					orderItemBean.setQty(cartItem.getQty());
					orderItemBean.setSubTotal(cartItem.getSubTotal());
					orderItemBean.setProduct(cartItem.getProduct());
					orderItemBeans.add(orderItemBean);
					totalAmount += cartItem.getSubTotal();
					cartItemServiceImpl.delete(cartItem.getProduct().getId(), mb, groupBuyBean);
				}
				orderBean.setTotalAmount(totalAmount);
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				orderBean.setOrderItemBeans(orderItemBeans);
				orderBean.setCreateTime(ts);
				orderBean.setMemberBean(mb);
				orderBean.setGroupBuyBean(groupBuyBean);
				orderServiceImpl.save(orderBean);

				response.sendRedirect(
						response.encodeRedirectURL(request.getContextPath() + "/orderDetail.html?group=" + group));
				return;
			}

		}

	}

}
