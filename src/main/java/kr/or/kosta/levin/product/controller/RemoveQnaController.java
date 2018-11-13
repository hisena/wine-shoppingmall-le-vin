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
import kr.or.kosta.levin.product.domain.ProductQna;
import kr.or.kosta.levin.product.domain.Review;
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 상품문의글 및 댓글 삭제를 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/qna-remove")
public class RemoveQnaController implements Controller {

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
			if (qnaId != null  &&qnaId.trim().length() != 0 ) {
				boolean romoveQna = productService.removeQna(qnaId);
				
				map.put("currentPage", currentPage);
				// 삭제가 잘 되었을 경우
				if (romoveQna) {
					map.put("qnaRemoveResult", "true");
				} else {
					// null일 경우
					map.put("qnaRemoveResult", "false");
				}
				return map;
			// 파라미터 값이 null일 경우
			} else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/RemoveQnaController 예외 ", e);
		}
	}
}
