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
 * 查看 订单
 * 
 * @author seawind
 * 
 */
public class ListOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得当前登陆用户
		User loginUser = (User) request.getSession().getAttribute("loginUser");

		// 传递 登陆用户 给业务层 ，获得订单列表
		OrderService orderService = new OrderService();

		// 根据用户查询订单
		List<Order> orders = orderService.listOrders(loginUser);

		// 传递查询Orders列表 给 页面
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orders.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
