package cn.itcast.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.service.ProductService;

/**
 * 根据 编号 查询商品
 * 
 * @author seawind
 * 
 */
public class ShowProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得编号
		String id = request.getParameter("id");
		// 传递 id 给业务层，查询商品 ---- 优化一定使用缓存
		ProductService productService = new ProductService();
		Product product = productService.showProduct(id);

		// 传递结果对象 给详情页面显示
		request.setAttribute("product", product);
		request.getRequestDispatcher("/detail_product.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
