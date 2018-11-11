package kr.or.kosta.levin.privateqna.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.privateqna.dao.QnaDao;
import kr.or.kosta.levin.privateqna.domain.Pagination;
import kr.or.kosta.levin.privateqna.domain.PaginationManager;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;

/**
 * PrivateQna와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * 
 * @author 류세은
 *
 */
@Bean(type = BeanType.Service)
public class QnaServiceImpl implements QnaService {

	// dao 선언
	@Inject
	private QnaDao qnaDao;
	
	public QnaDao getQnaDao() {
		return qnaDao;
	}

	public void setQnaDao(QnaDao qnaDao) {
		this.qnaDao = qnaDao;
	}

	// 1:1문의 리스트
	@Override
	public Map<String, Object> list(Map<String, String> parameter) throws Exception {
		int currentPage = Integer.parseInt(parameter.get("currentPage"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		//페이징, 검색 처리된 문의 리스트
		List<PrivateQna> list = qnaDao.listByPage(parameter);
		//검색해온 문의 갯수
		int count = qnaDao.countBySearch(parameter);
		//페이징 관련정보(시작, 끝 페이지 등) 처리 
		PaginationManager pm = new PaginationManager();
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currentPage);
		pm.setPagination(pagination);
		pm.setTotalCount(count);
		
		// controller로 넘겨 주기 위해 map에 담아주기
		map.put("qnaList", list);
		map.put("pageInfo", pm);
		return map;
	}
	
	//1:1문의글 등록
	@Override
	public boolean addQna(PrivateQna privateQna) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
				boolean addResult = false;
				boolean flag = false;
				// 1:1문의글 등록
				addResult = qnaDao.createQna(privateQna);
				// 등록에 성공하면
				if (addResult) {
						flag = true;
				}
				return flag;
	}
	
	@Override
	public boolean editQna(PrivateQna privateQna) throws Exception {
		boolean result = false;
		boolean flag = false;
		// 1:1문의글 수정
		result = qnaDao.updateQna(privateQna);
		//1:1문의글 수정 성공여부 확인
		if (result) { // 회원정보 수정에 성공했을 경우
			flag = true;
		}
		return flag;
	}
	
	//1:1문의글 상세보기
	@Override
	public PrivateQna detailQna(int articleId) throws Exception {
		PrivateQna privateQna = qnaDao.readQna(articleId);
		return privateQna;
	}
}
