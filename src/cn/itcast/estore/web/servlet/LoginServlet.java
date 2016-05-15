package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.UserService;

/**
 * �û���¼
 * 
 * @author seawind
 * 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���form ���� ��װ User����
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// ���� user�����ҵ�����ɵ�¼
		UserService service = new UserService();
		User loginUser = service.login(user);

		// �ж�loginUser�Ƿ����
		if (loginUser == null) {
			// ��¼ʧ��
			request.setAttribute("msg", "�û��������������");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			// ��¼�ɹ� --- ���û���Ϣ����session
			request.getSession().setAttribute("loginUser", loginUser);
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
