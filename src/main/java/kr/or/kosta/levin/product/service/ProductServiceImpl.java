package kr.or.kosta.levin.product.service;

import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.common.web.Params;
import kr.or.kosta.levin.product.dao.ProductDao;
import kr.or.kosta.levin.user.dao.AddressDao;
import kr.or.kosta.levin.user.dao.UserDao;
import kr.or.kosta.levin.user.domain.Address;
import kr.or.kosta.levin.user.domain.User;

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
	public List<User> list(Params params) throws Exception {
		return productDao.listByPage(params);
	}

}
