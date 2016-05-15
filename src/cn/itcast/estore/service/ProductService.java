package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.dao.ProductDAO;
import cn.itcast.estore.domain.Product;

/**
 * ��Ʒҵ���
 * 
 * @author seawind
 * 
 */
public class ProductService {
	// �����Ʒ
	public void addProduct(Product product) {
		// ����DAO �������ݿ�
		ProductDAO dao = new ProductDAO();
		dao.insert(product);
	}

	// ��ѯ������Ʒ
	public List<Product> listProduct() {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.findAll();
	}

	// ����id ��ѯ��Ʒ
	public Product showProduct(String id) {
		ProductDAO productDAO = new ProductDAO();
		return productDAO.findById(id);
	}
}
