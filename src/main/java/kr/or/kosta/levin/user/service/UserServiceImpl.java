package kr.or.kosta.levin.user.service;

import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.user.dao.UserDao;
import kr.or.kosta.levin.user.domain.User;

@Bean(type=BeanType.Service)
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User search(String id) throws Exception {
		return userDao.read(id);
	}

	@Override
	public List<User> list() throws Exception {
		return userDao.listAll();
	}

	
	@Override
	public Map<String, String> emailDuplicate(String email) throws Exception {
		return userDao.certifyEmail(email);
	}
	@Override
	public Map<String, String> login(String id, String passwd) throws Exception {
		
		return userDao.certify(id, passwd);
	}

}





