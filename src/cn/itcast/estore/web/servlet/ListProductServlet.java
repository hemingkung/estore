package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.service.ProductService;

/**
 * 查看所有商品信息
 * 
 * @author seawind
 * 
 */
public class ListProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 因为没有条件，直接调用Service完成查询
		ProductService productService = new ProductService();
		List<Product> products = productService.listProduct();

		// 传递查询结果 给 JSP
		request.setAttribute("products", products);
		request.getRequestDispatcher("/list_product.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
