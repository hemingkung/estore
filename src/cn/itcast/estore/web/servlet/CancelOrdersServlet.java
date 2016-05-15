package cn.itcast.estore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.OrderService;

/**
 * 取消订单
 * 
 * @author seawind
 * 
 */
public class CancelOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得要取消订单编号
		String id = request.getParameter("id");
		// 获得当前登陆用户信息
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 传递 取消订单编号和 登陆用户信息 同时给业务层
		OrderService orderService = new OrderService();
		orderService.cancelOrders(id, loginUser);

		// 删除订单后 跳转回 订单列表
		response.sendRedirect("/listOrders");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
