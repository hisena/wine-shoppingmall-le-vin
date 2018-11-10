package kr.or.kosta.levin.order.domain;

/**
 * OrderList(주문 목록)정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class OrderList {
	
	private String orderId;
	private String productId;
	private String quantity;
	// 기본 생성자
	public OrderList() {
		super();
		
	}
	// 매개변수 있는 생성자
	public OrderList(String orderId, String productId, String quantity) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	// getter/setter
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderList [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	
	

}
