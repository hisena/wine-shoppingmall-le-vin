package kr.or.kosta.levin.product.controller;

import java.util.HashMap;
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
			if (stringCurrentPage != null && !stringCurrentPage.equals("undefined")) {
				currentPage = Integer.parseInt(stringCurrentPage);
			}
			filterPagination.setCurrentPage(currentPage);
			// 기본값
			filterPagination.setPerPageNum(8);
			
			// 필터 정보
			String kind = request.getParameter("kind");
			kind = kind != null && !kind.equals("undefined") ? kind : null;
			filterPagination.setKind(kind);
			
			Integer regionId = null;
			String regionIdString = request.getParameter("regionId");
			if (regionIdString != null && !regionIdString.equals("undefined")) {
				regionId = Integer.parseInt(regionIdString);
			}
			filterPagination.setRegionId(regionId);
			
			Integer minAlcohol = null;
			String minAlcoholString = request.getParameter("minAlcohol");
			if (minAlcoholString != null && !minAlcoholString.equals("undefined")) {
				minAlcohol = Integer.parseInt(minAlcoholString);
			}
			filterPagination.setMinAlcohol(minAlcohol);
			
			Integer maxAlcohol = null;
			String maxAlcoholString = request.getParameter("maxAlcohol");
			if (maxAlcoholString != null && !minAlcoholString.equals("undefined")) {
				maxAlcohol = Integer.parseInt(maxAlcoholString);
			}
			filterPagination.setMaxAlcohol(maxAlcohol);
			
			Integer minSugarContent = null;
			String minSugarContentString = request.getParameter("minSugarContent");
			if (minSugarContentString != null && !minSugarContentString.equals("undefined")) {
				minSugarContent = Integer.parseInt(minSugarContentString);
			}
			filterPagination.setMinSugarContent(minSugarContent);
			
			Integer maxSugarContent = null;
			String maxSugarContentString = request.getParameter("maxSugarContent");
			if (maxSugarContentString != null && !maxSugarContentString.equals("undefined")) {
				maxSugarContent = Integer.parseInt(maxSugarContentString);
			}
			filterPagination.setMaxSugarContent(maxSugarContent);
			
			Integer minBody = null;
			String minBodyString = request.getParameter("minBody");
			if (minBodyString != null && !minBodyString.equals("undefined")) {
				minBody = Integer.parseInt(minBodyString);
			}
			filterPagination.setMinBody(minBody);
			
			Integer maxBody = null;
			String maxBodyString = request.getParameter("maxBody");
			if (maxBodyString != null && !maxBodyString.equals("undefined")) {
				maxBody = Integer.parseInt(maxBodyString);
			}
			filterPagination.setMaxBody(maxBody);
			
			Integer minPrice = null;
			String minPriceString = request.getParameter("minPrice");
			if (minPriceString != null && !minPriceString.equals("undefined")) {
				minPrice = Integer.parseInt(minPriceString);
			}
			filterPagination.setMinPrice(minPrice);
			
			Integer maxPrice = null;
			String maxPriceString = request.getParameter("maxPrice");
			if (maxPriceString != null && !maxPriceString.equals("undefined")) {
				maxPrice = Integer.parseInt(maxPriceString);
			}
			filterPagination.setMaxPrice(maxPrice);
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
