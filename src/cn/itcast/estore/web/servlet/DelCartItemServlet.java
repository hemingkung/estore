package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;

/**
 * 删除购物车中 一项 数据
 * 
 * @author seawind
 * 
 */
public class DelCartItemServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得删除 商品编号
		String id = request.getParameter("id");

		// 构造商品对象
		Product product = new Product();
		product.setId(id);// 含有 id 的商品对象

		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");
		cart.remove(product); // 重写hashcode equals

		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
