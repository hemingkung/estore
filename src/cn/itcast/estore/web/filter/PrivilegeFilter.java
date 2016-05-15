package cn.itcast.estore.web.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.estore.domain.User;

/**
 * 权限过滤器
 * 
 * @author seawind
 * 
 */
public class PrivilegeFilter implements Filter {

	// 这个list 存放 admin 角色用户 可以访问资源路径
	private List<String> adminPathList = new ArrayList<String>();

	// 存放商城用户 可以访问路径
	private List<String> userPathList = new ArrayList<String>();

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 控制页面是否有权限访问
		// 1 获得用户访问路径
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String visitPath = httpServletRequest.getRequestURI().substring(
				httpServletRequest.getContextPath().length());
		// 2 判断路径是否存在 adminPathList 和 userPathList
		if (adminPathList.contains(visitPath)
				|| userPathList.contains(visitPath)) {
			// 需要登录 一定权限才能访问
			// 3 判断是否登录
			User user = (User) httpServletRequest.getSession().getAttribute(
					"loginUser");
			if (user != null) { // 已经登陆
				// 判断用户权限是否满足
				if (user.getRole().equals("admin")) {// 管理员
					if (adminPathList.contains(visitPath)) {
						// 权限满足
						chain.doFilter(httpServletRequest, response);
					} else {
						throw new RuntimeException("您的权限不足！您当前权限是："
								+ user.getRole());
					}
				} else if (user.getRole().equals("user")) {// 商城用户
					if (userPathList.contains(visitPath)) {
						// 权限满足
						chain.doFilter(httpServletRequest, response);
					} else {
						throw new RuntimeException("您的权限不足！您当前权限是："
								+ user.getRole());
					}
				}

			} else {
				// 未登陆
				request.setAttribute("msg", "您访问的资源必须先登陆！");
				request.getRequestDispatcher("/login.jsp").forward(
						httpServletRequest, response);
			}
		} else {
			// 游客就可以访问
			chain.doFilter(httpServletRequest, response);
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 读取 admin.txt 和 user.txt
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(
					PrivilegeFilter.class.getResource("/admin.txt").getFile()));
			String line1;
			while ((line1 = reader1.readLine()) != null) {
				adminPathList.add(line1);
			}
			reader1.close();

			BufferedReader reader2 = new BufferedReader(new FileReader(
					PrivilegeFilter.class.getResource("/user.txt").getFile()));
			String line2;
			while ((line2 = reader2.readLine()) != null) {
				userPathList.add(line2);
			}
			reader2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
