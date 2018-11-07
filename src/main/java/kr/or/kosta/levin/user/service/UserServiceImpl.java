package kr.or.kosta.levin.user.service;

import java.util.List;

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
	public List<User> list() throws Exception {
		return userDao.listAll();
	}

}





