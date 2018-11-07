package kr.or.kosta.levin.user.domain;

/**
 * 이메일 인증을 위한 도메인 객체
 * @author 박소연
 *
 */
public class EmailVali {
	
	private String email;
	private String valiNumber;
	private String isValidated;
	private String createdDate;
	private String type;
	
	// 기본 생성자
	public EmailVali() {
		super();
	}
	
	// 매개변수 있는 생성자
	public EmailVali(String email, String valiNumber, String isValidated, String createdDate, String type) {
		super();
		this.email = email;
		this.valiNumber = valiNumber;
		this.isValidated = isValidated;
		this.createdDate = createdDate;
		this.type = type;
	}
	
	// getter/setter
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getValiNumber() {
		return valiNumber;
	}
	public void setValiNumber(String valiNumber) {
		this.valiNumber = valiNumber;
	}
	public String getIsValidated() {
		return isValidated;
	}
	public void setIsValidated(String isValidated) {
		this.isValidated = isValidated;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "EmailVali [email=" + email + ", valiNumber=" + valiNumber + ", isValidated=" + isValidated
				+ ", createdDate=" + createdDate + ", type=" + type + "]";
	}
}
