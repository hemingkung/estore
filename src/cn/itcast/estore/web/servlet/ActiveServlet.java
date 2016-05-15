package cn.itcast.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.UserService;

/**
 * 激活账户
 * 
 * @author seawind
 * 
 */
public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得激活码
		String activecode = request.getParameter("activecode");

		// 将激活码传递业务层 完成账户激活
		UserService service = new UserService();
		service.active(activecode);

		// 通知用户
		response.getWriter().println("账户激活成功！");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
