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
	private String kind;
	private Integer regionId;
	private Integer maxAlcohol;
	private Integer minAlcohol;
	private Integer maxSugarContent;
	private Integer minSugarContent;
	private Integer maxBody;
	private Integer minBody;
	private Integer maxPrice;
	private Integer minPrice;
	
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
	
	public Integer getMaxAlcohol() {
		return maxAlcohol;
	}
	
	public void setMaxAlcohol(Integer maxAlcohol) {
		this.maxAlcohol = maxAlcohol;
	}
	
	public Integer getMinAlcohol() {
		return minAlcohol;
	}
	
	public void setMinAlcohol(Integer minAlcohol) {
		this.minAlcohol = minAlcohol;
	}
	
	public Integer getMaxSugarContent() {
		return maxSugarContent;
	}
	
	public void setMaxSugarContent(Integer maxSugarContent) {
		this.maxSugarContent = maxSugarContent;
	}
	
	public Integer getMinSugarContent() {
		return minSugarContent;
	}
	
	public void setMinSugarContent(Integer minSugarContent) {
		this.minSugarContent = minSugarContent;
	}
	
	public Integer getMaxBody() {
		return maxBody;
	}
	
	public void setMaxBody(Integer maxBody) {
		this.maxBody = maxBody;
	}
	
	public Integer getMinBody() {
		return minBody;
	}
	
	public void setMinBody(Integer minBody) {
		this.minBody = minBody;
	}
	
	public Integer getMaxPrice() {
		return maxPrice;
	}
	
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
	
	public Integer getMinPrice() {
		return minPrice;
	}
	
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	
	// 유효성을 검사하는 메소드
	public boolean hasAllNeccessaryVariables() {
		return this.maxAlcohol != null
				&& this.minAlcohol != null
				&& this.maxSugarContent != null
				&& this.minSugarContent != null
				&& this.maxBody != null
				&& this.minBody != null
				&& this.minPrice != null
				&& this.maxPrice != null;
	}

	@Override
	public String toString() {
		return "FilterPagination [kind=" + kind + ", regionId=" + regionId + ", maxAlcohol=" + maxAlcohol
				+ ", minAlcohol=" + minAlcohol + ", maxSugarContent=" + maxSugarContent + ", minSugarContent="
				+ minSugarContent + ", maxBody=" + maxBody + ", minBody=" + minBody + ", maxPrice=" + maxPrice
				+ ", minPrice=" + minPrice + "]";
	}
	
}
