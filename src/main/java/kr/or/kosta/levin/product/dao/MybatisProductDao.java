package kr.or.kosta.levin.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * User 정보와 DB를 연동하기 위한 구현 클래스
 * 
 * @author 류세은, 박소연
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

	@Override
	public List<Product> listByPage(SearchPagination searchPagination) throws Exception {
		List<Product> list =null;
		System.out.println(searchPagination.getCurrentPage());
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			list = sqlSession.selectList(NAMESPACE + "listByPage", searchPagination);	
		}catch (Exception e) {
			
		}
		return list;
	}

	

	
}
