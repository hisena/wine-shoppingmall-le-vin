package kr.or.kosta.levin.privateqna.dao;

import java.util.List;
import java.util.Map;

import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqna.domain.PrivateQnaComment;

/**
 * 1:1 문의글 및 댓글 관련 기능을 수행하기 위해 DB와 연동하는 Dao 인터페이스 
 * @author 류세은
 * 
 */
public interface QnaDao {
	
	/** 선택페이지, 조회 목록개수, 검색유형에 따른 1:1문의 리스트 목록 반환 */	
	public List<PrivateQna> listByPage(Map<String, String> parameter) throws Exception;
	/** 검색해온 1:1문의 목록 개수 반환*/
	public int countBySearch(Map<String, String> parameter) throws Exception;
	
	/** 1:1문의글 등록*/
	public boolean createQna(PrivateQna privateQna) throws Exception;	
	
	/** 1:1문의글 수정*/
	public boolean updateQna(PrivateQna privateQna) throws Exception;
	
	/** 1:1문의글 상세보기*/
	public PrivateQna readQna(int articleId) throws Exception;
	
	/** 1:1문의글 삭제*/
	public boolean deleteQna(int articleId) throws Exception;
	
	/** 1:1문의글의 댓글 등록 */
	public boolean createComment(Map<String, String> parameter) throws Exception;
	
	/** 1:1문의글의 댓글리스트 조회*/
	public List<PrivateQnaComment> list(int parentId) throws Exception;
	
	/** 1:1문의글 댓글 수정*/
	public boolean updateComment(Map<String, Object> parameter) throws Exception;
	
}
