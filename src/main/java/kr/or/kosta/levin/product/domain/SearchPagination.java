package kr.or.kosta.levin.product.domain;

public class SearchPagination extends Pagination {

	// 인스턴스 변수
	private String searchType;
	private String searchKeyword;
	
	// getter/setter 메소드
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	@Override
	public String toString() {
		return "SearchPagination [searchType=" + searchType + ", searchKeyword=" + searchKeyword + "]";
	}
	
}
