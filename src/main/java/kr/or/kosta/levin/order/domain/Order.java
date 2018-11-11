package kr.or.kosta.levin.order.domain;

/**
 * Order 정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class Order {
	
	private String orderId;
	private String email;
	private String orderMoney;
	private String orderDate;
	private String cancelDate;
	private String isPaid;
	
	
	// 기본 생성자
	public Order() {
		super();
		
	}
	// 매개변수 있는 생성자
	public Order(String orderId, String email, String orderMoney, String orderDate, String cancelDate, String isPaid) {
		super();
		this.orderId = orderId;
		this.email = email;
		this.orderMoney = orderMoney;
		this.orderDate = orderDate;
		this.cancelDate = cancelDate;
		this.isPaid = isPaid;
	}
	// getter/setter
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(String isPaid) {
		this.isPaid = isPaid;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", email=" + email + ", orderMoney=" + orderMoney + ", orderDate="
				+ orderDate + ", cancelDate=" + cancelDate + ", isPaid=" + isPaid + "]";
	}
	
	

}
