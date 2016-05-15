package cn.itcast.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.service.ProductService;

/**
 * ���� ��� ��ѯ��Ʒ
 * 
 * @author seawind
 * 
 */
public class ShowProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ñ��
		String id = request.getParameter("id");
		// ���� id ��ҵ��㣬��ѯ��Ʒ ---- �Ż�һ��ʹ�û���
		ProductService productService = new ProductService();
		Product product = productService.showProduct(id);

		// ���ݽ������ ������ҳ����ʾ
		request.setAttribute("product", product);
		request.getRequestDispatcher("/detail_product.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
