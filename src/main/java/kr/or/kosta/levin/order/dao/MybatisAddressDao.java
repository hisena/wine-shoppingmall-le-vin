package kr.or.kosta.levin.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.order.domain.Address;
import kr.or.kosta.levin.user.domain.User;

/**
 * Delivery 관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스 
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisAddressDao implements AddressDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.user.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public boolean create(Address address) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장을 위한 변수
		int result = 0;
		boolean flag=false;
		result = sqlSession.insert(NAMESPACE + "createNewAddress", address);
		
		// insert에 성공했으면
		if (result == 1) {
			// 커밋해주기
			sqlSession.commit();
			flag = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}

	// 배송지 목록 불러오기
	@Override
	public List<Address> list(String email) throws Exception {
		List<Address> list = new ArrayList<>();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "list", email);
		sqlSession.close();
		return list;
	}

	
}
