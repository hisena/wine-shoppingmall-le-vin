package kr.or.kosta.levin.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.domain.SearchPagination;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.ProductQnaComment;

/**
 * Product관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스 
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisProductDao implements ProductDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.product.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 상품 목록 불러오기(페이징, 검색처리)
	@Override
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception {
		List<Product> list =null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPage", searchPagination);
		sqlSession.close();	
		return list;
	}
	
	// 검색한 상품 목록 갯수
	@Override
	public int countBySearch(SearchPagination searchPagination) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE+ "countBySearch", searchPagination);
		sqlSession.close();
		return count;
	}

	// 상품 상세
	@Override
	public Product getProduct(String productId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Product product = sqlSession.selectOne(NAMESPACE+ "getProduct", productId);
		sqlSession.close();
		return product;
	}

	// 상품 문의글 목록
	@Override
	public List<ProductQna> listByPageQna(Map<String, String> param) throws Exception {
		List<ProductQna> list =null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPageQna", param);
		sqlSession.close();	
		return list;
	}

	// 상품 문의글 갯수
	@Override
	public int countBySearchQna(String productId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE+ "countBySearchQna", productId);
		sqlSession.close();
		return count;
	}

	// 상품 문의 댓글 목록
	@Override
	public List<ProductQnaComment> listQnaComment(Map<String, String> param) throws Exception {
		List<ProductQnaComment> list =null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listQnaComment", param);
		sqlSession.close();	
		return list;
	}
	
	//전체글 상품 문의글 작성
	@Override
	public boolean createQna(ProductQna productQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int insertResult = sqlSession.insert(NAMESPACE + "createQna", productQna);
		boolean createQnaResult = false;
		// insert에 성공했으면
		if (insertResult == 1) {
			// 커밋해주기
			sqlSession.commit();
			createQnaResult = true;
		} else {
			// 실패했으면 rollback해주기
			sqlSession.rollback();
		}
		sqlSession.close();
		return createQnaResult;
	}
	
}
