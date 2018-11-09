package kr.or.kosta.levin.product.controller;

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

		String searchKeyword = request.getParameter("searchKeyword");
		String currentPage = request.getParameter("currentPage");
		if(currentPage == null) {
			currentPage = "0";
		}
		
		// 보여줄 페이지 갯수 전달할시 추가
		// int perPageNum = Integer.parseInt(request.getParameter("perPageNum"));


		//SearchPagination도메인에 전달 받은 값 넣기
		SearchPagination search = new SearchPagination();
		search.setCurrentPage(Integer.parseInt(currentPage));
		search.setSearchKeyword(searchKeyword);
		Map<String, Object> map;

		try {
			map = productService.list(search);
			if(map.get("productList") != null) {
				return map;
			}else {
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("product/ListController 예외 ", e);
		}
	}
}
