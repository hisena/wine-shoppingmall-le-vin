package kr.or.kosta.levin.privateqna.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import kr.or.kosta.levin.common.domain.Pagination;
import kr.or.kosta.levin.privateqna.service.QnaService;
import oracle.net.aso.e;

/**
 * 1:1문의 게시판 목록을 불러오기 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/list")
public class ListController implements Controller {

	// 서비스 선언
	@Inject
	private QnaService qnaService;


	public QnaService getQnaService() {
		return qnaService;
	}


	public void setQnaService(QnaService qnaService) {
		this.qnaService = qnaService;
	}


	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, RequestException {

		String searchType = request.getParameter("searchType");
		String currentPage = request.getParameter("currentPage");
		String email = request.getParameter("email");
		
		if(email == null || email.trim().length() == 0) {
			throw new RequestBadRequestException();
		}
		
		if(currentPage == null) {
			currentPage = "1";
		}
		
		//pagination에 담겨있는 perPageNum 기본값 받기
		Pagination pagination = new Pagination();
		int perPageNum = pagination.getPerPageNum();
		
		//전달받은 값 map 형태로 담기
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("searchType", searchType);
		parameter.put("currentPage", currentPage);
		parameter.put("email", email);
		parameter.put("perPageNum", String.valueOf(perPageNum));
		Map<String, Object> map;
		try {
				map = qnaService.list(parameter);	
			
			// 검색해온 문의리스트에 값이 있는 경우 - qnaList와 pageinfo가 담긴 map 반환
			ArrayList<e> qnaList = (ArrayList)map.get("qnaList");
				if(qnaList.size() != 0) {
					return map;
				}else {
			// 검색해온 문의리스트에 값이 없을 경우 - 400 에러
					throw new RequestBadRequestException();
				}
		} catch (Exception e) {
			throw new ServletException("privateQna/ListController 예외 ", e);
		}
	}
}
