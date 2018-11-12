package kr.or.kosta.levin.privateqna.domain;

/**
 * PrivateQnaComment의 데이터 전달을 위한 도메인 객체
 * 
 * @author 류세은
 *
 */
public class PrivateQnaComment {

	private int commentId;
	private String email;
	private String category;
	private String content;
	private String regdate;
	private char deleteYN;

	/** 디폴트 생성자 */
	public PrivateQnaComment() {
		super();
	}

	/** 매개변수 있는 생성자 */
	public PrivateQnaComment(int commentId, String email, String category, String content, String regdate,
			char deleteYN) {
		super();
		this.commentId = commentId;
		this.email = email;
		this.category = category;
		this.content = content;
		this.regdate = regdate;
		this.deleteYN = deleteYN;
	}

	/** getter/setter */

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public char getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(char deleteYN) {
		this.deleteYN = deleteYN;
	}

	/** toString 메소드 */
	@Override
	public String toString() {
		return "PrivateQnaComment [commentId=" + commentId + ", email=" + email + ", category=" + category
				+ ", content=" + content + ", regdate=" + regdate + ", deleteYN=" + deleteYN + "]";
	}

	// privateQna 속성값 null 체크
	public boolean checkNull(PrivateQnaComment privateQnaComment) {

		// 속성값 중 하나라도 null이거나 공백값이라면 - false
		if (privateQnaComment.getEmail() == null || privateQnaComment.getCategory() == null
				|| privateQnaComment.getContent() == null || privateQnaComment.getEmail().trim().length() == 0
				|| privateQnaComment.getCategory().trim().length() == 0
				|| privateQnaComment.getContent().trim().length() == 0) {
			return false;
		}
		// 속성값 모두 값이 존재하면- true
		return true;
	}

}
