package kr.or.kosta.levin.product.domain;

/**
 * ReviewComment의 데이터 전달을 위한 도메인 객체
 * 
 * @author 류세은
 *
 */
public class ReviewComment {
	
	private int commentId;
	private int productId;
	private String email;
	private String content;
	private String regdate;
	private String deleteYN;
	
	/** 디폴트 생성자 */
	public ReviewComment() {
		super();
	}

	/** 매개변수 있는 생성자 */
	public ReviewComment(int commentId, int productId, String email, String content, String regdate, String deleteYN) {
		this.commentId = commentId;
		this.productId = productId;
		this.email = email;
		this.content = content;
		this.regdate = regdate;
		this.deleteYN = deleteYN;
	}
	
	/** getter/setter */
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
		return "ReviewComment [commentId=" + commentId + ", productId=" + productId + ", email=" + email + ", content="
				+ content + ", regdate=" + regdate + ", deleteYN=" + deleteYN + "]";
	}

	// reviewComment 속성값 null 체크
	public boolean checkNull(ReviewComment reviewComment) {

		// 속성값 중 하나라도 null이거나 공백값이라면 - false
				if (reviewComment.getEmail() == null || reviewComment.getContent() == null
						|| reviewComment.getEmail().trim().length() == 0  
						|| reviewComment.getContent().trim().length() == 0) {
					return false;
				}
				// 속성값 모두 값이 존재하면- true
				return true;
			}

	

}
