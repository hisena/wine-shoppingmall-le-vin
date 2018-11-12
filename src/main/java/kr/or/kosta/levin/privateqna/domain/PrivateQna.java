package kr.or.kosta.levin.privateqna.domain;

/**
 * PrivateQna의 데이터 전달을 위한 도메인 객체
 * 
 * @author 류세은
 *
 */
public class PrivateQna {

	private int articleId;
	private String email;
	private String category;
	private String title;
	private String content;
	private String regdate;
	private String deleteYN;

	/** 디폴트 생성자 */
	public PrivateQna() {
		super();
	}

	/** 매개변수 있는 생성자 */
	public PrivateQna(int articleId, String email, String category, String title, String content, String regdate,
			String deleteYN) {
		super();
		this.articleId = articleId;
		this.email = email;
		this.category = category;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.deleteYN = deleteYN;
	}

	/** getter/setter */
	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getDeleteYN() {
		return deleteYN;
	}

	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}

	/** toString 메소드 */
	@Override
	public String toString() {
		return "PrivateQna [articleId=" + articleId + ", email=" + email + ", category=" + category + ", title=" + title
				+ ", content=" + content + ", regdate=" + regdate + ", deleteYN=" + deleteYN + "]";
	}

	// privateQna 속성값 null 체크
	public boolean checkNull(PrivateQna privateQna) {

		// 속성값 중 하나라도 null이거나 공백값이라면 - false
		if (privateQna.getEmail() == null || privateQna.getCategory() == null || privateQna.getTitle() == null
				|| privateQna.getContent() == null || privateQna.getEmail().trim().length() == 0
				|| privateQna.getCategory().trim().length() == 0 || privateQna.getTitle().trim().length() == 0
				|| privateQna.getContent().trim().length() == 0) {
			return false;
		}
		// 속성값 모두 값이 존재하면- true
		return true;
	}

}
