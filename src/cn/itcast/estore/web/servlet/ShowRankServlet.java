package cn.itcast.estore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Orderitem;
import cn.itcast.estore.service.RankService;

/**
 * 查看榜单
 * 
 * @author seawind
 * 
 */
public class ShowRankServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 直接调用 业务层 获得榜单数据
		RankService rankservice = new RankService();
		List<Orderitem> rank = rankservice.showRank();

		// 需要将榜单数据 传递jsp 显示
		// 如果 将榜单数据 保存request对象，导出榜单时 需要重新再查询一次 --- 考虑session、application
		getServletContext().setAttribute("rank", rank);
		request.getRequestDispatcher("/rank.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
