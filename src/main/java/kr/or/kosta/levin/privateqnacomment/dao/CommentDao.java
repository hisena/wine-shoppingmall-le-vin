package kr.or.kosta.levin.privateqnacomment.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.privateqnacomment.domain.PrivateQnaComment;

/**
 * 1:1 문의글의 댓글 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스
 * 
 * @author 류세은
 * 
 */
public interface CommentDao {

	/** 1:1문의글의 댓글 등록 */
	public boolean createComment(Map<String, String> parameter) throws Exception;
	
	/** 1:1문의글의 댓글리스트 조회*/
	public List<PrivateQnaComment> list(int parentId) throws Exception;
}
