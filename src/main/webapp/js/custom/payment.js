/**
 * 장바구니에서 구매로 이동하는 처리
 * 
 * @author 이승은
 */

function goToPurchasePage(id, items){
	route(event, id, '.fixed__footer', function(data){
		
		// 로그인 정보 표시
		getLoginInfo();
		
		// 로그인 안 했을 경우
		var loginUser = getCookie('email');
		if (loginUser.length <= 0) {
			$('.wc-proceed-to-checkout').html('로그인 후 이용 부탁드립니다.');
		}
		
		$('.table-responsive tbody').html('');

		for ( var index in items) {
			var item = items[index];
			var subTotal = getTotalPrice(item[cartIndexes.indexOf('quantity')], item[cartIndexes.indexOf('price')]);
			item.push(subTotal.toLocaleString());
			var itemToAppend = 
				'<tr class="product">' +
				'<input type="hidden" id="productId" value="###' + cartIndexes.indexOf('productId') +'###" >' +
				'<td class="product-thumbnail">' +
				'<img src="###' + cartIndexes.indexOf('imagePath') + '###" alt="product img" />' + 
				'</td>' + 
				'<td class="product-name">' + 
				'###' + cartIndexes.indexOf('productName') + '###' + 
				'</td>' +
				'<td class="product-price">' + 
				'<span class="amount">###' + cartIndexes.indexOf('price') + '###</span>' + 
				'</td>' + 
				'<td class="product-quantity">' + 
				'<input type="number" min="1" value="###' + cartIndexes.indexOf('quantity') + '###" max="###' + cartIndexes.indexOf('maxQuantity') + '###"/>' + 
				'</td>' +
				'<td class="product-subtotal">###' + cartIndexes.length + '###원</td>' +
				'<td class="product-remove"><a href="#">X</a></td>' +
				'</tr>';
			for (var i = 0; i < item.length + 1; i++) {
				
				var regExp = new RegExp('###' + i + '###', 'gm');
				itemToAppend = itemToAppend.replace(regExp, item[i]);
			}
			$('.table-responsive tbody').append(itemToAppend);
		}
		printCartTotal();
	});
}

$(function(){

	// 목록에서 구매 버튼 클릭 시
	$(document).on('click', 'ul.product__action li:nth-child(2) a', function(event){
		event.preventDefault();
		
		var targetProduct = $(this).parents('div.product');
		var item = createCartItemFromTags(targetProduct, 'list');
		
		// 최대 수량보다 장바구니 수량이 큰 경우 입력 방지
		var quantityIndex = cartIndexes.indexOf('quantity');
		var originalItem = getItemFromCookieString(getCookie('cart'), item[cartIndexes.indexOf('productId')]);
		var originalQuantity = 0;
		if (originalItem) {
			originalQuantity = parseInt(originalItem[quantityIndex]);
		}

		if (item[cartIndexes.indexOf('maxQuantity')] < (originalQuantity + parseInt(item[quantityIndex]))) {
			snackbar('재고가 부족합니다.');
		} else {
			goToPurchasePage('#' + $(this).attr('id'), [item]);
		}
	});
	
	$(document).on('click', '.addtocart-btn a:first-child', function(event){
		
		// 상세 보기에서 장바구니 추가 버튼 클릭 시
		var targetProduct = $('.modal-product');
		var item = createCartItemFromTags(targetProduct, 'detail');
		
		var quantityIndex = cartIndexes.indexOf('quantity');
		var itemQuantity = item[quantityIndex];
		var originalItem = getItemFromCookieString(getCookie('cart'), item[cartIndexes.indexOf('productId')]);
		var originalQuantity = 0;
		if (originalItem) {
			originalQuantity = parseInt(originalItem[quantityIndex]);
		}
		if (!itemQuantity || itemQuantity < 1) {
			snackbar('상품 수량을 입력해주세요.');
			return false;
		} else if (item[cartIndexes.indexOf('maxQuantity')] < (originalQuantity + parseInt(item[quantityIndex]))) {
			snackbar('재고가 부족합니다.');
			return false;
		} else {
			goToPurchasePage('#' + $(this).attr('id'), [item]);
		}
			
	});
	
	$('#goToPurchase').click(function(event){
  		var cartItems = [];
  		
  		// 장바구니 내 모든 아이템을 javascript 처리 가능한 객체로 변환
  		var targetItems = $('.shopping__cart .shp__single__product');
		for (var i = 0; i < targetItems.length; i++) {
			var targetItem = targetItems[i];
			cartItems.push(createCartItemFromTags(targetItem, 'cart'));
		}
      		
		goToPurchasePage('#' + $(this).attr('id'), cartItems);
		
	});
});