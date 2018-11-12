package kr.or.kosta.levin.product.domain;

/**
 * 상품 문의 댓글 테이블의 데이터를 관리하기 위한 도메인 객체
 * @author 박소연
 *
 */
public class ProductQnaComment {

	private String commentId;
	private String productId;
	private String writer;
	private String title;
	private String content;
	private String regdate;
	private String deleteYn;
	private String privateYn;
	// 기본 생성자
	public ProductQnaComment() {
		super();
		
	}
	// 매개변수 있는 생성자
	public ProductQnaComment(String commentId, String productId, String writer, String title, String content,
			String regdate, String deleteYn, String privateYn) {
		super();
		this.commentId = commentId;
		this.productId = productId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.deleteYn = deleteYn;
		this.privateYn = privateYn;
	}
	// getter/setter
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public String getPrivateYn() {
		return privateYn;
	}
	public void setPrivateYn(String privateYn) {
		this.privateYn = privateYn;
	}
	@Override
	public String toString() {
		return "ProductQnaComment [commentId=" + commentId + ", productId=" + productId + ", writer=" + writer
				+ ", title=" + title + ", content=" + content + ", regdate=" + regdate + ", deleteYn=" + deleteYn
				+ ", privateYn=" + privateYn + "]";
	}
	
	
}
