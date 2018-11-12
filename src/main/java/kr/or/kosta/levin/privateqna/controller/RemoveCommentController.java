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
import kr.or.kosta.levin.privateqna.service.QnaService;
import oracle.net.aso.e;

/**
 * 1:1문의글 댓글 삭제를 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/comment-remove")
public class RemoveCommentController implements Controller {

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

		// 1:1문의글 댓글 삭제 결과 변수 선언
		boolean removeCommentResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<String, String>();

		// 화면에서 받은 파라미터값 처리
//		String childId = request.getParameter("articleId");
		
		//테스트
		String childId = "3";
		// 댓글번호 null이거나 빈값 들어오는 경우 체크
		if (childId == null || childId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		try {
			removeCommentResult = qnaService.removeComment(Integer.parseInt(childId));
			// 1:1문의글 삭제 성공 시
			if (removeCommentResult) {
				map.put("removeCommentResult", "true");
			} else {
				// 실패시
				map.put("removeCommentResult", "false");
			}
			return map;

		} catch (Exception e) {
			throw new ServletException("qnaService.removeComment() 예외 발생", e);
		}
	}
}
