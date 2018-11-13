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
@RequestMapping(value = "/product/qna-add")
public class AddQnaController implements Controller {

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
		String privateYn = request.getParameter("privateYn");
		String productId = request.getParameter("productId");
		String title = request.getParameter("title");
	    String writer = request.getParameter("writer");
	    String content = request.getParameter("content");
		    
		boolean addResult;
		ProductQna productQna = new ProductQna();
		Map<String, String> map = new HashMap<>();
		try {
			// 전달 받은 값 null 체크
			if(productId != null && privateYn != null && title != null && writer != null && content != null
					&& productId.trim().length() != 0 && privateYn.trim().length() != 0
					&& title.trim().length() != 0 && writer.trim().length() != 0
					&& content.trim().length() != 0) {
				// 도메인에 담아주기
				productQna.setProductId(productId);
				productQna.setPrivateYn(privateYn);
				productQna.setTitle(title);
				productQna.setContent(content);
				productQna.setWriter(writer);
				
				addResult = productService.addQna(productQna);
				// service 작업 결과가 true일 경우
				if(addResult) {
					map.put("addQna", "true");
				}else {
					map.put("addQna", "false");
				}
				return map;
				
			}else {
				// 클라이언트로부터 받은 값이 null일 경우
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/AddQnaController 예외 ", e);
		}
	}
}
