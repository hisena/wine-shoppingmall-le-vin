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
 * 
 * @author 류세은, 박소연
 *
 */
@Bean(type = BeanType.Service)
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
	public List<User> list() throws Exception {
		return userDao.listAll();
	}

	@Override
	public Map<String, String> emailDuplicate(String email) throws Exception {
		return userDao.certifyEmail(email);
	}

	/** 로그인 */
	@Override
	public Map<String, String> login(String id, String passwd) throws Exception {

		return userDao.certify(id, passwd);
	}

	/** 회원 가입 */
	@Override
	public boolean join(User user, Address address) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
		boolean userResult = false;
		boolean addressResult = false;
		boolean flag = false;
		// user기본 정보 등록
		userResult = userDao.create(user);
		// 정보등록에 성공하면
		if (userResult) {
			// 주소 정보 등록
			addressResult = addressDao.create(address);
			if (addressResult) {
				// 기본 정보와 주소 정보 등록에 모두 성공할시
				flag = true;
			}
		}
		return flag;
	}
	
	/** 회원 기본정보 목록 */
	@Override
	public User listBasicInfo(String email) throws Exception {
		User user = userDao.readBasicInfo(email);
		return user;
	}
	
	/** 회원정보 수정 */
	@Override
	public boolean changeInfo(User user) throws Exception {
		boolean result = false;
		boolean flag = false;

		// 회원정보 수정 성공여부 확인
		result = userDao.updateInfo(user);
		if (result) { // 회원정보 수정에 성공했을 경우
			flag = true;
		}
		return flag;
	}

	@Override
	public User search(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 신규 배송지 추가
	@Override
	public boolean addAddress(Address address) throws Exception {
		// controller에게 service결과 성공여부 알려주기 위한 변수
		boolean flag = false;
		// insert문 성공 여부를 판단하기 위한 변수
		boolean addressResult = false;
		// create 메소드 호출
		addressResult = addressDao.create(address);
		// dao의 insert문 성공했을 시 true값 리턴
		if (addressResult) {
			flag = true;
		}
		return flag;
	}

}
