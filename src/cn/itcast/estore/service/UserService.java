package cn.itcast.estore.service;

import javax.mail.Message;
import javax.mail.Session;

import cn.itcast.estore.dao.UserDAO;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.utils.MailUtils;

/**
 * �û����� ҵ���
 * 
 * @author seawind
 * 
 */
public class UserService {
	// �û�ע��
	// ����ûд����ֵ���û��� ���� �����ظ� ����
	public void regist(User user) {
		// 1�����û���Ϣ �������ݿ�
		// ���ɼ�����
		String activecode = MailUtils.generateActivecode();
		user.setActivecode(activecode);

		// ����DAO
		UserDAO dao = new UserDAO();
		dao.insert(user);
		// 2�����ͼ����ʼ� ---- ��װ���ʲ���
		// ���������˺� service/111 aaa/111 bbb/111 --- service���ʼ�

		// ����Session
		Session session = MailUtils.createSession();

		try {
			// ��д�ʼ�
			Message message = MailUtils.generateMessage(session, user);
			// �����ʼ�
			MailUtils.sendMail(message, session);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("���ͼ����ʼ�ʧ�ܣ�");
		}

	}

	// �˻�����
	public void active(String activecode) {
		// 1�����ݼ����� ��ѯ�˻� --- �жϼ������Ƿ����
		UserDAO dao = new UserDAO();
		User user = dao.findByActivecode(activecode);
		if (user == null) {
			// ��������Ч
			throw new RuntimeException("��������Ч��");
		} else {
			// ���������
			// 2 �жϼ������Ƿ����
			if (System.currentTimeMillis() - user.getRegisttime().getTime() > 1000 * 60 * 60 * 2) {
				// ������Сʱ
				// TODO ���·��� --- �������ݿⱣ�漤����
				throw new RuntimeException("�������Ѿ����ڣ�");
			} else {
				// 3 ���Լ���
				user.setState(1);
				dao.updateState(user);
			}
		}
	}

	// �û���¼ --- ���ذ��������û���Ϣ ����
	public User login(User user) {
		// �����û��� ������ ��DAO
		UserDAO userDAO = new UserDAO();
		return userDAO.findByUsernameAndPassword(user.getUsername(), user
				.getPassword());
	}
}
