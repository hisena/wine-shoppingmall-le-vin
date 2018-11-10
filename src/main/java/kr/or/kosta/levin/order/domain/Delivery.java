package kr.or.kosta.levin.order.domain;

/**
 * Delivery(배송)정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class Delivery {
	
	private String orderId;
	private String productId;
	private String quantity;
	
	// 기본생성자
	public Delivery() {
		super();
		
	}
	// 매개변수 있는 생성자
	public Delivery(String orderId, String productId, String quantity) {
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
		return "Delivery [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + "]";
	}
}
