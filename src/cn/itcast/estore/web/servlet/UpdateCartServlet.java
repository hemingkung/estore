package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;

/**
 * 修改购物车商品数量
 * 
 * @author seawind
 * 
 */
public class UpdateCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得数据
		String id = request.getParameter("id");// 商品编号
		int number = Integer.parseInt(request.getParameter("number"));// 修改后数量
		// 构造商品
		Product product = new Product();
		product.setId(id);// 有了id的product 就和map中product一样的

		// 获得购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");

		if (number <= 0) {
			// 删除该商品
			cart.remove(product);
		} else {
			cart.put(product, number);
		}

		request.getSession().setAttribute("cart", cart);// 这句不写也行

		// 跳回购物车
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
