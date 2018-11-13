package kr.or.kosta.levin.privateqna.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.domain.Pagination;
import kr.or.kosta.levin.common.domain.PaginationManager;
import kr.or.kosta.levin.privateqna.dao.QnaDao;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqna.domain.PrivateQnaComment;

/**
 * 1:1 문의글 및 댓글과 관련된 비즈니스 로직 수행을 위한 Service 객체
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

		// 페이징, 검색 처리된 문의 리스트
		List<PrivateQna> list = qnaDao.listByPage(parameter);
		// 검색해온 문의 갯수
		int count = qnaDao.countBySearch(parameter);
		// 페이징 관련정보(시작, 끝 페이지 등) 처리
		PaginationManager pm = new PaginationManager();
		Pagination pagination = new Pagination();
		pagination.setCurrentPage(currentPage);
		pm.setPagination(pagination);
		pm.setTotalCount(count);
		
		if(!list.isEmpty()) {
			map.put("qnaList", list);
		}else {
			map.put("qnaList", "false");
		}

		// controller로 넘겨 주기 위해 map에 담아주기
		map.put("pageInfo", pm.pageInfo());
		return map;
	}

	// 1:1문의글 등록
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
		// 1:1문의글 수정 성공여부 확인
		if (result) { // 수정에 성공했을 경우
			flag = true;
		}
		return flag;
	}

	// 1:1문의글 상세보기
	@Override
	public PrivateQna detailQna(int articleId) throws Exception {
		PrivateQna privateQna = qnaDao.readQna(articleId);
		return privateQna;
	}

	// 1:1문의글 삭제
	@Override
	public boolean removeQna(int articleId) throws Exception {
		boolean result = false;
		boolean flag = false;
		// 1:1문의글 삭제
		result = qnaDao.deleteQna(articleId);
		// 1:1문의글 삭제 성공여부 확인
		if (result) { // 삭제에 성공했을 경우
			flag = true;
		}
		return flag;
	}

	// 1:1문의글의 댓글 등록
	@Override
	public boolean addComment(Map<String, String> parameter) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
		boolean addResult = false;
		boolean flag = false;
		// 1:1문의글의 댓글 등록
		addResult = qnaDao.createComment(parameter);
		// 등록에 성공하면
		if (addResult) {
			flag = true;
		}
		return flag;
	}

	// 1:1문의글의 댓글 리스트 조회
	@Override
	public List<PrivateQnaComment> listComment(int parentId) throws Exception {

		return qnaDao.list(parentId);
	}

	// 1:1문의글 댓글 수정
	@Override
	public boolean editComment(Map<String, Object> parameter) throws Exception {
		boolean result = false;
		boolean flag = false;
		// 1:1문의글 댓글 수정
		result = qnaDao.updateComment(parameter);
		// 1:1문의글 댓글 수정 성공여부 확인
		if (result) { // 수정에 성공했을 경우
			flag = true;
		}
		return flag;
	}
	
	//1:1문의글 댓글 삭제
	@Override
	public boolean removeComment(int childId) throws Exception {
			boolean result = false;
			boolean flag = false;
			// 1:1문의글 댓글 삭제
			result = qnaDao.deleteComment(childId);
			// 1:1문의글 댓글 삭제 성공여부 확인
			if (result) { // 삭제에 성공했을 경우
				flag = true;
			}
			return flag;
	}
}
