package kr.or.kosta.levin.privateqna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;

/**
 * 1:1문의 관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스 
 * 
 * @author 류세은
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisQnaDao implements QnaDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.privateqna.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 1:1문의 리스트 불러오기(페이징, 검색처리)
	@Override
	public List<PrivateQna> listByPage(Map<String, String> parameter) throws Exception {
		List<PrivateQna> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "listByPage", parameter);
		sqlSession.close();	
		return list;
	}
	
	// 검색한 문의 목록 갯수
	@Override
	public int countBySearch(Map<String, String> parameter) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int count = sqlSession.selectOne(NAMESPACE+ "countBySearch", parameter);
		sqlSession.close();
		return count;
	}
	
	// 1:1문의글 등록
	@Override
	public boolean createQna(PrivateQna privateQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// insert문 실행 후 반환값 저장
		int result = sqlSession.insert(NAMESPACE + "createQna", privateQna);
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
	
	//1:1문의글 수정
	@Override
	public boolean updateQna(PrivateQna privateQna) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update(NAMESPACE + "updateQna", privateQna);
		boolean flag = false;
		// update에 성공한 경우
		if (result == 1) {
			// 커밋
			sqlSession.commit();
			flag = true;
		} else {
			// 실패한 경우 rollback
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}
	
	//1:1문의글 상세보기
	@Override
	public PrivateQna readQna(int articleId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		PrivateQna privateQna = null;
		privateQna = sqlSession.selectOne(NAMESPACE + "readQna", articleId);
		sqlSession.close();	
		return privateQna;
	}
	
	//1:1문의글 삭제
	@Override
	public boolean deleteQna(int articleId) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result = sqlSession.update(NAMESPACE + "deleteQna", articleId);
		boolean flag = false;
		// delete에 성공한 경우
		if (result == 1) {
			// 커밋
			sqlSession.commit();
			flag = true;
		} else {
			// 실패한 경우 rollback
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}
	
}
