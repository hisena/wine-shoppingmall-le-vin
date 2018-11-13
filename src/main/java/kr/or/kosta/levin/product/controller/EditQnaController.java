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
 * 구매후기글 수정을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-edit")
public class EditQnaController implements Controller {

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
		ProductQna productQna = new ProductQna();
		String qnaId = request.getParameter("qnaId");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String currentPage = request.getParameter("currentPage");
		// 현재 페이지 값 처리
		if(currentPage == null || currentPage.trim().length() == 0) {
			currentPage ="1";
		}
		try {
			// 파라미터값 null 체크
			if (qnaId != null && title != null && content != null &&
					qnaId.trim().length() != 0 && title.trim().length() != 0 && content.trim().length() !=0) {
				productQna.setQnaId(qnaId);
				productQna.setTitle(title);
				productQna.setContent(content);
				boolean editQna = productService.editQna(productQna);
				
				map.put("currentPage", currentPage);
				// 검색해온 상품문의글 상세정보가 이 null이 아니면
				if (editQna) {
					map.put("qnaEditResult", "true");
				} else {
					// null일 경우
					map.put("qnaEditResult", "false");
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
