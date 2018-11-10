package kr.or.kosta.levin.order.domain;

/**
 * Delivery(배송)정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class Delivery {
	
	private String orderId;
	private String addressId;
	private String receiverName;
	private String receiverMobile;
	private String deliveryComments;
	private String completeDate;
	private String startDate;
	
	// 기본 생성자
	public Delivery() {
		super();
	}

	// 매개변수 있는 생성자
	public Delivery(String orderId, String addressId, String receiverName, String receiverMobile,
			String deliveryComments, String completeDate, String startDate) {
		super();
		this.orderId = orderId;
		this.addressId = addressId;
		this.receiverName = receiverName;
		this.receiverMobile = receiverMobile;
		this.deliveryComments = deliveryComments;
		this.completeDate = completeDate;
		this.startDate = startDate;
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

	@Override
	public String toString() {
		return "Delivery [orderId=" + orderId + ", addressId=" + addressId + ", receiverName=" + receiverName
				+ ", receiverMobile=" + receiverMobile + ", deliveryComments=" + deliveryComments + ", completeDate="
				+ completeDate + ", startDate=" + startDate + "]";
	}
}
