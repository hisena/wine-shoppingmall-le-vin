package kr.or.kosta.levin.product.service;

import java.util.List;
import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.product.dao.ProductDao;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * Product와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * 
 * @author 류세은, 박소연
 *
 */
@Bean(type = BeanType.Service)
public class ProductServiceImpl implements ProductService {

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
	public List<Product> list(SearchPagination searchPagination) throws Exception {
		
		return productDao.listByPage(searchPagination);
	}

}
