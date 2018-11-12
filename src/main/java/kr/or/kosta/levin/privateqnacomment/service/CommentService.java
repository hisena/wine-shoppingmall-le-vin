package kr.or.kosta.levin.privateqnacomment.service;

import java.util.Map;

/**
 * PrivateQnaComment와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 류세은
 */
public interface CommentService {

	/** 1:1문의글 등록 */
	public boolean addComment(Map<String, String> parameter) throws Exception;

	/** 1:1문의글의 댓글 리스트 조회*/
	public Map<String, Object> listComment(int parentId) throws Exception;
}
