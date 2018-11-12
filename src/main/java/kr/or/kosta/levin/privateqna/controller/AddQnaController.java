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

/**
 * 1:1문의글 등록을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/privateqna/add")
public class AddQnaController implements Controller {

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


		// 1:1문의글  등록 결과 변수 선언
		boolean addQnaResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<>();
		// 화면에서 받은 파라미터값 처리
		PrivateQna privateQna = new PrivateQna();
		
		privateQna.setEmail(request.getParameter("email"));
		privateQna.setCategory(request.getParameter("category"));
		privateQna.setTitle(request.getParameter("title"));
		privateQna.setContent(request.getParameter("content"));
		
		// 카테고리 체크
		String category = request.getParameter("category");
		boolean categoryCheck = (category != "주문/결제" && category != "배송" && category != "취소/반품" && category != "회원정보" && category != "기타");
		if(categoryCheck == true) {
			throw new RequestBadRequestException();
		}
		
		try {
			// 파라미터값 null 유효성 검사
			if (privateQna.checkNull(privateQna)) {
				addQnaResult = qnaService.addQna(privateQna);
				// 1:1문의글 등록 성공 시
				if (addQnaResult) {
					map.put("addQnaResult", "true");
				} else {
				// 실패시
					map.put("addQnaResult", "false");
				}
				return map;
			}else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("qnaService.addQna() 예외 발생", e);
		}
	}
}
