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
import kr.or.kosta.levin.product.service.ProductService;

/**
 * 상품문의 댓글 수정을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/qna-comment-edit")
public class EditQnaCommentController implements Controller {

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
		String content = request.getParameter("content");
		String currentPage = request.getParameter("currentPage");

		// 현재 페이지 값 처리
		if(currentPage == null || currentPage.trim().length() == 0) {
			currentPage ="1";
		}
		try {
			// 파라미터값 null 체크
			if (qnaId != null  && content != null &&
					qnaId.trim().length() != 0  && content.trim().length() !=0) {
				productQna.setQnaId(qnaId);
				productQna.setContent(content);
				boolean editQnaComment = productService.editQnaComment(productQna);
				
				map.put("currentPage", currentPage);
				// 업데이트가 제대로 됐으면
				if (editQnaComment) {
					map.put("qnaCommentEditResult", "true");
				} else {
					// null일 경우
					map.put("qnaCommentEditResult", "false");
				}
				return map;
			// 파라미터 값이 null일 경우
			} else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/EditQnaController 예외 ", e);
		}
	}
}
