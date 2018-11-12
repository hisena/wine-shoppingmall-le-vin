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
 * 1:1문의글 수정을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/qna-edit")
public class EditQnaController implements Controller {

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

		// 1:1문의글 수정 결과 변수 선언
		boolean editQnaResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<>();

		// 화면에서 받은 파라미터값 처리
		PrivateQna privateQna = new PrivateQna();
		String articleId = request.getParameter("articleId");
		String email = request.getParameter("email");
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		// 카테고리 체크
		boolean categoryCheck = (!category.equals("주문/결제") 
				&& !category.equals("배송") 
				&& !category.equals("취소/반품") 
				&& !category.equals("회원정보")
				&& !category.equals("기타"));
		if (categoryCheck) {
			throw new RequestBadRequestException();
		}

		// 주문번호 null이거나 빈값 들어오는 경우 체크
		if (articleId == null || articleId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		privateQna.setArticleId(Integer.parseInt(articleId));
		privateQna.setEmail(email);
		privateQna.setCategory(category);
		privateQna.setTitle(title);
		privateQna.setContent(content);

		try {
			// 파라미터값 null 유효성 검사
			if (privateQna.checkNull(privateQna)) { // Null 체크 통과시
				editQnaResult = qnaService.editQna(privateQna);
				// 1:1문의글 수정 성공 시
				if (editQnaResult) {
					map.put("editQnaResult", "true");
				} else {
					// 실패시
					map.put("editQnaResult", "false");
				}
				return map;
			} else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("qnaService.editQna() 예외 발생", e);
		}
	}
}
