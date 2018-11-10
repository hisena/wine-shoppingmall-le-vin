package kr.or.kosta.levin.privateqna.service;

import java.util.Map;


/**
 * PrivateQna와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 류세은
 */
public interface QnaService {

	/** 문의 리스트 및 페이징처리 */
	public Map<String, Object> list(Map<String, String> parameter) throws Exception;
	
}
