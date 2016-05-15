package cn.itcast.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.UserService;

/**
 * �����˻�
 * 
 * @author seawind
 * 
 */
public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ü�����
		String activecode = request.getParameter("activecode");

		// �������봫��ҵ��� ����˻�����
		UserService service = new UserService();
		service.active(activecode);

		// ֪ͨ�û�
		response.getWriter().println("�˻�����ɹ���");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
