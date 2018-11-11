package kr.or.kosta.levin.privateqna.service;

import java.util.Map;

import kr.or.kosta.levin.privateqna.domain.PrivateQna;


/**
 * PrivateQna와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 류세은
 */
public interface QnaService {

	/** 문의 리스트 및 페이징처리 */
	public Map<String, Object> list(Map<String, String> parameter) throws Exception;
	
	/** 1:1문의글 등록 */
	public boolean addQna(PrivateQna privateQna) throws Exception;
	
	/** 1:1문의글 수정 */
	public boolean editQna(PrivateQna privateQna) throws Exception;
	
	/** 1:1문의글 상세보기 */
	public PrivateQna detailQna(int articleId) throws Exception;
}
