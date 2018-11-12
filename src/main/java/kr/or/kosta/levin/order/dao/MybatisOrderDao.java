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
	public Map<String, String> getOrder(int orderId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Map<String, String> orderInfo = sqlSession.selectOne(NAMESPACE+ "getOrder", orderId);
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
		// 주문 취소 결과값을 Service에 보내기 위한 변수
		boolean deleteResult = false;
		int delete = sqlSession.update(NAMESPACE+ "deleteOrder", orderId);
		// update문이 잘 실행 되었을 경우
		if(delete == 1) {
			// 변경 사항 커밋
			sqlSession.commit();
			// 성공 값 Service로 보내주기
			deleteResult = true;
		}else {
			// update문 실패했을 경우 롤백
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteResult;
	}

	// 반품하기
	@Override
	public boolean createRefund(String orderId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// 반품 신청 결과값을 Service에 보내기 위한 변수
		boolean refundResult = false;
		int create = sqlSession.update(NAMESPACE+ "createRefund", orderId);
		// insert문이 잘 실행 되었을 경우
		if(create == 1) {
			// 변경 사항 커밋
			sqlSession.commit();
			// 성공 값 Service로 보내주기
			refundResult = true;
		}else {
			// insert문 실패했을 경우 롤백
			sqlSession.rollback();
		}
		sqlSession.close();
		return refundResult;
	}

	
	
}
