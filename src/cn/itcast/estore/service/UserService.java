package cn.itcast.estore.service;

import javax.mail.Message;
import javax.mail.Session;

import cn.itcast.estore.dao.UserDAO;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.utils.MailUtils;

/**
 * 用户操作 业务层
 * 
 * @author seawind
 * 
 */
public class UserService {
	// 用户注册
	// 这里没写返回值，用户名 或者 邮箱重复 报错
	public void regist(User user) {
		// 1、将用户信息 保存数据库
		// 生成激活码
		String activecode = MailUtils.generateActivecode();
		user.setActivecode(activecode);

		// 传递DAO
		UserDAO dao = new UserDAO();
		dao.insert(user);
		// 2、发送激活邮件 ---- 安装易邮测试
		// 易邮三个账号 service/111 aaa/111 bbb/111 --- service发邮件

		// 创建Session
		Session session = MailUtils.createSession();

		try {
			// 编写邮件
			Message message = MailUtils.generateMessage(session, user);
			// 发送邮件
			MailUtils.sendMail(message, session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送激活邮件失败！");
		}

	}

	// 账户激活
	public void active(String activecode) {
		// 1、根据激活码 查询账户 --- 判断激活码是否存在
		UserDAO dao = new UserDAO();
		User user = dao.findByActivecode(activecode);
		if (user == null) {
			// 激活码无效
			throw new RuntimeException("激活码无效！");
		} else {
			// 激活码存在
			// 2 判断激活码是否过期
			if (System.currentTimeMillis() - user.getRegisttime().getTime() > 1000 * 60 * 60 * 2) {
				// 超过两小时
				// TODO 重新发送 --- 更新数据库保存激活码
				throw new RuntimeException("激活码已经过期！");
			} else {
				// 3 可以激活
				user.setState(1);
				dao.updateState(user);
			}
		}
	}

	// 用户登录 --- 返回包含所有用户信息 对象
	public User login(User user) {
		// 传递用户名 和密码 到DAO
		UserDAO userDAO = new UserDAO();
		return userDAO.findByUsernameAndPassword(user.getUsername(), user
				.getPassword());
	}
}
