package kr.or.kosta.levin.order.dao;

import java.util.List;

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
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception {
		List<Product> list =null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPage", searchPagination);
		sqlSession.close();	
		return list;
	}
	
	// 검색한 주문 목록 갯수
	@Override
	public int countBySearch(SearchPagination searchPagination) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE+ "countBySearch", searchPagination);
		sqlSession.close();
		return count;
	}

	// 주문 상세
	@Override
	public Product getProduct(String productId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Product product = sqlSession.selectOne(NAMESPACE+ "getProduct", productId);
		sqlSession.close();
		return product;
	}

	@Override
	public int create(Order order) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int result =0;
		result= sqlSession.insert(NAMESPACE + "createOrder", order);
		// insert에 성공했으면
		if (result != 0) {
			// 커밋해주기
			sqlSession.commit();
			sqlSession.close();
			return result;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
			sqlSession.close();
			return result;
		}
		
	}
	
}
