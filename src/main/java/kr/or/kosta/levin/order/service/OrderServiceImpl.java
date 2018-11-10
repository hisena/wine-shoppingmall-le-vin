package kr.or.kosta.levin.order.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.order.dao.AddressDao;
import kr.or.kosta.levin.order.dao.DeliveryDao;
import kr.or.kosta.levin.order.dao.OrderDao;
import kr.or.kosta.levin.order.dao.OrderListDao;
import kr.or.kosta.levin.order.domain.Address;
import kr.or.kosta.levin.order.domain.Delivery;
import kr.or.kosta.levin.order.domain.Order;
import kr.or.kosta.levin.order.domain.OrderList;
import kr.or.kosta.levin.product.dao.ProductDao;
import kr.or.kosta.levin.product.domain.PaginationManager;
import kr.or.kosta.levin.product.domain.Product;
import kr.or.kosta.levin.product.domain.SearchPagination;

/**
 * 주문와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * 
 * @author 박소연
 *
 */
@Bean(type = BeanType.Service)
public class OrderServiceImpl implements OrderService {

	// dao 선언
	@Inject
	private	OrderDao orderDao;
	@Inject
	private	OrderListDao orderlistDao;
	@Inject
	private	DeliveryDao deliveryDao;
	@Inject
	private	AddressDao addressDao;
	
	
	public OrderListDao getOrderlistDao() {
		return orderlistDao;
	}
	public void setOrderlistDao(OrderListDao orderlistDao) {
		this.orderlistDao = orderlistDao;
	}
	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}
	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}
	public AddressDao getAddressDao() {
		return addressDao;
	}
	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	public OrderDao getOrderDao() {
		return orderDao;
	}
	// dao getter/setter
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 상품 목록
	@Override
	public Map<String, Object> list(SearchPagination searchPagination) throws Exception {
		
//		Map<String, Object> map = new HashMap<>();
//		
//		//페이징, 검색 처리된 상품 목록
//		List<Product> list = productDao.listByPage(searchPagination);
//		//검색해온 상품 목록 갯수
//		int count = productDao.countBySearch(searchPagination);
//		//페이징 관련정보(시작, 끝 페이지 등) 처리 
//		PaginationManager pm = new PaginationManager();
//		pm.setPagination(searchPagination);
//		pm.setTotalCount(count);
//		
//		
//		// controller로 넘겨 주기 위해 map에 담아주기
//		map.put("productList", list);
//		map.put("pageInfo", pm.pageInfo());
//		
//		if(searchPagination.getSearchKeyword() != null) {
//			map.put("searchPagination", searchPagination);
//		}
//		return map;
		return null;
	}

	// 상품 상세 보기
	@Override
	public Product detailProduct(String productId) throws Exception {
		//return productDao.getProduct(productId);
		return null;
	}

	@Override
	public boolean add(Order order, Delivery delivery, Address address, List<OrderList> productList) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
		boolean orderResult = false;
		boolean orderListResult = false;
		boolean deliveryResult = false;
		boolean addressResult= true;
		boolean flag = false;
		// 주문 정보 등록
		orderResult = orderDao.create(order);
		System.out.println("주문등록 완료");
		// 정보등록에 성공하면
		if (orderResult) {
			System.out.println("신규 배송지 등록 시작");
			// 신규배송지일 경우
			System.out.println(address.getAddressId());
			if(address.getAddress() != null) {
				addressResult = addressDao.create(address);
				delivery.setNewTF("New");
				System.out.println("신규배송지등록 완료");
			}
			
			// 배송 정보 등록
			deliveryResult = deliveryDao.create(delivery);
			
			System.out.println("배송정보 등록완료");
						
			// 상품정보 등록
			for (int i = 0; i < productList.size(); i++) {
				Map<String, String> productInfo = new HashMap<>();
				productInfo.put("productId", productList.get(i).getProductId());
				productInfo.put("quantity", productList.get(i).getQuantity());
				orderListResult = orderlistDao.create(productInfo);
				if(orderListResult == false) { break; }
			}
			
			System.out.println("상품정보등록 완료");
			
			if (orderListResult && deliveryResult && addressResult ) {
				flag = true;
			}
		}
		return flag;
	}

}
