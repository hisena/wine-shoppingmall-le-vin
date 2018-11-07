package kr.or.kosta.levin.user.domain;

/**
 * User기본 정보 관리를 위한 도메인 객체
 * @author 박소연
 *
 */
public class User {
	
	private String email;                                    
	private String password;                                  
	private String userName;                                 
	private String mobile;                                 
	private String regdate;
	
	// 기본생성자
	public User() {
		super();
	}
	
	// 매개변수 있는 생성자
	public User(String email, String password, String userName, String mobile, String regdate) {
		this.email = email;
		this.password = password;
		this.userName = userName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		return "User [email=" + email + ", password=" + password + ", userName=" + userName + ", mobile=" + mobile
				+ ", regdate=" + regdate + "]";
	}
	
	public boolean checkNull(User user) {
		
		if(user.getEmail() == null || user.getPassword() == null || user.getMobile() == null || user.getUserName() == null
				|| user.getEmail().trim().length() == 0 || user.getPassword().trim().length() == 0 
				|| user.getMobile().trim().length() == 0 || user.getUserName().trim().length() == 0 ) {
			return false;
		}
		return true;
	}
	
	
	
}
