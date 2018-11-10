package kr.or.kosta.levin.order.domain;

import java.util.List;

/**
 * OrderList(주문 목록)정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class OrderList {
	
	private String orderId;
	private List<String> productId;
	private List<String> quantity;
	// 기본 생성자
	public OrderList() {
		super();
		
	}
	public OrderList(String orderId, List<String> productId, List<String> quantity) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<String> getProductId() {
		return productId;
	}
	public void setProductId(List<String> productId) {
		this.productId = productId;
	}
	public List<String> getQuantity() {
		return quantity;
	}
	public void setQuantity(List<String> quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderList [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	
	
	

}
