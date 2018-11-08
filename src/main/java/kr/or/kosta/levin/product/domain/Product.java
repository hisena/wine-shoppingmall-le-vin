package kr.or.kosta.levin.product.domain;

/**
 * Product의 데이터 전달을 위한 도메인 객체
 * @author 류세은, 박소연
 *
 */
public class Product {
	
	private String productId;
	private String productNameKor;
	private String productBameEng;
	private String alcohol;
	private String kind;
	private String sugarContent;
	private String body;
	private String vintage;
	private String grapeKind;
	private String totalQuantity;
	private String winery;
	private String regionId;
	private String regionName;
	private String price;
	
	// 기본 생성자
	public Product() {
		super();
	}
	
	// 매개변수 있는 생성자
	public Product(String productId, String productNameKor, String productBameEng, String alcohol, String kind,
			String sugarContent, String body, String vintage, String grapeKind, String totalQuantity, String winery,
			String regionId, String regionName, String price) {
		super();
		this.productId = productId;
		this.productNameKor = productNameKor;
		this.productBameEng = productBameEng;
		this.alcohol = alcohol;
		this.kind = kind;
		this.sugarContent = sugarContent;
		this.body = body;
		this.vintage = vintage;
		this.grapeKind = grapeKind;
		this.totalQuantity = totalQuantity;
		this.winery = winery;
		this.regionId = regionId;
		this.regionName = regionName;
		this.price = price;
	}
	
	// getter/setter
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductNameKor() {
		return productNameKor;
	}
	public void setProductNameKor(String productNameKor) {
		this.productNameKor = productNameKor;
	}
	public String getProductBameEng() {
		return productBameEng;
	}
	public void setProductBameEng(String productBameEng) {
		this.productBameEng = productBameEng;
	}
	public String getAlcohol() {
		return alcohol;
	}
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSugarContent() {
		return sugarContent;
	}
	public void setSugarContent(String sugarContent) {
		this.sugarContent = sugarContent;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getVintage() {
		return vintage;
	}
	public void setVintage(String vintage) {
		this.vintage = vintage;
	}
	public String getGrapeKind() {
		return grapeKind;
	}
	public void setGrapeKind(String grapeKind) {
		this.grapeKind = grapeKind;
	}
	public String getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getWinery() {
		return winery;
	}
	public void setWinery(String winery) {
		this.winery = winery;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productNameKor=" + productNameKor + ", productBameEng="
				+ productBameEng + ", alcohol=" + alcohol + ", kind=" + kind + ", sugarContent=" + sugarContent
				+ ", body=" + body + ", vintage=" + vintage + ", grapeKind=" + grapeKind + ", totalQuantity="
				+ totalQuantity + ", winery=" + winery + ", regionId=" + regionId + ", regionName=" + regionName
				+ ", price=" + price + "]";
	}
	
}
