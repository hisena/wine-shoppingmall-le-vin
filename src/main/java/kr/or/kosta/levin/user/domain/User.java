package kr.or.kosta.levin.user.domain;

/**
 * User 기본 정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class User {
	
	private String email;
	private String password;
	private String user_name;
	private String mobile;
	private String regdate;
	
	// 기본 생성자
	public User() {
		super();
	}
	
	// 매개 변수 있는 생성자
	public User(String email, String password, String user_name, String mobile, String regdate) {
		super();
		this.email = email;
		this.password = password;
		this.user_name = user_name;
		this.mobile = mobile;
		this.regdate = regdate;
	}
	
	// getter/setter
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", user_name=" + user_name + ", mobile=" + mobile
				+ ", regdate=" + regdate + "]";
	}
	
	
}
