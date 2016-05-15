package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Order;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.OrderService;

/**
 * �鿴 ����
 * 
 * @author seawind
 * 
 */
public class ListOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��õ�ǰ��½�û�
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// ���� ��½�û� ��ҵ��� ����ö����б�
		OrderService orderService = new OrderService();

		// �����û���ѯ����
		List<Order> orders = orderService.listOrders(loginUser);

		// ���ݲ�ѯOrders�б� �� ҳ��
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orders.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
