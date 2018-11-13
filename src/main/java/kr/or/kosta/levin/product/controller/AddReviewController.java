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
import kr.or.kosta.levin.product.domain.Review;
import kr.or.kosta.levin.product.service.ProductService;

/**
 * 구매후기글 등록을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-add")
public class AddReviewController implements Controller {

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
		boolean addReviewResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<>();
		// 화면에서 받은 파라미터값 처리
		Review review = new Review();

		String productId = request.getParameter("productId");
		String email = request.getParameter("email");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String grade = request.getParameter("grade");

		// productId null과 공백값 확인
		if (productId == null || productId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		review.setProductId(Integer.parseInt(productId));
		review.setEmail(email);
		review.setTitle(title);
		review.setContent(content);
		review.setGrade(grade);
		try {
			// 파라미터값 null 유효성 검사
			if (review.checkNull(review) && review.getGrade() != null && review.getGrade().trim().length() != 0) {
				addReviewResult = productService.addReview(review);
				// 구매후기글 등록 성공 시
				if (addReviewResult) {
					map.put("addReviewResult", "true");
				} else {
					// 실패시
					map.put("addReviewResult", "false");
				}
				return map;
			} else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("productService.addReview() 예외 발생", e);
		}
	}
}
