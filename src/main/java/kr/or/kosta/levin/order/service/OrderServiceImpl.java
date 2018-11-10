package kr.or.kosta.levin.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.product.dao.ProductDao;
import kr.or.kosta.levin.product.domain.PaginationManager;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * Product와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Service)
public class OrderServiceImpl implements OrderService {

	// dao 선언
	@Inject
	private ProductDao productDao;
	
	public ProductDao getProductDao() {
		return productDao;
	}

	// dao getter/setter
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 상품 목록
	@Override
	public Map<String, Object> list(SearchPagination searchPagination) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		
		//페이징, 검색 처리된 상품 목록
		List<Product> list = productDao.listByPage(searchPagination);
		//검색해온 상품 목록 갯수
		int count = productDao.countBySearch(searchPagination);
		//페이징 관련정보(시작, 끝 페이지 등) 처리 
		PaginationManager pm = new PaginationManager();
		pm.setPagination(searchPagination);
		pm.setTotalCount(count);
		
		
		// controller로 넘겨 주기 위해 map에 담아주기
		map.put("productList", list);
		map.put("pageInfo", pm.pageInfo());
		
		if(searchPagination.getSearchKeyword() != null) {
			map.put("searchPagination", searchPagination);
		}
		return map;
	}

	// 상품 상세 보기
	@Override
	public Product detailProduct(String productId) throws Exception {
		return productDao.getProduct(productId);
	}

}
