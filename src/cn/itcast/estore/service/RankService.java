package cn.itcast.estore.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.itcast.estore.dao.RankDAO;
import cn.itcast.estore.domain.Orderitem;

/**
 * 榜单 业务类
 * 
 * @author seawind
 * 
 */
public class RankService {
	// 查看榜单
	public List<Orderitem> showRank() {
		RankDAO rankDAO = new RankDAO();
		List<Orderitem> rank = rankDAO.findRankData();
		// 查看数据 是一个无序的 --- 榜单应该按照销售数量降序
		Collections.sort(rank, new RankComparator());// sort --- Ordetitem
		// 必须实现Compareable
		// 接口，或者需要提供第三方比较器
		return rank;
	}

	class RankComparator implements Comparator<Orderitem> {

		@Override
		public int compare(Orderitem o1, Orderitem o2) {
			// 升序 前 - 后， 降序 后 -前
			return o2.getBuynum() - o1.getBuynum();
		}

	}
}
