package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.service.ProductService;

/**
 * 添加商品 到购物车
 * 
 * @author seawind
 * 
 */
public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从请求中 获得商品编号
		String id = request.getParameter("id");

		// 调用业务层 获得商品对象
		ProductService productService = new ProductService();
		Product product = productService.showProduct(id);

		// 获得Session中购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");
		// 购物车第一次使用cart 不存在
		if (cart == null) { // 购物车不存在 创建
			cart = new HashMap<Product, Integer>();
		}

		// 判断cart 购物车中是否已经存在该商品
		if (cart.containsKey(product)) { // 使用hashcode 和 equals
			// 已经存在
			int number = cart.get(product);
			cart.put(product, number + 1);
		} else {
			// 商品不在购物车中
			cart.put(product, 1);
		}

		// 保存cart 到 session
		request.getSession().setAttribute("cart", cart);

		// 买完商品 后 ，回到商品列表页面 继续购买
		request.getRequestDispatcher("/listProduct").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
