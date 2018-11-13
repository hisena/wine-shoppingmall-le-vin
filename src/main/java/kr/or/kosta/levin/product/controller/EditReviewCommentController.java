package kr.or.kosta.levin.product.controller;

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
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 구매후기글의 댓글 수정을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-comment-edit")
public class EditReviewCommentController implements Controller {

	// 서비스 선언
	@Inject
	private ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, RequestException {

		// 구매후기글의 댓글 수정 결과 변수 선언
		boolean editReviewCommentResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<String, String>();

		// 화면에서 받은 파라미터값 처리
		String childId = request.getParameter("commentId");
		String content = request.getParameter("content");
		
		// 댓글번호 null이거나 빈값 들어오는 경우 체크
		if (childId == null || childId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("childId", Integer.parseInt(childId));
		parameter.put("content", content);

		try {
			// content null이나 공백 유효성 검사
			if (content != null && content.trim().length() != 0) { // Null 체크 통과시
				editReviewCommentResult = productService.editReviewComment(parameter);
				// 구매후기글의 댓글 수정 성공 시
				if (editReviewCommentResult) {
					map.put("editReviewCommentResult", "true");
				} else {
					// 실패시
					map.put("editReviewCommentResult", "false");
				}
				return map;
			} else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("productService.editReviewComment() 예외 발생", e);
		}
	}
}
