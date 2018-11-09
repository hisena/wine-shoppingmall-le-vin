/**
 * 와인 상품을 동적으로 추가할 수 있게 만드는 자바 스크립트
 * 
 * @author 김홍기
 */
function printItems() {
	var String = '<div class="col-md-3  col-lg-3 col-sm-4 col-xs-12 cat--1 single__pro">'
		+ '<div class="product foo">'
           + '<div class="product__inner">'
             + '<div class="pro__thumb">'
               + '<a href="#">'
                 + '<img src="images/product/1.png" alt="product images">'
               + '</a>'
             + '</div>'
             + '<div class="product__hover__info">'
               + '<ul class="product__action">'
                 + '<li><a data-toggle="modal" data-target="#productModal" title="Quick View" class="quick-view modal-view detail-link" href="#"><span class="ti-plus"></span></a></li>'
                 + '<li><a title="구매하기" href="cart.html"><span class="ti-shopping-cart"></span></a></li>'
                 + '<li><a title="장바구니 추가" href=""><span class="ti-heart"></span></a></li>'
               + '</ul>'
             + '</div>'
           + '</div>'
           + '<div class="product__details">'
             + '<h2><a href="product-details.html">상품이름</a></h2>'
             + '<ul class="product__price">'
               + '<li class="new__price">가격</li>'
             + '</ul>'
           + '</div>'
         + '</div>'
       + '</div>';
	
	for (var i = 0; i < 8; i++) {
		$('#product').append(String);
	}
}