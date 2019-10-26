package shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _00.utils.ToJson;
import shopping.model.IngredientProduct;
import shopping.service.impl.IngredientProductServiceImpl;

/**
 * Servlet implementation class ListIngredient
 */
@WebServlet("/ListIngredient.do")
public class ListIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		response.setContentType("application/json");
		IngredientProductServiceImpl impl = new IngredientProductServiceImpl();
		ToJson<IngredientProduct> toJson = new ToJson<IngredientProduct>();
		List<IngredientProduct> results;
		String category = request.getParameter("category");
		String itemId = request.getParameter("itemId");

		if (itemId != null) {
			if (!itemId.equalsIgnoreCase("false") && !itemId.equals("0")) {
				IngredientProduct ingredientProduct = new IngredientProduct();
				ingredientProduct = impl.getIngredientProductById(Integer.parseInt(itemId));
				String json = toJson.getJson(ingredientProduct);
				PrintWriter out = response.getWriter();
				System.out.println(json);
				out.print(json);
				return;
			}
		}

		if (category != null) {
			if (!category.equalsIgnoreCase("false") && !category.equals("0")) {
				category = category.toLowerCase().trim();
				if (category.equals("all")) {
					results = impl.getAllIngredientProducts();
					String json = toJson.getArrayJson(results);
					PrintWriter out = response.getWriter();
					out.print(json);
					out.close();
				} else {

					results = impl.getIngredientProductByCategory(category);
					String json = toJson.getArrayJson(results);
					PrintWriter out = response.getWriter();

					out.print(json);
					out.close();
				}
			} else {
				response.setStatus(404);
				PrintWriter out = response.getWriter();
				out.print("404 page not found");
				out.close();
			}
		}

	}

}
