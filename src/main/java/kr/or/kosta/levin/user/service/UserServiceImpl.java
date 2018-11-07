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

/**
 * User와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * @author 류세은, 박소연
 *
 */
@Bean(type=BeanType.Service)
public class UserServiceImpl implements UserService {
	
	// dao 선언
	@Inject
	private UserDao userDao;
	@Inject
	private AddressDao addressDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	// dao getter/setter
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

	/** 로그인*/
	@Override
	public Map<String, String> login(String id, String passwd) throws Exception {
		
		return userDao.certify(id, passwd);
	}

	/** 회원 가입*/
	@Override
	public boolean join(User user, Address address) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
		boolean userResult = false;
		boolean addressResult = false;
		boolean flag = false;
		// user기본 정보 등록
		userResult = userDao.create(user);
		// 정보등록에 성공하면
		if(userResult) {
			// 주소 정보 등록
			addressResult = addressDao.create(address);
			if(addressResult) {
				// 기본 정보와 주소 정보 등록에 모두 성공할시
				flag = true;
			}
		}
		return flag;
	}

}





