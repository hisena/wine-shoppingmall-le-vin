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
import kr.or.kosta.levin.product.service.ProductService;
import oracle.net.aso.e;

/**
 * 구매후기글의 댓글 삭제를 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/review-comment-remove")
public class RemoveReviewCommentController implements Controller {

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

		// 구매후기글의 댓글 삭제 결과 변수 선언
		boolean removeReviewCommentResult;
		// frontController로 값을 보내기 위한 map 선언
		Map<String, String> map = new HashMap<String, String>();

		// 화면에서 받은 파라미터값 처리
		String childId = request.getParameter("commentId");
		
		// 구매후기글의 댓글 번호 null이거나 빈값 들어오는 경우 체크
		if (childId == null || childId.trim().length() == 0) {
			throw new RequestBadRequestException();
		}

		try {
			removeReviewCommentResult = productService.removeReview(Integer.parseInt(childId));
			// 구매후기글 삭제 성공 시
			if (removeReviewCommentResult) {
				map.put("removeReviewCommentResult", "true");
			} else {
				// 실패시
				map.put("removeReviewCommentResult", "false");
			}
			return map;

		} catch (Exception e) {
			throw new ServletException("productService.removeReviewComment() 예외 발생", e);
		}
	}
}
