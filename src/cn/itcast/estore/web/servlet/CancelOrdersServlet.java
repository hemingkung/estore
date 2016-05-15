package cn.itcast.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.OrderService;

/**
 * ȡ������
 * 
 * @author seawind
 * 
 */
public class CancelOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���Ҫȡ���������
		String id = request.getParameter("id");
		// ��õ�ǰ��½�û���Ϣ
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// ���� ȡ��������ź� ��½�û���Ϣ ͬʱ��ҵ���
		OrderService orderService = new OrderService();
		orderService.cancelOrders(id, loginUser);

		// ɾ�������� ��ת�� �����б�
		response.sendRedirect("/listOrders");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
