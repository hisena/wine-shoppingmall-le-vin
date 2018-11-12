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
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqna.service.QnaService;
import kr.or.kosta.levin.product.domain.Review;
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 구매후기 상세보기를 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-detail")
public class DetailReviewController implements Controller {

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

		String currentPage = request.getParameter("currentPage");
		String reviewId = request.getParameter("reviewId");
		
		//reviewId null과 공백값 체크
		if (reviewId == null || reviewId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}
		if (currentPage == null) {
			currentPage = "1";
		}

		// 반환해줄 map
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			// 구매후기글 상세정보를 담을 Review 선언
			Review review;

			// 서비스의 비즈니스메소드 호출
			review = productService.detailReview(Integer.parseInt(reviewId));

			map.put("review", review);
			map.put("currentPage", currentPage);

			// 구매후기글 상세정보가 담기지 않은 경우 체크
			if (review != null) {
				return map;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new ServletException("product/DetailReviewController 예외 ", e);
		}
	}
}
