package cn.itcast.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.domain.Orderitem;
import cn.itcast.estore.domain.Product;
import cn.itcast.estore.utils.JDBCUtils;

/**
 * 查看榜单需要数据
 * 
 * @author seawind
 * 
 */
public class RankDAO {
	// 查看榜单数据
	public List<Orderitem> findRankData() {
		// 当你进行关联查询， 很可能查询字段 和 类属性 不一样，为了DBCUtils 可以封装，通过别名方式
		String sql = "select product_id , sum(orderitem.buynum) buynum from orders,orderitem where orders.id = orderitem.order_id and orders.state =1 group by orderitem.product_id";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			List<Orderitem> rank = queryRunner.query(sql,
					new BeanListHandler<Orderitem>(Orderitem.class));
			// 查看榜单中需要商品名称、单价等信息 --- 根据 商品id 查询 products表
			for (Orderitem orderitem : rank) {
				String sql2 = "select * from products where id = ?";
				Product product = queryRunner.query(sql2,
						new BeanHandler<Product>(Product.class), orderitem
								.getProduct_id());

				// 将商品 关联 orderitem
				orderitem.setProduct(product);
			}

			return rank;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
