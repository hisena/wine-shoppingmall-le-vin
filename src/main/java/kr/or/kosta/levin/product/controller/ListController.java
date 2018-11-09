package kr.or.kosta.levin.product.controller;

import java.util.ArrayList;
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
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;
import kr.or.kosta.levin.product.service.ProductService;

/**
 * 회원 기본정보 목록 확인을 위한 세부 컨트롤러
 * 
 * @author 류세은
 */

@Bean(type = BeanType.Controller)
@RequestMapping(value = "/product/list")
public class ListController implements Controller {

	@Inject
	private ProductService productService;

	public  ProductService getProductService() {
		return productService;
	}

	public void setProductService( ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, RequestException {

		// 클라이언트로부터 받는 페이지 및 
		if(request.getParameter("searchKeyword").equals("null")) {
			
		}
		String searchKeyword = request.getParameter("searchKeyword");
		//int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// 보여줄 페이지 갯수 전달할시 추가
		// int perPageNum = Integer.parseInt(request.getParameter("perPageNum"));
		
		Map<String, String> map = new HashMap<String, String>();
		List<Product> list = new ArrayList<>(); 

		//SearchPagination도메인에 전달 받은 값 넣기
		SearchPagination search = new SearchPagination();
		//search.setCurrentPage(currentPage);
		search.setSearchKeyword(searchKeyword);
		search.setCurrentPage(1);

		try {
				list = productService.list(search);
//				if (changeResult) { // 회원정보 수정에 성공했을 경우 - 클라이언트에게 changeResult : true 반환
//					map.put("changeResult", "true");
//				} else {
//					// 실패했을 경우 - 클라이언트에게 changeResult : false 반환
//					map.put("changeResult", "false");
//				}
				return map;
			
		} catch (Exception e) {
			throw new ServletException("userService.changeResult() 예외 발생", e);
		}
	}
}
