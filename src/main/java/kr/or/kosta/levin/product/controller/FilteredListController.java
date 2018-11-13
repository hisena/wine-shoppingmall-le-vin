package kr.or.kosta.levin.product.controller;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import kr.or.kosta.levin.product.domain.FilterPagination;
import kr.or.kosta.levin.product.service.ProductService;

@Bean(type=BeanType.Controller)
@RequestMapping(value="/product/filter/list")
public class FilteredListController implements Controller{
	
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
		
		try {
			// 요청으로부터 데이터를 넣어줌
			JSONParser parser = new JSONParser();
			
			FilterPagination filterPagination = new FilterPagination();
			
			// 페이지 정보
			Integer currentPage = 0;
			String stringCurrentPage = request.getParameter("currentPage");
			if (stringCurrentPage != null) {
				currentPage = Integer.parseInt(stringCurrentPage);
			}
			filterPagination.setCurrentPage(currentPage);
			// 기본값
			filterPagination.setPerPageNum(8);
			
			// 필터 정보
			filterPagination.setKind(request.getParameter("kind"));
			
			Integer regionId = null;
			String stringRegionId = request.getParameter("regionId");
			if (stringRegionId != null) {
				regionId = Integer.parseInt(stringRegionId);
			} 
			filterPagination.setRegionId(regionId);
			
			Map<String, String> alcohol = null;
			String stringAlcohol = request.getParameter("alcohol");
			if (stringAlcohol != null) {
				alcohol = (JSONObject) parser.parse(stringAlcohol);
			}
			filterPagination.setAlcohol(alcohol);
			
			Map<String, Integer> sugarContent = null;
			String stringSugarContent = request.getParameter("sugarContent");
			if (stringSugarContent != null) {
				sugarContent = (JSONObject) parser.parse(stringSugarContent);
			}
			filterPagination.setSugarContent(sugarContent);
			
			Map<String, Integer> body = null;
			String stringBody = request.getParameter("body");
			if (stringBody != null) {
				body = (JSONObject) parser.parse(stringBody);
			}
			filterPagination.setBody(body);
			
			Map<String, Integer> price = null;
			String stringPrice = request.getParameter("price");
			if (stringPrice != null) {
				price = (JSONObject) parser.parse(stringPrice);
			}
			filterPagination.setPrice(price);
			
			// 값 유효성 검사
			if (!filterPagination.hasAllNeccessaryVariables()) {
				throw new RequestBadRequestException();
			} 
			
			return productService.listFilteredProduct(filterPagination);
		
		} catch (Exception e) {
			throw new ServletException("product/FilteredListController 예외", e);
		}
	}
	
}
