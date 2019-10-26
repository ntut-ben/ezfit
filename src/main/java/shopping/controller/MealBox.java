package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import _00.utils.ToJson;
import shopping.model.CuisineProduct;
import shopping.model.IngredientProduct;
import shopping.service.impl.CuisineProductServiceImpl;
import shopping.service.impl.IngredientProductServiceImpl;

/**
 * Servlet implementation class ListIngredient
 */
@WebServlet("/MealBox.do")
public class MealBox extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		CuisineProductServiceImpl cuisineProductServiceImpl = new CuisineProductServiceImpl();
		String productId = null, modifyMeal = null, plane = null, shipDate = null, planeDays = null, json = null;
		List<CuisineProduct> cuisineProducts = null;
		ToJson<CuisineProduct> toJson = new ToJson<CuisineProduct>();
		plane = request.getParameter("plane");
		modifyMeal = request.getParameter("modifyMeal");
		shipDate = request.getParameter("dateForSelect");
		planeDays = request.getParameter("workoutPlane");
		productId = request.getParameter("productId");
		String referrer = request.getHeader("referer");
		referrer = referrer.substring(referrer.lastIndexOf("/") + 1, referrer.lastIndexOf(".jsp"));

		if (referrer.equals("modifyMeals") && plane != null && modifyMeal.equals("true")) {
			response.setContentType("application/json");
			cuisineProducts = cuisineProductServiceImpl.getCuisineProductByCategory(plane);
			json = toJson.getArrayJson(cuisineProducts);
			System.out.println(json);
			PrintWriter out = response.getWriter();
			out.print(json);
			return;
		}
		System.out.println(productId);
		if (productId != null) {
			response.setContentType("application/json");
			CuisineProduct cuisineProduct = cuisineProductServiceImpl
					.getCuisineProductById(Integer.parseInt(productId));
			json = toJson.getJson(cuisineProduct);
			PrintWriter out = response.getWriter();
			out.print(json);
			return;
		}

		if (plane != null && shipDate != null && planeDays != null) {
			response.setContentType("application/json");
			cuisineProducts = cuisineProductServiceImpl.getCuisineProductByCategory(plane);
			json = toJson.getArrayJson(cuisineProducts);
			PrintWriter out = response.getWriter();
			out.print(json);
			return;
		}

	}

}
