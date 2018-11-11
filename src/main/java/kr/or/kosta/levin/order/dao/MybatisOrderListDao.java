package kr.or.kosta.levin.order.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;

/**
 * OrderList 관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스 
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisOrderListDao implements OrderListDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.order.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public boolean create(Map<String, String> productInfo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int result = sqlSession.insert(NAMESPACE + "createOrderList",productInfo);
		// service에 실행 결과를 반환해주기 위한 변수
		boolean flag = false;
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

	
}
