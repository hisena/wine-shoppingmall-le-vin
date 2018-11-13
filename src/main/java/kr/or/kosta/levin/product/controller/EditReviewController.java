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
import oracle.net.aso.e;

/**
 * 구매후기글 수정을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-edit")
public class EditReviewController implements Controller {

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

		// 구매후기글 수정 결과 변수 선언
		boolean editReviewResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<String, String>();

		// 화면에서 받은 파라미터값 처리
		Review review = new Review();
//		String reviewId = request.getParameter("reviewId");
//		String title = request.getParameter("title");
//		String content = request.getParameter("content");
		String grade = request.getParameter("grade");
		System.out.println("grade : " + grade);
		//테스트
		String reviewId = "3";
		String title = "최고의 레드와인";
		String content = "진짜 맛있어요 대박이예요";
//		String grade = "5";
		
		//평점 입력하지 않았을 시
		if(grade == null) {
			grade = "0"; //grade가 0이면 평점 별처리할 때 빈 별 표시
		}
		
		// 구매후기글 번호 null이거나 빈값 들어오는 경우 체크
		if (reviewId == null || reviewId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		review.setReviewId(Integer.parseInt(reviewId));
		review.setTitle(title);
		review.setContent(content);
		review.setGrade(grade);
		
		try {
			// 파라미터값 null 유효성 검사
			if (review.checkNull(review)) { // Null 체크 통과시
				editReviewResult = productService.editReview(review);
				// 구매후기글 수정 성공 시
				if (editReviewResult) {
					map.put("editReviewResult", "true");
				} else {
					// 실패시
					map.put("editReviewResult", "false");
				}
				return map;
			} else {
				// 파라미터 값 중 null이 있을 때 예외처리
				throw new RequestBadRequestException();
			}

		} catch (Exception e) {
			throw new ServletException("productService.editReview() 예외 발생", e);
		}
	}
}
