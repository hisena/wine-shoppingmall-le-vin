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
 * 상품문의글 작성하기 기능을 위한 세부 컨트롤러
 * 
 * @author 박소연
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/qna-comment-add")
public class AddQnaCommentController implements Controller {

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
		String privateYn = request.getParameter("privateYn");
		String productId = request.getParameter("productId");
	    String writer = request.getParameter("writer");
	    String content = request.getParameter("content");
	    
		boolean addCommentResult;
		ProductQna productQna = new ProductQna();
		Map<String, String> map = new HashMap<>();
		try {
			// 전달 받은 값 null 체크
			if(productId != null && privateYn != null && qnaId != null && writer != null && content != null
					&& productId.trim().length() != 0 && privateYn.trim().length() != 0
					&& qnaId.trim().length() != 0 && writer.trim().length() != 0
					&& content.trim().length() != 0) {
				// 도메인에 담아주기
				productQna.setProductId(productId);
				productQna.setPrivateYn(privateYn);
				productQna.setQnaId(qnaId);
				productQna.setContent(content);
				productQna.setWriter(writer);
				
				addCommentResult = productService.addQnaComment(productQna);
				// service 작업 결과가 true일 경우
				if(addCommentResult) {
					map.put("addQnaComment", "true");
				}else {
					map.put("addQnaComment", "false");
				}
				return map;
				
			}else {
				// 클라이언트로부터 받은 값이 null일 경우
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/addQnaCommentCommentController 예외 ", e);
		}
	}
}
