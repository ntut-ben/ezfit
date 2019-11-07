package shopping.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import _00.utils.ToJson;
import createAccount.model.MemberBean;
import login.service.LoginService;
import shopping.model.CartItem;
import shopping.model.CuisineProduct;
import shopping.model.GroupBuyBean;
import shopping.model.IngredientProduct;
import shopping.model.OrderBean;
import shopping.model.OrderItemBean;
import shopping.model.OrderPlaneItem;
import shopping.model.PlaneItem;
import shopping.model.PlaneProduct;
import shopping.model.Product;
import shopping.repository.OrderItemService;
import shopping.service.CartItemService;
import shopping.service.CuisineProductService;
import shopping.service.GroupBuyService;
import shopping.service.IngredientProductService;
import shopping.service.OrderService;
import shopping.service.PlaneProductService;
import shopping.service.ProductService;

@Controller
public class Mall {
	private final Integer mealboxCount = 1;
	private Integer totalAmount;

	@Autowired
	IngredientProductService ingredientProductService;
	@Autowired
	LoginService loginService;
	@Autowired
	ProductService productService;
	@Autowired
	CartItemService cartItemService;
	@Autowired
	PlaneProductService planeProductService;
	@Autowired
	CuisineProductService cuisineProductService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	GroupBuyService groupBuyService;
	@Autowired
	ServletContext context;
	String account, pwd;
	OrderBean orderBean = null;
	Product product = null;
	GroupBuyBean groupBuyBean = null;
	CartItem cartItem = null;
	OrderItemBean orderItemBean = null;
	CuisineProduct cuisineProduct = null;
	List<CartItem> cartItems = null;
	List<CuisineProduct> cuisineProducts = null;
	List<OrderItemBean> orderItemBeans = null;
	List<OrderBean> orderBeans = null;
	List<GroupBuyBean> groupBeans = null;

