package kr.or.kosta.levin.order.domain;

/**
 * Delivery(배송)정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class Delivery {
	
	private int order_id;
	private int address_id;
	private String orderId;
	private String addressId;
	private String receiverName;
	private String receiverMobile;
	private String deliveryComments;
	private String completeDate;
	private String startDate;
	private String newTF;
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Delivery(int order_id, int address_id, String orderId, String addressId, String receiverName,
			String receiverMobile, String deliveryComments, String completeDate, String startDate, String newTF) {
		super();
		this.order_id = order_id;
		this.address_id = address_id;
		this.orderId = orderId;
		this.addressId = addressId;
		this.receiverName = receiverName;
		this.receiverMobile = receiverMobile;
		this.deliveryComments = deliveryComments;
		this.completeDate = completeDate;
		this.startDate = startDate;
		this.newTF = newTF;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getAddress_id() {
		return address_id;
	}
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getDeliveryComments() {
		return deliveryComments;
	}
	public void setDeliveryComments(String deliveryComments) {
		this.deliveryComments = deliveryComments;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getNewTF() {
		return newTF;
	}
	public void setNewTF(String newTF) {
		this.newTF = newTF;
	}
	@Override
	public String toString() {
		return "Delivery [order_id=" + order_id + ", address_id=" + address_id + ", orderId=" + orderId + ", addressId="
				+ addressId + ", receiverName=" + receiverName + ", receiverMobile=" + receiverMobile
				+ ", deliveryComments=" + deliveryComments + ", completeDate=" + completeDate + ", startDate="
				+ startDate + ", newTF=" + newTF + "]";
	}
	
	
}
