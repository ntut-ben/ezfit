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
import shopping.model.PlaneProduct;
import shopping.service.PlaneProductService;
import shopping.service.impl.PlaneProductServiceImpl;

/**
 * Servlet implementation class ListIngredient
 */
@WebServlet("/PlaneProductRetrieve.do")
public class PlaneProductRetrieve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final String CHARSET = "UTF8";
		request.setCharacterEncoding(CHARSET);
		response.setCharacterEncoding(CHARSET);
		PlaneProductService planeProductService = new PlaneProductServiceImpl();
		List<PlaneProduct> planeProducts = null;
		ToJson<PlaneProduct> toJson = new ToJson<PlaneProduct>();
		String json = null;

		response.setContentType("application/json");
		planeProducts = planeProductService.getAllPlaneProducts();
		json = toJson.getArrayJson(planeProducts);
		System.out.println(json);
		PrintWriter out = response.getWriter();
		out.print(json);
		return;

	}

}
