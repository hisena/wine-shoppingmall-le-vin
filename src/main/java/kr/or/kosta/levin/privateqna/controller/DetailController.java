package kr.or.kosta.levin.privateqna.controller;

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
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqna.service.QnaService;
import oracle.net.aso.e;

/**
 * 1:1문의글 상세보기를 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/qna-detail")
public class DetailController implements Controller {

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

		String currentPage = request.getParameter("currentPage");
		String articleId = request.getParameter("articleId");

		if (articleId == null || articleId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}
		if (currentPage == null) {
			currentPage = "1";
		}

		// 반환해줄 map
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 1:1문의글 상세정보를 담을 privateQna 선언
			PrivateQna privateQna;

			// 서비스의 비즈니스메소드 호출
			privateQna = qnaService.detailQna(Integer.parseInt(articleId));

			map.put("privateQna", privateQna);
			map.put("currentPage", currentPage);

			// 문의글 상세정보가 담기지 않은 경우 체크
			if (privateQna != null) {
				return map;
			} else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("privateQna/DetailController 예외 ", e);
		}
	}
}
