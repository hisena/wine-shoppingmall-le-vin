package kr.or.kosta.levin.privateqnacomment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqnacomment.domain.PrivateQnaComment;

/**
 * 1:1문의글의 댓글 관련 기능을 수행하기 위해 DB와 연동하는 Dao 구현클래스
 * 
 * @author 류세은
 *
 */
@Bean(type = BeanType.Repository)
public class MybatisCommentDao implements CommentDao {

	private static final String NAMESPACE = "kr.or.kosta.levin.privateqnacomment.";

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	// 1:1문의글의 댓글 등록
	@Override
	public boolean createComment(Map<String, String> parameter) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		boolean flag = false;

		// insert문 실행 후 반환값 저장
		int createContentResult = sqlSession.insert(NAMESPACE + "createCommentContent", parameter);
		if (createContentResult == 1) {
			sqlSession.insert(NAMESPACE + "createCommentId", parameter);
			sqlSession.commit();
			flag = true;
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return flag;
	}
	
	//1:1문의글의 댓글리스트 조회
	@Override
	public List<PrivateQnaComment> list(int parentId) throws Exception {
		List<PrivateQnaComment> list = null;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		list = sqlSession.selectList(NAMESPACE + "list", parentId);
		sqlSession.close();	
		return list;
	}
}
