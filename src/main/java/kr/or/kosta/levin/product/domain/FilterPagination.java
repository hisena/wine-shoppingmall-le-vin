package kr.or.kosta.levin.product.domain;

import java.util.Map;

import kr.or.kosta.levin.common.domain.Pagination;

/**
 * 
 * 
 * @author 이승은
 *
 */
public class FilterPagination extends Pagination{
	
	// 인스턴스 변수
	String kind;
	Integer regionId;
	Map<String, String> alcohol;
	Map<String, Integer> sugarContent;
	Map<String, Integer> body;
	Map<String, Integer> price;
	
	// getter / setter
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	public Map<String, String> getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(Map<String, String> alcohol) {
		this.alcohol = alcohol;
	}
	public Map<String, Integer> getSugarContent() {
		return sugarContent;
	}
	public void setSugarContent(Map<String, Integer> sugarContent) {
		this.sugarContent = sugarContent;
	}
	public Map<String, Integer> getBody() {
		return body;
	}
	public void setBody(Map<String, Integer> body) {
		this.body = body;
	}
	public Map<String, Integer> getPrice() {
		return price;
	}
	public void setPrice(Map<String, Integer> price) {
		this.price = price;
	}
	
	// 유효성을 검사하는 메소드
	public boolean hasAllNeccessaryVariables() {
		return this.alcohol != null
				&& this.sugarContent != null
				&& this.body != null
				&& this.price != null;
	}
	
	@Override
	public String toString() {
		return "FilterPagination [kind=" + kind + ", regionId=" + regionId + ", alcohol=" + alcohol + ", sugarContent="
				+ sugarContent + ", body=" + body + ", price=" + price + "]";
	}
	
}
