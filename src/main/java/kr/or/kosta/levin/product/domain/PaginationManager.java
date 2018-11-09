package kr.or.kosta.levin.product.domain;

/**
 * 페이징 정보 처리를 위한 도메인
 * 
 * @author 박소연
 *
 */
public class PaginationManager {

	// 인스턴스 변수
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 5;

	private Pagination pagination;
	
	// getter/setter 메소드
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calculateData();
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Pagination getPagination() {
		return pagination;
	}

	// 시작페이지, 끝 페이지, 맨앞으로, 맨뒤로 기능을 위해 계산처리하는 메소드
	private void calculateData() {
		
		endPage = (int) (Math.ceil(pagination.getCurrentPage() / (double) displayPageNum) * displayPageNum);
		
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pagination.getPerPageNum()));
		
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * pagination.getPerPageNum() >= totalCount ? false : true;
	}

	@Override
	public String toString() {
		return "PaginationManager [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", pagination="
				+ pagination + "]";
	}
	
	
}
