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
import kr.or.kosta.levin.privateqna.domain.PrivateQnaComment;
import kr.or.kosta.levin.privateqna.service.QnaService;
import oracle.net.aso.e;

/**
 * 1:1문의글의 댓글 등록을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/comment-add")
public class AddCommentController implements Controller {

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

		// 1:1문의글 등록 결과 변수 선언
		boolean addCommentResult;
		// frontController로 값을 보내기 위한 map 생성
		Map<String, String> map = new HashMap<String, String>();

		// 화면에서 받은 파라미터 담을 map 생성
		Map<String, String> parameter = new HashMap<String, String>();

		String parentId = request.getParameter("articleId");
		String email = request.getParameter("email");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		
		//parentId null이나 공백 체크
		if (parentId == null || parentId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}
		// 카테고리 체크
		boolean categoryCheck = (category != "주문/결제" && category != "배송" && category != "취소/반품" && category != "회원정보"
				&& category != "기타");
		if (categoryCheck) {
			throw new RequestBadRequestException();
		}

		// 전달받은 값 map 형태에 담기
		parameter.put("parentId", parentId);
		parameter.put("email", email);
		parameter.put("category", category);
		parameter.put("content", content);

		// null값 유효성 검증을 위한 privateQnaComment 생성
		PrivateQnaComment privateQnaComment = new PrivateQnaComment();
		privateQnaComment.setEmail(email);
		privateQnaComment.setCategory(category);
		privateQnaComment.setContent(content);

		try {
			// 파라미터값 null 유효성 검사
			if (privateQnaComment.checkNull(privateQnaComment)) {
				addCommentResult = qnaService.addComment(parameter);
				// 1:1문의글의 댓글 등록 성공 시
				if (addCommentResult) {
					map.put("addCommentResult", "true");
				} else {
					// 실패시
					map.put("addCommentResult", "false");
				}
				return map;
			} else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("qnaService.addComment() 예외 발생", e);
		}
	}
}
