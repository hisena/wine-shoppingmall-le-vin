package kr.or.kosta.levin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestPreconditionFailedException;
import kr.or.kosta.levin.common.domain.RandomNumberGenerator;
import kr.or.kosta.levin.user.dao.AddressDao;
import kr.or.kosta.levin.user.dao.UserDao;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.EmailVali;
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

	/** 신규 배송지 추가 */
	@Override
	public boolean addAddress(Address address) throws Exception, RequestPreconditionFailedException {
		// controller에게 service결과 성공여부 알려주기 위한 변수
		boolean addAddressResult = false;
		// insert문 성공 여부를 판단하기 위한 변수
		boolean addResult = false;

		// address중복 검사
		boolean certify = addressDao.certify(address);
		// 주소 중복이 아닐 경우
		if (certify) {
			// create 메소드 호출
			addResult = addressDao.create(address);
			// dao의 insert문 성공했을 시 true값 리턴
			if (addResult) {
				addAddressResult = true;
			}
		} else {
			// 주소가 중복될 경우 412에러 보내주기
			throw new RequestPreconditionFailedException();
		}
		return addAddressResult;
	}

	/** 배송지 삭제 */
	@Override
	public boolean deleteAddress(String addressId) throws Exception {
		// controller에게 service결과 성공여부 알려주기 위한 변수
		boolean deleteAddressResult = false;
		// 배송지 삭제 성공 여부를 판단하기 위한 변수
		boolean deleteResult = false;

		deleteResult = addressDao.delete(addressId);
		// dao의 배송지 삭제 성공했을 시 true값 리턴
		if (deleteResult) {
			deleteAddressResult = true;
		}
		return deleteAddressResult;
	}

	/** 비밀번호 찾기 - 인증번호 보내주기 */
	@Override
	public boolean generateValiNum(String email) throws Exception, RequestPreconditionFailedException {
		String valiNumber = RandomNumberGenerator.generateRandomNum();
		// service 성공 여부를 controller에 보내주기 위한 변수
		boolean generateValiNumResult = false;
		// insert문 성공 여부를 판단하기 위한 변수
		boolean generateResult = false;
		EmailVali emailVali = new EmailVali();

		// 이메일 중복이 아닐 경우
		if (userDao.certifyEmail(email) != null) {
			// generateValiNum 메소드에 보낼 값 처리
			emailVali.setEmail(email);
			emailVali.setValiNumber(valiNumber);
			// generateValiNum 메소드 호출
			generateResult = userDao.generateValiNum(emailVali);
			// dao의 insert문 성공했을 시 true값 리턴
			if (generateResult) {
				generateValiNumResult = true;
			}
		}
		// 이메일 중복일 경우 412에러
		else {
			throw new RequestPreconditionFailedException();
		}
		return generateValiNumResult;

	}

	@Override
	public boolean findPassword(Map<String, String> param) throws Exception, RequestPreconditionFailedException {
		// service의 수행 결과를 controller에게 알려주기 위한 변수
		boolean findPasswordResult = false;
		// 비밀번호 수정 완료 여부를 위한 변수
		boolean findResult = false;

		// 인증번호 확인하는 메소드 호출, 인증번호가 일치하는 경우
		if (userDao.checkValidaitonNumber(param.get("valiNumber")) != null) {
			// 패스워드 수정을 위한 메소드 호출
			findResult = userDao.updatePassword(param);
			// dao의 insert문 성공했을 시 true값 리턴
			if (findResult) {
				findPasswordResult = true;
			}
		}
		// 인증번호가 일치 하지 않을 경우 412에러
		else {
			throw new RequestPreconditionFailedException();
		}
		return findPasswordResult;
	}

}
