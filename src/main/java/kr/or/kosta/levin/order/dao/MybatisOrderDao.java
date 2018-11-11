package kr.or.kosta.levin.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.order.domain.Order;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

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

	// 주문 상세
	@Override
	public Map<String, String> getOrder(String productId) throws Exception {
//		SqlSession sqlSession = sqlSessionFactory.openSession();
//		Product product = sqlSession.selectOne(NAMESPACE+ "getProduct", productId);
//		sqlSession.close();
//		return product;
		return null;
	}

	// 주문하기
	@Override
	public boolean create(Order order) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장을 위한 변수
		int result =0;
		// service에 실행 결과를 반환해주기 위한 변수
		boolean flag = false;
		result= sqlSession.insert(NAMESPACE + "createOrder", order);
		
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
