package kr.or.kosta.levin.product.domain;

public class Pagination {
	
	// 인스턴스 변수
	private int currentPage;
	private int perPageNum;
	
	// 생성자
	public Pagination() {
		
		this.currentPage = 1;
		this.perPageNum = 8;
	}

	// getter/setter 메소드
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		
		if (currentPage <= 0) {
			this.currentPage = 1;
			return;
		}
		
		this.currentPage = currentPage;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	// sql에 사용할 메소드
	public int getStartPage() {
		return (this.currentPage - 1) * perPageNum + 1;
	}
	
	public int getEndPage () {
		return this.currentPage * perPageNum;
	}
	
	@Override
	public String toString() {
		return "Pagination [currentPage=" + currentPage + ", perPageNum=" + perPageNum + "]";
	}
	
}
