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
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.service.ProductService;

/**
 * 상품문의 상세보기를 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/qna-detail")
public class DetailQnaController implements Controller {

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

		Map<String, Object> map = new HashMap<>();
		String qnaId = request.getParameter("qnaId");
		String currentPage = request.getParameter("currentPage");
		// 현재 페이지 값 처리
		if(currentPage == null || currentPage.trim().length() == 0) {
			currentPage ="1";
		}
		try {
			// 파라미터값 null 체크
			if (qnaId != null && qnaId.trim().length() != 0) {
				ProductQna productQna = productService.detailQna(qnaId);
				
				map.put("currentPage", currentPage);
				// 검색해온 상품문의글 상세정보가 이 null이 아니면
				if (productQna != null) {
					map.put("qnaDetailResult", productQna);
				} else {
					// null일 경우
					map.put("qnaDetailResult", "false");
				}
				return map;
			// 파라미터 값이 null일 경우
			} else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/DetailController 예외 ", e);
		}
	}
}
