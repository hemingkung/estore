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
 * �鿴������Ʒ��Ϣ
 * 
 * @author seawind
 * 
 */
public class ListProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��Ϊû��������ֱ�ӵ���Service��ɲ�ѯ
		ProductService productService = new ProductService();
		List<Product> products = productService.listProduct();

		// ���ݲ�ѯ��� �� JSP
		request.setAttribute("products", products);
		request.getRequestDispatcher("/list_product.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
