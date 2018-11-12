package kr.or.kosta.levin.product.controller;

import java.util.ArrayList;
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
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 구매후기 게시판 목록을 불러오기 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-list")
public class ListReviewController implements Controller {

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

		String productId = request.getParameter("productId");
		String currentPage = request.getParameter("currentPage");

		if (productId == null || productId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		if (currentPage == null) {
			currentPage = "1";
		}

		int perPageNum = 5;

		// 전달받은 값 map 형태로 담기
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.put("productId", productId);
		parameter.put("currentPage", currentPage);
		parameter.put("perPageNum", String.valueOf(perPageNum));
		Map<String, Object> map;
		try {
			map = productService.listReview(parameter);

			// 검색해온 구매후기 리스트에 값이 있는 경우 - reviewList와 pageinfo가 담긴 map 반환
			ArrayList<e> reviewList = (ArrayList) map.get("reviewList");
			if (reviewList.size() != 0) {
				return map;
			} else {
				// 검색해온 구매후기 리스트에 값이 없을 경우
				return false;
			}
		} catch (Exception e) {
			throw new ServletException("product/ListReviewController 예외 ", e);
		}
	}
}