	// 食材商城頁面
	@RequestMapping(value = "/shopMaterial", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String shopMaterial() {

		return "shopMaterial";
	}

	// 購物車頁面
	@RequestMapping(value = "/cartList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String cartList() {

		return "cartList";
	}

	// 飲食計畫餐點頁面
	@RequestMapping(value = "/work-out-plane", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String workoutPlane() {

		return "work-out-plane";
	}

//	訂單明細頁面
	@RequestMapping(value = "/orderDetail", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String orderDetail() {

		return "orderDetail";
	}

//	計畫明細頁面
	@RequestMapping(value = "/planeItemDetail", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String planeItemDetail() {

		return "planeItemDetail";
	}

	// 修改飲食計畫餐點頁面
	@RequestMapping(value = "/modifyMeals", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String modifyMeals() {

		return "modifyMeals";
	}

	// 團購頁面
	@RequestMapping(value = "/groupBuying", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String groupBuying() {

		return "groupBuying";
	}

	// 訂單管理頁面
	@RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String orders() {

		return "orders";
	}

	// 訂單管理頁面
	@RequestMapping(value = "/helloWorld", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String helloWorld() {

		return "helloWorld";
	}

	// 食材商城依分類顯示
	@RequestMapping(value = "api/ingredients/category/{category}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String ingredientsCategory(@PathVariable("category") String category) {
		ToJson<IngredientProduct> toJson = new ToJson<IngredientProduct>();
		List<IngredientProduct> results;
		String json = null;
		category = category.toLowerCase().trim();
		if (category.equals("all")) {
			results = ingredientProductService.getAllIngredientProducts();
			json = toJson.getArrayJson(results);
		} else {
			results = ingredientProductService.getIngredientProductByCategory(category);
			json = toJson.getArrayJson(results);
		}
		return json;
	}

	// 計畫商品取出
	@RequestMapping(value = "api/planeProductRetrieve", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String planeProductRetrieve() {

		List<PlaneProduct> planeProducts = null;
		ToJson<PlaneProduct> toJson = new ToJson<PlaneProduct>();
		planeProducts = planeProductService.getAllPlaneProducts();
		String json = toJson.getArrayJson(planeProducts);

		return json;
	}

	// 餐盒商品取出 (依選擇的計畫)
	@RequestMapping(value = "api/mealBox/{plane}", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String mealboxRetrieveByPlane(@PathVariable("plane") String plane,
			@RequestParam("dateForSelect") String shipDate, @RequestParam("workoutPlane") String planeDays) {

		String json = null;
		ToJson<CuisineProduct> toJson = new ToJson<>();
		cuisineProducts = cuisineProductService.getCuisineProductByCategory(plane);
		json = toJson.getArrayJson(cuisineProducts);
		return json;
	}

	// 餐盒商品取出詳細資訊 (依產品ID)
	@RequestMapping(value = "api/mealBox/{id}/single", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String mealboxRetrieveById(@PathVariable("id") Integer id) {
		String json = null;
		ToJson<CuisineProduct> toJson = new ToJson<>();
		CuisineProduct cuisineProduct = cuisineProductService.getCuisineProductById(id);
		json = toJson.getJson(cuisineProduct);
		return json;
	}

	// 修改計畫內的商品
	@RequestMapping(value = "api/mealBox/modify/{plane}", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String modifyPlane(@PathVariable("plane") String plane) {

		String json = null;
		ToJson<CuisineProduct> toJson = new ToJson<>();
		cuisineProducts = cuisineProductService.getCuisineProductByCategory(plane);
		json = toJson.getArrayJson(cuisineProducts);
		return json;
	}

	// 顯示計畫內的商品 (購物車)
	@RequestMapping(value = "api/planeDetail/cart/{cartItemId}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String planeDetailByCart(@PathVariable("cartItemId") Integer cartItemId,
			HttpServletRequest req) {
		String json = null;
		ToJson<CartItem> toJson = new ToJson<>();
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		cartItem = cartItemService.checkItem(cartItemId, memberBean);
		if (cartItem != null) {
			if (cartItem.getProduct() instanceof PlaneProduct) {
				json = toJson.getJson(cartItem);
				return json;
			}
		}
		return null;
	}

	// 顯示計畫內的商品 (訂單)
	@RequestMapping(value = "api/planeDetail/order/{orderItemId}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String planeDetailByOrder(@PathVariable("orderItemId") Integer orderItemId,
			HttpServletRequest req) {
		orderItemBean = orderItemService.query(orderItemId);
		if (orderItemBean != null) {
			if (orderItemBean.getProduct() instanceof PlaneProduct) {
				String json = new ToJson<OrderItemBean>().getJson(orderItemBean);
				return json;
			}
		}
		return null;
	}

	// 食材商品詳細資訊
	@RequestMapping(value = "api/ingredients/productDetail/{itemId}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String ingredientProductDetail(@PathVariable("itemId") Integer itemId) {

		return new ToJson<IngredientProduct>().getJson(ingredientProductService.getIngredientProductById(itemId));
	}

	// 食材加入購物車 (非團購)
	@RequestMapping(value = "api/shopCart/add", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String ingredientProductCart(@RequestParam(value = "category") String category,
			@RequestParam(value = "itemId") Integer itemId, @RequestParam(value = "quantity") Integer quantity,
			HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		// 如果itemId and productQTY 不為空值

		// 取出商品
		product = productService.getProductById(itemId);
		// 檢查存貨
		Integer checkStock = product.getStock() - quantity;

		cartItem = cartItemService.checkItem(product, memberBean);

		if (cartItem != null) {
			Integer updateQty = cartItem.getQty() + (quantity);
			Integer updateSubtotal = updateQty * product.getPrice();
			cartItem.setQty(updateQty);
			cartItem.setSubTotal(updateSubtotal);
			cartItemService.saveOrUpdate(cartItem);

			Map<String, String> status = new HashMap<String, String>();
			status.put("status", "ok");
			String json = new ToJson<Map<String, String>>().getJson(status);
			return json;

		} else {
			if (checkStock >= 0) {
				Integer subTotal = product.getPrice() * quantity;
				cartItem = new CartItem();
				cartItem.setMemberBean(memberBean);
				cartItem.setProduct(product);
				cartItem.setQty(quantity);
				cartItem.setSubTotal(subTotal);
				cartItemService.saveOrUpdate(cartItem);
				Map<String, String> status = new HashMap<String, String>();
				status.put("status", "ok");
				String json = new ToJson<Map<String, String>>().getJson(status);
				return json;
			} else {
				Map<String, String> status = new HashMap<String, String>();
				status.put("status", "false");
				String json = new ToJson<Map<String, String>>().getJson(status);
				return json;
			}
		}
	}

	// 食材加入購物車 (食譜一鍵打包)
	@RequestMapping(value = "api/shopCart/addPackage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String productPackage(@RequestBody ArrayList<String> productName, HttpServletRequest req) {
		Map<String, String> status = new HashMap<String, String>();
		String json = null;
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		List<Integer> integers = new ArrayList<Integer>();
		Integer id = null;
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		DataSource ds = ctx.getBean(DataSource.class);

		for (Iterator iterator = productName.iterator(); iterator.hasNext();) {
			String name = (String) iterator.next();
			System.out.println(name);
			id = ingredientProductService.getIngredientProductByName(ds, name);
			System.out.println(id);
			if (id != null) {
				integers.add(id);
			}
		}

		if (integers.size() > 0) {
			for (Iterator iterator = integers.iterator(); iterator.hasNext();) {
				id = (Integer) iterator.next();
				// 取出商品
				product = productService.getProductById(id);
				// 檢查存貨
				Integer checkStock = product.getStock() - 1;
				cartItem = cartItemService.checkItem(product, memberBean);
				if (cartItem != null) {
					Integer updateQty = cartItem.getQty() + (1);
					Integer updateSubtotal = updateQty * product.getPrice();
					cartItem.setQty(updateQty);
					cartItem.setSubTotal(updateSubtotal);
					cartItemService.saveOrUpdate(cartItem);
					status.put("status", "ok");
					json = new ToJson<Map<String, String>>().getJson(status);
				} else {
					if (checkStock >= 0) {
						Integer subTotal = product.getPrice() * 1;
						cartItem = new CartItem();
						cartItem.setMemberBean(memberBean);
						cartItem.setProduct(product);
						cartItem.setQty(1);
						cartItem.setSubTotal(subTotal);
						cartItemService.saveOrUpdate(cartItem);
						status.put("status", "ok");
						json = new ToJson<Map<String, String>>().getJson(status);
					} else {
						status.put("status", "false");
						json = new ToJson<Map<String, String>>().getJson(status);
					}
				}
			}
			return json;
		} else {
			status.put("status", "false");
			json = new ToJson<Map<String, String>>().getJson(status);
			return json;
		}
	}

	// 食材搜尋
	@RequestMapping(value = "api/search", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String searchProduct(@RequestParam(value = "search") String search) {
		System.out.println(search);
		String json = null;
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		DataSource ds = ctx.getBean("dataSource", DataSource.class);
		List<IngredientProduct> ingredientProducts = ingredientProductService.getIngredientProductBySearch(ds, search);
		ToJson<IngredientProduct> toJson = new ToJson<IngredientProduct>();
		json = toJson.getArrayJson(ingredientProducts);

		if (ingredientProducts == null) {
			return "null";
		} else if (ingredientProducts.size() == 0) {
			return "null";
		} else {
			return json;
		}

	}

	// 食材加入購物車 (團購)
	@RequestMapping(value = "api/shopCart/groupAdd/{group}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String ingredientProductCartGroup(@RequestParam(value = "category") String category,
			@RequestParam(value = "itemId") Integer itemId, @RequestParam(value = "quantity") Integer quantity,
			@PathVariable(value = "group") String group, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		GroupBuyBean groupBuyBean = groupBuyService.queryGroupBuyByAlias(group);
		if (groupBuyBean.getStatus() != 0) {
			return "(redirect:/shopMaterial)";
		}
		;

		// 取出商品
		product = productService.getProductById(itemId);
		// 檢查存貨
		Integer checkStock = product.getStock() - quantity;

		cartItem = cartItemService.checkItem(product, memberBean, groupBuyBean);

		if (cartItem != null) {
			Integer updateQty = cartItem.getQty() + (quantity);
			Integer updateSubtotal = updateQty * product.getPrice();

			cartItem.setQty(updateQty);
			cartItem.setSubTotal(updateSubtotal);
			cartItemService.saveOrUpdate(cartItem);

			Map<String, String> status = new HashMap<String, String>();
			status.put("status", "ok");
			String json = new ToJson<Map<String, String>>().getJson(status);
			return json;
		} else {
			if (checkStock >= 0) {
				cartItem = new CartItem();
				Integer subTotal = product.getPrice() * quantity;
				cartItem.setMemberBean(memberBean);
				cartItem.setProduct(product);
				cartItem.setQty(quantity);
				cartItem.setSubTotal(subTotal);
				cartItem.setGroupBuyBean(groupBuyBean);
				cartItemService.saveOrUpdate(cartItem);

				Map<String, String> status = new HashMap<String, String>();
				status.put("status", "ok");
				String json = new ToJson<Map<String, String>>().getJson(status);
				return json;
			} else {
				Map<String, String> status = new HashMap<String, String>();
				status.put("status", "false");
				String json = new ToJson<Map<String, String>>().getJson(status);
				return json;
			}
		}
	}

	// 修改購物車內的食材商品數量
	@RequestMapping(value = "api/mealBox/modifyCount", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String modifyCount(@RequestParam("qty") Integer qty, @RequestParam("cartId") Integer cartId,
			HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		// 透過商品ID從購物車修改該品項
		if (qty < 1) {
		} else {
			cartItem = cartItemService.modifyQTY(cartId, memberBean, qty);
			String json = new ToJson<CartItem>().getJson(cartItem);
			return json;
		}
		return null;
	}

//	計畫商品加入購物車
	@RequestMapping(value = "api/shopCart/planeAdd", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String planeProductCart(@RequestBody Map<String, Object> productInfo, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		List<Integer> breakfasts = new ArrayList<Integer>(), lunches = new ArrayList<Integer>(),
				dinners = new ArrayList<Integer>();
		Date shipDate = null;
		Integer productId = null;
		Set<Entry<String, Object>> entries = productInfo.entrySet();
		for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			List<String> list = (List<String>) entry.getValue();
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

			product = productService.getProductById(productId);
			cartItem = cartItemService.checkItem(product, memberBean);
			if (cartItem != null) {
//				cartItemServiceImpl.delete(cartItemEvict.getProduct().getId(), mb);
				List<PlaneItem> planeItems = cartItem.getPlaneItems();
				for (int i = 0; i < planeItems.size(); i++) {
					PlaneItem item = planeItems.get(i);
					PlaneItem newItem = items.get(i);
					item.setCuisineProduct(newItem.getCuisineProduct());
					planeItems.set(i, item);
				}
				Integer subTotal = product.getPrice();
				cartItem.setMemberBean(memberBean);
				cartItem.setProduct(product);
				cartItem.setQty(mealboxCount);
				cartItem.setSubTotal(subTotal);
				cartItem.setPlaneItems(planeItems);
				cartItem.setShipDate(shipDate);
//				cartItemServiceImpl.saveOrUpdate(cartItemEvict);
			} else {
				cartItem = new CartItem();
				Integer subTotal = product.getPrice();
				cartItem.setMemberBean(memberBean);
				cartItem.setProduct(product);
				cartItem.setQty(mealboxCount);
				cartItem.setSubTotal(subTotal);
				cartItem.setPlaneItems(items);
				cartItem.setShipDate(shipDate);
				cartItemService.saveOrUpdate(cartItem);
			}
		}
		return null;

	}

	// 購物車刪除(非團購)
	@RequestMapping(value = "api/shopCart/delete", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String deleteCartItem(@RequestParam(value = "itemId") Integer itemId, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		// 透過購物車ID從購物車移除該品項

		cartItemService.delete(itemId, memberBean);
		Map<String, String> status = new HashMap<String, String>();
		status.put("status", "ok");
		String json = new ToJson<Map<String, String>>().getJson(status);
		return json;
	}

	// 購物車列出商品(個人)
	@RequestMapping(value = "api/CheckShopCart", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String CheckShopCartPersional(HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		cartItems = cartItemService.checkAllItems(memberBean);
		if (cartItems.size() > 0) {
			String json = new ToJson<CartItem>().getArrayJson(cartItems);
			return json;
		} else {
			return null;
		}
	}

	// 購物車列出商品(團購)
	@RequestMapping(value = "api/CheckShopCart/{group}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String CheckShopCartGroup(@PathVariable("group") String group, HttpServletRequest req) {
		String json = null;
		ToJson<CartItem> toJson = new ToJson<CartItem>();
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		groupBuyBean = groupBuyService.queryGroupBuyByAlias(group);
		if (groupBuyBean != null && groupBuyBean.getStatus() == 0) {
			cartItems = cartItemService.checkAllItems(groupBuyBean, memberBean);
			if (cartItems.size() > 0) {
				json = toJson.getArrayJson(cartItems);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("groupBuy", groupBuyBean);
				map.put("cartItems", cartItems);
				String mapJson = new ToJson<Map<String, Object>>().getJson(map);
				return mapJson;
			}
		} else {
			// 取出該會員參與的購物車商品 (不包含團購)
			// 取出該mb的購物車
			cartItems = cartItemService.checkAllItems(memberBean);
			if (cartItems.size() > 0) {
				json = toJson.getArrayJson(cartItems);
				return json;
			}
		}
		return null;
	}

	// 用來redirect
	@RequestMapping(value = "api/shopCart/bill", method = RequestMethod.GET)
	public String billCartItem() {
		return "redirect:/cartList";
	}

	// 購物車結帳商品(個人)
	@RequestMapping(value = "api/shopCart/bill", method = RequestMethod.POST)
	public String billCartItem(@RequestParam("subscriberName") String subscriberName,
			@RequestParam("subscriberPhone") String subscriberPhone,
			@RequestParam("subscriberEmail") String subscriberEmail,
			@RequestParam("subscribercity") String subscriberCity,
			@RequestParam("subscriberdistrict") String subscriberDistrict,
			@RequestParam("subscriberzipCode") String subscriberZipCode,
			@RequestParam("subscriberAddress") String subscriberAddress, @RequestParam("name") String shippingName,
			@RequestParam("phone") String shippingPhone, @RequestParam("county") String shippingCity,
			@RequestParam("district") String shippingDistrict, @RequestParam("zipcode") String shippingZipCode,
			@RequestParam("address") String shippingAddress, HttpServletRequest req, Model model) {
		totalAmount = 0;
		Map<String, String> errorMsg = new HashMap<String, String>();
		model.addAttribute("MsgMap", errorMsg);

		if (subscriberName.trim() == "") {
			errorMsg.put("errorSubName", "請輸入購買人姓名");
		}

		if (subscriberPhone.trim() == "") {
			errorMsg.put("errorSubPhone", "請輸入購買人手機號碼");
		} else if (!(subscriberPhone.length() == 10) || !StringUtils.isNumeric(subscriberPhone)) {
			errorMsg.put("errorSubPhone", "手機號碼為10位數字");
		}

		if (subscriberEmail.trim() == "") {
			errorMsg.put("errorSubEmail", "請輸入購買人信箱");
		}

		if (subscriberAddress.trim() == "") {
			errorMsg.put("errorSubAddress", "請輸入購買人地址");
		}

		if (shippingName.trim() == "") {
			errorMsg.put("errorShipName", "請輸入收件人姓名");
		}

		if (shippingPhone.trim() == "") {
			errorMsg.put("errorShipPhone", "請輸入收件人電話");
		} else if (!(shippingPhone.length() == 10) || !StringUtils.isNumeric(shippingPhone)) {
			errorMsg.put("errorShipPhone", "手機號碼為10位數字");
		}

		if (shippingAddress.trim() == "") {
			errorMsg.put("errorShipAddress", "請輸入收件人地址");
		}

		if (errorMsg.size() > 0) {
			return "/cartList";
		} else {

			HttpSession session = req.getSession(false);
			MemberBean memberBean = checkMemberBean(session);
			orderBean = new OrderBean();
			orderItemBeans = new ArrayList<OrderItemBean>();
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
			orderBean.setGroupBuyBean(null);
			if (memberBean != null) {
				cartItems = cartItemService.checkAllItems(memberBean);
				for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
					OrderItemBean orderItemBean = new OrderItemBean();
					cartItem = (CartItem) iterator.next();
					if (cartItem.getPlaneItems() != null && cartItem.getPlaneItems().size() != 0) {
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
					cartItemService.remove(cartItem.getProduct().getId(), memberBean);
				}
				orderBean.setTotalAmount(totalAmount);
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				orderBean.setOrderItemBeans(orderItemBeans);
				orderBean.setCreateTime(ts);
				orderBean.setMemberBean(memberBean);
				orderService.save(orderBean);
//			int count = cartItemServiceImpl.delete(mb);

				return "redirect:/orderDetail";
			}
		}
		return null;
	}

	// 購物車結帳商品(團購)
	@RequestMapping(value = "api/shopCart/groupBill", method = RequestMethod.POST)
	public String groupBillCartItem(@RequestParam("subscriberName") String subscriberName,
			@RequestParam("subscriberPhone") String subscriberPhone,
			@RequestParam("subscriberEmail") String subscriberEmail,
			@RequestParam("subscribercity") String subscriberCity,
			@RequestParam("subscriberdistrict") String subscriberDistrict,
			@RequestParam("subscriberzipCode") String subscriberZipCode,
			@RequestParam("subscriberAddress") String subscriberAddress, @RequestParam("name") String shippingName,
			@RequestParam("phone") String shippingPhone, @RequestParam("county") String shippingCity,
			@RequestParam("district") String shippingDistrict, @RequestParam("zipcode") String shippingZipCode,
			@RequestParam("address") String shippingAddress, @RequestParam("group") String group,
			HttpServletRequest req) {
		orderBean = new OrderBean();
		orderItemBeans = new ArrayList<OrderItemBean>();
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		GroupBuyBean groupBuyBean = groupBuyService.queryGroupBuyByAlias(group);
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

		if (memberBean != null && groupBuyBean != null) {
			cartItems = cartItemService.checkAllItems(groupBuyBean, memberBean);
			for (Iterator iterator = cartItems.iterator(); iterator.hasNext();) {
				OrderItemBean orderItemBean = new OrderItemBean();

				cartItem = (CartItem) iterator.next();

				orderItemBean.setQty(cartItem.getQty());
				orderItemBean.setSubTotal(cartItem.getSubTotal());
				orderItemBean.setProduct(cartItem.getProduct());
				orderItemBeans.add(orderItemBean);
				totalAmount += cartItem.getSubTotal();
				cartItemService.delete(cartItem.getProduct().getId(), memberBean, groupBuyBean);
			}
			orderBean.setTotalAmount(totalAmount);
			Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
			orderBean.setOrderItemBeans(orderItemBeans);
			orderBean.setCreateTime(ts);
			orderBean.setMemberBean(memberBean);
			orderBean.setGroupBuyBean(groupBuyBean);
			orderService.save(orderBean);

			return ("redirect:/orderDetail.html?group=" + group);
		}
		return null;
	}

	// 訂單明細(個人)
	@RequestMapping(value = "api/orderDetail", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String orderDetail(HttpServletRequest req) {

		ToJson<OrderBean> toJson = new ToJson<OrderBean>();
		String json = null;

		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		orderBean = orderService.query(memberBean);
		json = toJson.getJson(orderBean);
		return json;
	}

	// 訂單明細(根據訂單Id)
	@RequestMapping(value = "api/orders/query/order/{orderId}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String perOrderDetail(@PathVariable("orderId") Integer orderId, HttpServletRequest req) {

		ToJson<OrderBean> toJson = new ToJson<OrderBean>();
		String json = null;

		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		orderBean = orderService.query(orderId, memberBean);
		json = toJson.getJson(orderBean);
		return json;
	}

	// 訂單明細(根據團購Id)
	@RequestMapping(value = "api/orders/query/order/groupBuy/{groupId}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String perOrderDetailByGroup(@PathVariable("groupId") Integer groupId,
			HttpServletRequest req) {

		ToJson<OrderBean> toJson = new ToJson<OrderBean>();
		String json = null;
		System.out.println(groupId);
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		groupBuyBean = groupBuyService.queryGroupBuyById(groupId);

		orderBean = orderService.query(groupBuyBean, memberBean);
		if (orderBean != null) {
			json = toJson.getJson(orderBean);
			return json;
		} else {
//			Map<String, String> status = new HashMap<String, String>();
//			status.put("status", "false");
//			json = new ToJson<Map<String, String>>().getJson(status);
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", null);
			json = new ToJson<Map<String, String>>().getJson(map);
			return json;
		}

	}

	// 創立團購
	@RequestMapping(value = "api/GroupBuy/create", method = RequestMethod.POST)
	public String groupBuyCreate(@RequestParam("groupName") String groupName,
			@RequestParam("initiatorName") String initiatorName, @RequestParam("initiatorPhone") String initiatorPhone,
			@RequestParam("county") String country, @RequestParam("district") String district,
			@RequestParam("zipcode") String zipcode, @RequestParam("address") String address,
			@RequestParam("deadLine") String deadLine, HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);

		Map<String, String> errorMsg = new HashMap<String, String>();
		model.addAttribute("MsgErr", errorMsg);
		if (initiatorName == null || initiatorName.trim().equals("")) {
			errorMsg.put("errorName", "請輸入主揪姓名");
		}

		if (initiatorPhone == null || initiatorPhone.trim().equals("")) {
			errorMsg.put("errorPhone", "請輸入聯絡電話");
		} else if (!(initiatorPhone.length() == 10) || !StringUtils.isNumeric(initiatorPhone)) {
			errorMsg.put("errorPhone", "手機號碼為10位數字");
		}

		if (address == null || address.trim().equals("")) {
			errorMsg.put("errorAddress", "請輸入收件地址");
		}

		if (groupName == null || groupName.trim().equals("")) {
			errorMsg.put("errorGroupName", "請輸入團購名稱");
		}

		if (errorMsg.size() != 0) {
			return "groupBuying";
		}

		Date deadLineSQL = null;
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
		groupBuyBean.setMemberBean(memberBean);
		groupBuyBean.setRole(0);
		Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		groupBuyBean.setCreateTime(ts);
		String myHash = groupBuyService.createGroupBuy(groupBuyBean);

		return ("redirect:/groupBuying?group=" + myHash);
	}

	// 加入團購
	@RequestMapping(value = "api/GroupBuy/join/{group}", method = RequestMethod.GET)
	public String groupBuyJoin(@PathVariable("group") String group, HttpServletRequest req) {

		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		GroupBuyBean groupBuyBean = new GroupBuyBean();
		// 依照group alias 取出 團購bean
		System.out.println(group);
		GroupBuyBean groupBuyBeanInit = groupBuyService.queryGroupBuyByAlias(group);
		System.out.println(groupBuyBeanInit.getInitiatorName());
		Set<GroupBuyBean> groupBuyBeans = groupBuyBeanInit.getJoiner();

		if (groupBuyBeanInit.getStatus() != 0) {
			return ("redirect:/shopMaterial");
		}

		// avoid Initiator
		if (groupBuyBeanInit.getMemberBean().equals(memberBean)) {
			return ("redirect:/shopMaterial?group=" + group);
		}

		// 檢查會員(Joiner)是否已經參加此團購
		if (groupBuyBeans.size() > 0) {
			for (Iterator iterator = groupBuyBeans.iterator(); iterator.hasNext();) {
				GroupBuyBean joiners = (GroupBuyBean) iterator.next();
				if (joiners.getMemberBean().equals(memberBean)) {
					return ("redirect:/shopMaterial?group=" + group);
				} else {
					groupBuyBean.setInitiator(groupBuyBeanInit);
					groupBuyBean.setRole(1);
					groupBuyBean.setMemberBean(memberBean);
					groupBuyService.saveOrUpdate(groupBuyBean);
					return ("redirect:/shopMaterial?group=" + group);
				}
			}
		} else {
			groupBuyBean.setInitiator(groupBuyBeanInit);
			groupBuyBean.setRole(1);
			groupBuyBean.setMemberBean(memberBean);
			groupBuyService.saveOrUpdate(groupBuyBean);
			return ("redirect:/shopMaterial?group=" + group);
		}

		return null;
	}

	// 查詢團購是否存在
	@RequestMapping(value = "api/GroupBuy/query/{group}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody String groupBuyQuery(@PathVariable("group") String group, HttpServletRequest req) {

		GroupBuyBean groupBuyBeanInit = groupBuyService.queryGroupBuyByAlias(group);
		if (groupBuyBeanInit == null || groupBuyBeanInit.getStatus() != 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("valid", "false");
			Gson gson = new Gson();
			return gson.toJson(map);
		} else {
			ToJson<GroupBuyBean> toJson = new ToJson<GroupBuyBean>();
			return toJson.getJson(groupBuyBeanInit);
		}
	}

	// 查詢該會員所有已結帳/收單的訂單紀錄
	@RequestMapping(value = "api/orders/query/order", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody String orderQuery(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		orderBeans = orderService.queryOrderBeans(memberBean);
		if (orderBeans != null) {
			return new ToJson<OrderBean>().getArrayJson(orderBeans);
		} else {
			return null;
		}
	}

	// 團購收單
	@RequestMapping(value = "api/GroupBuy/checkout/{groupId}", method = RequestMethod.GET)
	public @ResponseBody String groupBuyCheckout(@PathVariable("groupId") Integer groupId, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		groupBuyService.checkoutGroupBuy(groupId, memberBean);

		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "ok");
		return new ToJson<Map<String, String>>().getJson(map);
	}

	// 該會員所有團購紀錄
	@RequestMapping(value = "api/GroupBuy/query", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public @ResponseBody String groupBuyQuery(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		MemberBean memberBean = checkMemberBean(session);
		groupBeans = groupBuyService.queryGroupBuyByMember(memberBean);

		if (groupBeans == null) {
			return null;
		} else {
			ToJson<GroupBuyBean> toJson = new ToJson<GroupBuyBean>();
			String json = toJson.getArrayJson(groupBeans);
			System.out.println(groupBeans.size());
			return json;
		}
	}

	public MemberBean checkMemberBean(HttpSession session) {
		MemberBean mb = null;
		// Session 取出Member Bean物件
		mb = (MemberBean) session.getAttribute("LoginOK");
		account = mb.getEmail();
		pwd = mb.getPassword();
		// 確認該Member Object帳密都正確
		if ((account != null && pwd != null)) {
			mb = loginService.checkIDPassword(account, pwd);
		}
		return mb;
	}
}
