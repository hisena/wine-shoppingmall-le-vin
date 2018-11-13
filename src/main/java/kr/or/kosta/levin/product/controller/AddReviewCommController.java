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
import kr.or.kosta.levin.product.domain.ReviewComment;
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 구매후기글의 댓글 등록을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-comment-add")
public class AddReviewCommController implements Controller {

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

		// 구매후기글 등록 결과 변수 선언
		boolean addReviewCommResult;
		// frontController로 값을 보내기 위한 map 생성
		Map<String, String> map = new HashMap<String, String>();

		// 화면에서 받은 파라미터 담을 map 생성
		Map<String, String> parameter = new HashMap<String, String>();
		
		String parentId = request.getParameter("reviewId");
		String productId = request.getParameter("productId");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		
		// parentId null이나 공백 체크
		if (parentId == null || parentId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}
		
		// productId null이나 공백 체크
		if (productId == null || productId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}
		
		// 전달받은 값 map 형태에 담기
		parameter.put("parentId", parentId);
		parameter.put("productId", productId);
		parameter.put("email", email);
		parameter.put("content", content);

		// null값 유효성 검증을 위한 privateQnaComment 생성
		ReviewComment reviewComment = new ReviewComment();
		reviewComment.setEmail(email);
		reviewComment.setContent(content);

		try {
			// 파라미터값 null 유효성 검사
			if (reviewComment.checkNull(reviewComment)) {
				addReviewCommResult = productService.addReviewComm(parameter);
				// 구매후기글의 댓글 등록 성공 시
				if (addReviewCommResult) {
					map.put("addReviewCommResult", "true");
				} else {
					// 실패시
					map.put("addReviewCommResult", "false");
				}
				return map;
			} else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("productService.addReviewComm() 예외 발생", e);
		}
	}
}
