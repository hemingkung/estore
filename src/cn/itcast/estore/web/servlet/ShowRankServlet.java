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
 * �鿴��
 * 
 * @author seawind
 * 
 */
public class ShowRankServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ֱ�ӵ��� ҵ��� ��ð�����
		RankService rankservice = new RankService();
		List<Orderitem> rank = rankservice.showRank();

		// ��Ҫ�������� ����jsp ��ʾ
		// ��� �������� ����request���󣬵�����ʱ ��Ҫ�����ٲ�ѯһ�� --- ����session��application
		getServletContext().setAttribute("rank", rank);
		request.getRequestDispatcher("/rank.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
