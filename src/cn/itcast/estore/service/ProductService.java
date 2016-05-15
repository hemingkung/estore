package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.dao.ProductDAO;
import cn.itcast.estore.domain.Product;

/**
 * 商品业务层
 * 
 * @author seawind
 * 
 */
public class ProductService {
	// 添加商品
	public void addProduct(Product product) {
		// 传递DAO 保存数据库
		ProductDAO dao = new ProductDAO();
		dao.insert(product);
	}

	// 查询所有商品
	public List<Product> listProduct() {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.findAll();
	}

	// 根据id 查询商品
	public Product showProduct(String id) {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.findById(id);
	}
}
