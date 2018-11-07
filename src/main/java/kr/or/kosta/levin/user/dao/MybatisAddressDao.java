package kr.or.kosta.levin.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.user.domain.Address;

/**
 * Address 정보와 DB를 연동하기 위한 구현 클래스
 * @author 류세은, 박소연
 *
 */
@Bean(type=BeanType.Repository)
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

	// 회원가입
	@Override
	public boolean create(Address address) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int result = sqlSession.insert(NAMESPACE + "createAddress", address);
		boolean flag = false;
		// insert에 성공했으면
		if(result == 1) {
			// 커밋해주기
			sqlSession.commit();
			flag = true;
		}else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}

	
}










