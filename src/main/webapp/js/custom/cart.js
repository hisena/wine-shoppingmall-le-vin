/**
 * 장바구니 관련 처리를 담당하는 자바 스크립트 
 */

// 장바구니 상품 (배열)의 각 인덱스의 의미
var cartIndexes = ['productId', 'productName', 'quantity', 'price', 'imagePath'];
// 장바구니에서 상품을 구분할 때 사용하는 구분자
var cartItemDelimiter = '-';
// 상품 정보를 구분할 때 사용하는 구분자
var itemInfoDelimiter = '+';
	
// 상품 정보를 문자열로 만들어주는 함수
function createItemString(cartItem) {
	
	var result = '';
	
	for ( var key in cartItem) {
		result += (cartItem[key] + itemInfoDelimiter);
	}
	
	// 마지막 구분자를 삭제해주기 위해 substr 사용
	return result.substr(0, result.length - 1);
}
// 문자열을 상품 정보로 바꿔주는 함수
function convertStringToItem(cartItemString) {
	return cartItemString.split(itemInfoDelimiter);
}
// 상품 고유 번호를 이용해 장바구니에 해당 상품을 가져옴
function getItemFromCookieString(cookieString, productId) {
	
	if (cookieString.length > 0) {
		// 장바구니 안의 모든 상품을 순회하며 추가하는 상품과 일치하는 상품이 있는지 확인
		var cartItems = cookieString.split(cartItemDelimiter);
		for ( var index in cartItems) {
			var item = convertStringToItem(cartItems[index]);
			
			var cartProductIdIndex = cartIndexes.indexOf('productId');
			if (cartItem[cartProductIdIndex] == item[cartProductIdIndex]) {
				return item;
			}
		}
	}
}
// 장바구니에 상품을 추가하는 함수
function addItemToCart(cartItem) {
	
	var value = getCookie('cart');
	
	// 추가하고자 하는 상품이 장바구니에 존재하는 지 확인
	var foundItem = getItemFromCookieString(value, cartItem[cartIndexes.indexOf('productId')]);
	// 추가하고자 하는 상품이 장바구니에 존재할 경우의 처리
	if (foundItem !== null && foundItem !== undefined) {
		
		// 새로 추가하는 상품의 수량을 변경
		var cartQuantityIndex = cartIndexes.indexOf('quantity');
		cartItem[cartQuantityIndex] += foundItem[cartQuantityIndex];
		// 기존의 상품을 삭제
		removeItemFromCart(item);
	}
	// 장바구니에 상품이 있을 경우 상품 구분자 추가
	if (value.length > 0) {
		value = value + cartItemDelimiter;
	}
	
	var newItem = createItemString(cartItem);
	
	setInstanceCookie('cart', value + newItem);
	
}
// 장바구니에서 상품을 제거하는 함수
function removeItemFromCart(cartItem) {
	
	// 쿠키로부터 장바구니 정보를 가져옴
	var value = getCookie('cart');
	// 지워야 하는 상품을 문자열 정보로 변환
	var deleteItem = createItemString(cartItem);
	
	// 제거해야 하는 상품의 위치가 1번째 이상일 때 처리
	value = value.replace(cartItemDelimiter + deleteItem, '');
	// 제거해야 하는 상품의 위치가 0번째일 때 처리 
	value = value.replace(deleteItem + cartItemDelimiter, '');
	
	// 쿠키값 저장
	setInstanceCookie('cart', value);
}
// 장바구니 정보를 이용해 화면에 출력해주는 함수
function printCart() {
	// 쿠키로부터 장바구니 정보를 가져옴
	var value = getCookie('cart');
	if (value.length > 0) {
		var cartItems = value.split(cartItemDelimiter);
		for ( var index in cartItems) {
			var item = convertStringToItem(cartItems[index]);
			
			var cartProductIdIndex = cartIndexes.indexOf('productId');
			item = item.push(Utils.getImagePath(item[cartProductIdIndex]));
			
			for (itemInfoIndex in item) {
				
				// 장바구니에서 하나의 상품을 표시할 때 사용하는 html
				var cartItemTemplate = '<div class="shp__single__product">'
									+  '	<div class="shp__pro__thumb">'
									+  '		<a href="#">'
									+  '		  <img src="###' + cartIndexes.indexOf('imagePath') + '###" alt="product images">'
									+  '		</a>'
									+  '	</div>'
									+  '	<div class="shp__pro__details">'
									+  '		<h2><a href="product-details.html">###' + cartIndexes.indexOf('productName') + '###</a></h2>'
									+  '		<span class="quantity">QTY: ###' + cartIndexes.indexOf('quantity') + '###</span>'
									+  '		<span class="shp__price">###' + cartIndexes.indexOf('price') + '###</span>'
									+  '	</div>'
									+  '	<div class="remove__btn">'
									+  '		<a href="' + cartIndexes.indexOf('productId') + '" title="Remove this item"><i class="zmdi zmdi-close"></i></a>'
									+  '	</div>'
									+  '</div>';
				
				// 값을 변화시켜주기 위한 정규표현식
				var regExp = new RegExp('###' + itemInfoIndex + '###', 'gm');
				
				var itemToAppend = cartItemTemplate.replace(regExp, item[itemInfoIndex]);
				
				$('div.shp__cart__wrap').append(itemToAppend);
			}
		}
	}
}