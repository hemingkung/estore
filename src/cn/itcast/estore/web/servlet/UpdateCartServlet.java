package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Product;

/**
 * �޸Ĺ��ﳵ��Ʒ����
 * 
 * @author seawind
 * 
 */
public class UpdateCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �������
		String id = request.getParameter("id");// ��Ʒ���
		int number = Integer.parseInt(request.getParameter("number"));// �޸ĺ�����
		// ������Ʒ
		Product product = new Product();
		product.setId(id);// ����id��product �ͺ�map��productһ����

		// ��ù��ﳵ
		Map<Product, Integer> cart = (Map<Product, Integer>) request
				.getSession().getAttribute("cart");

		if (number <= 0) {
			// ɾ������Ʒ
			cart.remove(product);
		} else {
			cart.put(product, number);
		}

		request.getSession().setAttribute("cart", cart);// ��䲻дҲ��

		// ���ع��ﳵ
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
