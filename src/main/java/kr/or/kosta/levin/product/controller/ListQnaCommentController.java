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
import kr.or.kosta.levin.product.domain.ProductQnaComment;
import kr.or.kosta.levin.product.service.ProductService;

/**
 * 상품문의 댓글 목록을 불러오기 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/qna-comment-list")
public class ListQnaCommentController implements Controller {

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

		// 클라이언트로부터 받은 값
		String qnaId = request.getParameter("qnaId");
		String productId = request.getParameter("productId");
		
		Map<String, String> param = new HashMap<>();
		List<ProductQnaComment> listResult;
		Map<String, Object> map = new HashMap<>();
		try {
			// 전달 받은 값 null 체크
			if(productId != null && qnaId != null) {
				param.put("qnaId", qnaId);
				param.put("productId", productId);
				listResult = productService.listQnaComment(param);
				// 댓글 리스트가 존재하면
				if(!listResult.isEmpty()) {
					map.put("QnaCommentlistResult", listResult);
				}else {
					// 존재 하지 않을 경우
					map.put("QnaCommentlistResult", "false");
				}
				return map;
			}else {
				// 클라이언트로부터 받은 값이 null일 경우
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/QnaListController 예외 ", e);
		}
	}
}
