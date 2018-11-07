package kr.or.kosta.levin.user.domain;

public class Address{
	
	private String addressId;
	private String email;
	private String address;
	private String zipCode;
	private String detailedAddress;
	private String regdate;
	private String delyn;
	public Address() {
		super();
	}
	public Address(String addressId, String email, String address, String zipCode, String detailedAddress,
			String regdate, String delyn) {
		super();
		this.addressId = addressId;
		this.email = email;
		this.address = address;
		this.zipCode = zipCode;
		this.detailedAddress = detailedAddress;
		this.regdate = regdate;
		this.delyn = delyn;
	}
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
}
