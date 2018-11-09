package kr.or.kosta.levin.privateqna.service;

import java.util.Map;

import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * PrivateQna와 관련된 비즈니스 로직 수행을 위한 인터페이스
 * 
 * @author 류세은
 */
public interface QnaService {


	/** 상품 목록 및 페이징처리 */
	public Map<String, Object> list(SearchPagination searchPagination) throws Exception;
	
}
