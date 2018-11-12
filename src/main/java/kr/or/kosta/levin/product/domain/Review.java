package kr.or.kosta.levin.product.domain;

/**
 * Review의 데이터 전달을 위한 도메인 객체
 * 
 * @author 류세은
 *
 */
public class Review {

	private int reviewId;
	private int productId;
	private String email;
	private String title;
	private String content;
	private String grade;
	private String regdate;
	private String deleteYN;

	/** 디폴트 생성자 */
	public Review() {
		super();
	}

	/** 매개변수 있는 생성자 */
	public Review(int reviewId, int productId, String email, String title, String content, String grade, String regdate,
			String deleteYN) {
		super();
		this.reviewId = reviewId;
		this.productId = productId;
		this.email = email;
		this.title = title;
		this.content = content;
		this.grade = grade;
		this.regdate = regdate;
		this.deleteYN = deleteYN;
	}

	/** getter/setter */
	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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
		return "Review [reviewId=" + reviewId + ", productId=" + productId + ", email=" + email + ", title=" + title
				+ ", content=" + content + ", grade=" + grade + ", regdate=" + regdate + ", deleteYN=" + deleteYN + "]";
	}

	// Review 속성값 null 체크
	public boolean checkNull(Review review) {

		// 속성값 중 하나라도 null이거나 공백값이라면 - false
		if (review.getEmail() == null || review.getTitle() == null || review.getContent() == null
				|| review.getEmail().trim().length() == 0 || review.getTitle().trim().length() == 0
				|| review.getContent().trim().length() == 0) {
			return false;
		}
		// 속성값 모두 값이 존재하면- true
		return true;
	}

}
