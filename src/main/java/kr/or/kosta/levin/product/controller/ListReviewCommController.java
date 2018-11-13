package kr.or.kosta.levin.product.controller;

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
import kr.or.kosta.levin.product.domain.ReviewComment;
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 구매후기글의 댓글리스트를 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-comment-list")
public class ListReviewCommController implements Controller {

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
		

		String parentId = request.getParameter("reviewId");
		
		//부모글 번호 null과 공백값 체크
		if (parentId == null || parentId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		// 반환해줄 map
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReviewComment> reviewCommResult;
		try {

			// 서비스의 비즈니스메소드 호출
			reviewCommResult = productService.listReviewComm(Integer.parseInt(parentId));

			// 검색해온 댓글리스트에 값이 있는 경우
			if (!(reviewCommResult.isEmpty())) {
				map.put("reviewCommResult", reviewCommResult);
				return map;
			} else {
				// 검색해온 댓글리스트에 값이 없을 경우
				map.put("reviewCommResult", "false");
				return map;
			}
		} catch (Exception e) {
			throw new ServletException("productQna/ListReviewCommController 예외 ", e);
		}
	}
}
