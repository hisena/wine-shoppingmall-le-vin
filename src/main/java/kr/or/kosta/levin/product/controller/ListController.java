package kr.or.kosta.levin.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.tools.web.BadHttpRequest;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.annotations.RequestMapping;
import io.github.leeseungeun.webframework.controller.Controller;
import io.github.leeseungeun.webframework.enums.BeanType;
import io.github.leeseungeun.webframework.exception.RequestBadRequestException;
import io.github.leeseungeun.webframework.exception.RequestException;
import io.github.leeseungeun.webframework.exception.RequestUnauthorizedException;
import kr.or.kosta.levin.common.web.Params;
import kr.or.kosta.levin.product.service.ProductService;
import kr.or.kosta.levin.user.domain.User;
import kr.or.kosta.levin.user.service.UserService;

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
		String searchValue = request.getParameter("searchValue");
		int page = Integer.parseInt(request.getParameter("currentPage"));

		Params params = new Params();
		Map<String, String> map = new HashMap<String, String>();
		//List<Product>

		// params 객체에 클라이언트로부터 받은 정보 등록
		params.setSearchValue(searchValue);
		params.setPage(page);

		try {
			// 파라미터값 null 유효성 검사
			if (params.getSearchValue() != null && params.getPage() != 0
					&& params.getSearchValue().trim().length() != 0) { // user 객체에 올바른 값이 들어오는 경우 - 서비스 메소드 실행
				//changeResult = userService.changeInfo(user);
//				if (changeResult) { // 회원정보 수정에 성공했을 경우 - 클라이언트에게 changeResult : true 반환
//					map.put("changeResult", "true");
//				} else {
//					// 실패했을 경우 - 클라이언트에게 changeResult : false 반환
//					map.put("changeResult", "false");
//				}
				return map;
			} else {
				// user 객체의 속성값으로 null이나 공백값이 들어왔을 경우 - 400(bad request) 에러 발생
				throw new RequestBadRequestException();
			}
		} catch (Exception e) {
			throw new ServletException("userService.changeResult() 예외 발생", e);
		}
	}
}
