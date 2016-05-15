package cn.itcast.estore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.domain.Product;
import cn.itcast.estore.utils.JDBCUtils;

/**
 * 商品信息 DAO
 * 
 * @author seawind
 * 
 */
public class ProductDAO {
	// 将商品信息插入数据库
	public void insert(Product product) {
		String sql = "insert into products values(?,?,?,?,?,?)";
		Object[] param = { JDBCUtils.generateProductId(), product.getName(),
				product.getPrice(), product.getCategory(),
				product.getDescription(), product.getImg() };
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			queryRunner.update(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 查询所有商品
	public List<Product> findAll() {
		String sql = "select * from products";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			List<Product> products = queryRunner.query(sql,
					new BeanListHandler<Product>(Product.class));
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 根据id查询商品
	public Product findById(String id) {
		String sql = "select * from products where id = ?";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			Product product = queryRunner.query(sql, new BeanHandler<Product>(
					Product.class), id);
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
