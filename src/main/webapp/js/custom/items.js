/**
 * 와인 상품을 동적으로 추가할 수 있게 만드는 자바 스크립트
 * 
 * @author 김홍기
 */
// 상품 목록 페이지에서 동적 추가
function printItems(productId, productName, price, maxQUantity) {
	// 추가할 상품
	var String = '<div class="col-md-3  col-lg-3 col-sm-3 col-xs-12 single__pro">'
		       + '  <div class="product foo">'
		       + '	  <input type="hidden" id="maxQuantity" value="' + maxQUantity + '">'
               + '    <div class="product__inner">'
               + '      <div class="pro__thumb">'
               + '        <img src="' + Utils.getImagePath(productId) + '" alt="product images" class="wineImage">'
               + '      </div>'
               + '      <div class="product__hover__info">'
               + '        <ul class="product__action">'
               + '          <li><a data-toggle="modal" data-target="#productModal" title="상세보기" class="quick-view modal-view detail-link" href="' + productId + '"><span class="ti-plus"></span></a></li>'
               + '          <li><a title="구매하기" href="cart.html"><span class="ti-shopping-cart"></span></a></li>'
               + '          <li><a title="장바구니 추가" href=""><span class="ti-heart"></span></a></li>'
               + '        </ul>'
               + '      </div>'
               + '    </div>'
               + '    <div class="product__details">'
               + '      <h2>' + productName + '</h2>'
               + '      <ul class="product__price">'
               + '        <li class="new__price">' + Number(price).toLocaleString('en') + '원' + '</li>'
               + '      </ul>'
               + '    </div>'
               + '  </div>'
               + '</div>';

	$('#product').append(String);
}



// 메인페이지에서 동적 추가
function printMainItems(productId, productName, price, id, maxQUantity) {
    // 추가할 상품
	var String = '<div class="col-md-4 single__pro col-lg-4 col-sm-4 col-xs-12">'
	           + '  <div class="product">'
	           + '	  <input type="hidden" id="maxQuantity" value="' + maxQUantity + '">'
	           + '    <div class="product__inner">'
	           + '      <div class="pro__thumb">'
	           + '        <img src="' + Utils.getImagePath(productId) + '" alt="product images" class="wineImage">'
	           + '      </div>'
	           + '      <div class="product__hover__info">'
	           + '        <ul class="product__action">'
	           + '          <li><a data-toggle="modal" data-target="#productModal" title="상세보기" class="quick-view modal-view detail-link" href="' + productId + '"><span class="ti-plus"></span></a></li>'
	           + '          <li><a title="구매하기" href="cart.html"><span class="ti-shopping-cart"></span></a></li>'
	           + '          <li><a title="장바구니 추가" href=""><span class="ti-heart"></span></a></li>'
	           + '        </ul>'
	           + '      </div>'
	           + '    </div>'
	           + '    <div class="product__details">'
	           + '      <h2>' + productName + '</h2>'
	           + '      <ul class="product__price">'
	           + '        <li class="new__price">' + Number(price).toLocaleString('en') + '원' + '</li>'
	           + '      </ul>'
	           + '    </div>'
	           + '  </div>'
	           + '</div>';
	
	$("#" + id).append(String);
}