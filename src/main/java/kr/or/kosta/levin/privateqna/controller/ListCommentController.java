package kr.or.kosta.levin.privateqna.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import kr.or.kosta.levin.privateqna.domain.PrivateQnaComment;
import kr.or.kosta.levin.privateqna.service.QnaService;
import oracle.net.aso.e;

/**
 * 1:1문의글의 댓글리스트를 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/comment-list")
public class ListCommentController implements Controller {

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

		String parentId = request.getParameter("articleId");

		if (parentId == null || parentId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		// 반환해줄 map
		
		List<PrivateQnaComment> list;
		Map<String, Object> listResult = new HashMap<String, Object>();
		try {
			// 서비스의 비즈니스메소드 호출
			list = qnaService.listComment(Integer.parseInt(parentId));

			// 검색해온 댓글리스트에 값이 있는 경우 - commentList가 담긴 map 반환
			if (!list.isEmpty()) {
				listResult.put("listResult", list);
			} else {
				listResult.put("listResult", "false");
			}
			return listResult;
			
		} catch (Exception e) {
			throw new ServletException("privateQna/ListCommentController 예외 ", e);
		}
	}
}
