package kr.or.kosta.levin.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.order.domain.Order;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스 
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisOrderDao implements OrderDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.order.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 주문 목록 불러오기(페이징, 검색처리)
	@Override
	public List<Map<String, String>> listByPage(Map<String, String> param) throws Exception {
		List<Map<String, String>> list =null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPage", param);
		sqlSession.close();	
		return list;
	}
	
	// 검색한 주문 목록 갯수
	@Override
	public int countByList(String email) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE+ "countByList", email);
		sqlSession.close();
		return count;
	}

	// 주문 상세 - 주문정보 및 배송정보
	@Override
	public Map<String, String> getOrder(Map<String, String> param) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> orderInfo = sqlSession.selectOne(NAMESPACE+ "getOrder", param);
		sqlSession.close();
		return orderInfo;
	}
	// 주문 상세 - 상품정보
	@Override
	public List<Map<String, String>> getProduct(String orderId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<Map<String, String>> productInfo = sqlSession.selectList(NAMESPACE+ "getProduct", orderId);
		sqlSession.close();
		return productInfo;
	}

	// 주문하기
	@Override
	public boolean create(Order order) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장을 위한 변수
		
		// service에 실행 결과를 반환해주기 위한 변수
		boolean createResult = false;
		int insert = sqlSession.insert(NAMESPACE + "createOrder", order);
		
		// insert에 성공했으면
		if (insert == 1) {
			// 커밋해주기
			sqlSession.commit();
			createResult = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return createResult;
	}
	
	// 주문 취소하기
	@Override
	public boolean deleteOrder(String orderId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		boolean deleteResult = false;
		int delete = sqlSession.update(NAMESPACE+ "deleteOrder", orderId);
		if(delete == 1) {
			sqlSession.commit();
			deleteResult = true;
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteResult;
	}

	
	
}
