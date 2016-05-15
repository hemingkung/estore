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
 * 用户登录
 * 
 * @author seawind
 * 
 */
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得form 数据 封装 User对象
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		// 传递 user对象给业务层完成登录
		UserService service = new UserService();
		User loginUser = service.login(user);

		// 判断loginUser是否存在
		if (loginUser == null) {
			// 登录失败
			request.setAttribute("msg", "用户名或者密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			// 登录成功 --- 将用户信息保存session
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
