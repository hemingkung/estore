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
 * �����Ʒ �����ﳵ
 * 
 * @author seawind
 * 
 */
public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������� �����Ʒ���
		String id = request.getParameter("id");

		// ����ҵ��� �����Ʒ����
		ProductService productService = new ProductService();
		Product product = productService.showProduct(id);

		// ���Session�й��ﳵ
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");
		// ���ﳵ��һ��ʹ��cart ������
		if (cart == null) { // ���ﳵ������ ����
			cart = new HashMap<Product, Integer>();
		}

		// �ж�cart ���ﳵ���Ƿ��Ѿ����ڸ���Ʒ
		if (cart.containsKey(product)) { // ʹ��hashcode �� equals
			// �Ѿ�����
			int number = cart.get(product);
			cart.put(product, number + 1);
		} else {
			// ��Ʒ���ڹ��ﳵ��
			cart.put(product, 1);
		}

		// ����cart �� session
		request.getSession().setAttribute("cart", cart);

		// ������Ʒ �� ���ص���Ʒ�б�ҳ�� ��������
		request.getRequestDispatcher("/listProduct").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
