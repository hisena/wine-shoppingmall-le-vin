package kr.or.kosta.levin.product.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestException;
import kr.or.kosta.levin.product.service.ProductService;

@Bean(type=BeanType.Controller)
@RequestMapping(value="/product/filter/range-get")
public class FilterRangeGetterController implements Controller{
	
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
			return productService.initiateFilterValues();
		} catch (Exception e) {
			throw new ServletException("product/FilterRangeGetterController 예외", e);
		}
	}
}
