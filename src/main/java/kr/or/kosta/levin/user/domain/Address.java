package kr.or.kosta.levin.user.domain;

/**
 * User의 주소 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class Address {
	
	private String addressId;
	private String email;
	private String address;
	private String zipCode;
	private String detailedAddress;
	private String regdate;
	private String delyn;
	
	// 기본 생성자
	public Address() {
		super();
		
	}
	// 매개변수 있는 생성자
	public Address(String addressId, String email, String address, String zipCode, String detailedAddress,
			String regdate, String delyn) {
		
		this.addressId = addressId;
		this.email = email;
		this.address = address;
		this.zipCode = zipCode;
		this.detailedAddress = detailedAddress;
		this.regdate = regdate;
		this.delyn = delyn;
	}
	
	//getter/setter
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getDelyn() {
		return delyn;
	}
	public void setDelyn(String delyn) {
		this.delyn = delyn;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", email=" + email + ", address=" + address + ", zipCode=" + zipCode
				+ ", detailedAddress=" + detailedAddress + ", regdate=" + regdate + ", delyn=" + delyn + "]";
	}
	
	/** 도메인 객체 값의 null을 체크하기 위한 함수*/
	public boolean checkNull() {
		
		if(email.trim().length() == 0 || email == null
				|| address.trim().length() == 0 || address == null
				|| detailedAddress.trim().length() == 0 || detailedAddress == null
				|| zipCode.trim().length() == 0 || zipCode == null) {
			return false;
		}
		return true;
	}
	
}
