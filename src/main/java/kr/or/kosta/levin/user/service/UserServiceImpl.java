package kr.or.kosta.levin.user.service;

import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.user.dao.AddressDao;
import kr.or.kosta.levin.user.dao.UserDao;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;

@Bean(type=BeanType.Service)
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDao userDao;
	@Inject
	private AddressDao addressDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public AddressDao getAddressDao() {
		return addressDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
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
	public Map<String, String> login(String id, String passwd) throws Exception {
		
		return userDao.certify(id, passwd);
	}

	@Override
	public boolean join(User user, Address address) throws Exception {
		boolean userResult = false;
		boolean addressResult = false;
		
		userResult = userDao.create(user);
		addressResult = addressDao.create(address);
		
		if(userResult && addressResult) {
			return true;
		}else {
			return false;
		}
	}

}





