/**
 * 장바구니 관련 처리를 담당하는 자바 스크립트 
 */

// 장바구니 상품 (배열)의 각 인덱스의 의미
var cartIndexes = ['productId', 'productName', 'quantity', 'price', 'imagePath', 'maxQuantity'];
// 장바구니에서 상품을 구분할 때 사용하는 구분자
var cartItemDelimiter = '_';
// 상품 정보를 구분할 때 사용하는 구분자
var itemInfoDelimiter = '~';
// 태그 정보를 담고 있는 변수
var cartSelectorData = {
	'list' : {},
	'detail' : {},
	'cart' : {},
	'cartDetail' : {}
};
// 리스트에서 값을 가져올 때
cartSelectorData.list[cartIndexes.indexOf('productId')] = {
	'selector' : '.product__action a[data-toggle="modal"]',
	'attr' : 'href'
};
cartSelectorData.list[cartIndexes.indexOf('productName')] = '.product__details h2';
cartSelectorData.list[cartIndexes.indexOf('price')] = '.product__price .new__price';
cartSelectorData.list[cartIndexes.indexOf('imagePath')] = {
		'selector' : '.pro__thumb img',
		'attr' : 'src'
}
// 가능한 최대 수량 관련 정보를 담고 있는 변수
cartSelectorData.list[cartIndexes.indexOf('maxQuantity')] = {
	'selector' : '#maxQuantity',
	'attr' : 'value'
};
// 상세보기에서 값을 가져올 때 
cartSelectorData.detail[cartIndexes.indexOf('productId')] = {
		'selector' : '#productId',
		'attr' : 'value'
};
cartSelectorData.detail[cartIndexes.indexOf('productName')] = '.product-info h1';
cartSelectorData.detail[cartIndexes.indexOf('quantity')] = {
		'selector' : '#quantity',
		'attr' : 'value'
}
cartSelectorData.detail[cartIndexes.indexOf('price')] = '.new-price';
cartSelectorData.detail[cartIndexes.indexOf('imagePath')] = {
		'selector' : '.product-images img',
		'attr' : 'src'
}
//가능한 최대 수량 관련 정보를 담고 있는 변수
cartSelectorData.detail[cartIndexes.indexOf('maxQuantity')] = {
	'selector' : '#quantity',
	'attr' : 'max'
};
// 장바구니에서 값을 가져올 때
cartSelectorData.cart[cartIndexes.indexOf('productId')] = {
		'selector' : '#productId',
		'attr' : 'value'
};
cartSelectorData.cart[cartIndexes.indexOf('productName')] = '.shp__pro__details h2 a';
cartSelectorData.cart[cartIndexes.indexOf('quantity')] = '.quantity';
cartSelectorData.cart[cartIndexes.indexOf('price')] = '.shp__price';
cartSelectorData.cart[cartIndexes.indexOf('imagePath')] = {
		'selector' : '.shp__pro__thumb img',
		'attr' : 'src'
}
//가능한 최대 수량 관련 정보를 담고 있는 변수
cartSelectorData.cart[cartIndexes.indexOf('maxQuantity')] = {
		'selector' : '#maxQuantity',
		'attr' : 'value'
};
// 주문하기 전 장바구니 상세에서 값을 가져올 때
cartSelectorData.cartDetail[cartIndexes.indexOf('productId')] = {
		'selector' : '#productId',
		'attr' : 'value'
};
cartSelectorData.cartDetail[cartIndexes.indexOf('productName')] = '.product-name';
cartSelectorData.cartDetail[cartIndexes.indexOf('quantity')] = {
		'selector' : '.product-quantity input',
		'attr' : 'value'
}
cartSelectorData.cartDetail[cartIndexes.indexOf('price')] = '.product-price .amount';
cartSelectorData.cartDetail[cartIndexes.indexOf('imagePath')] = {
		'selector' : '.product-thumbnail img',
		'attr' : 'src'
}


// 모든 경우의 수를 고려해 수정
function replaceInAllCases(value, target, stringToReplace, needDelimiter) {
	// 수정해야 하는 상품의 위치가 1번째 이상일 때 처리
	var replaceString = needDelimiter? cartItemDelimiter + stringToReplace : stringToReplace; 
	value = value.replace(cartItemDelimiter + target, replaceString);
	
	// 수정해야 하는 상품의 위치가 0번째일 때 처리 
	replaceString = needDelimiter? stringToReplace + cartItemDelimiter: stringToReplace; 
	value = value.replace(target + cartItemDelimiter, replaceString);
	
	// 상품이 하나만 있을 때 처리
	replaceString = stringToReplace; 
	value = value.replace(target, replaceString);
	
	return value;
}
// 선택자와 속성 정보로 값을 가져오는 함수
function retrieveValueFromTarget(targetProduct, selector, attr) {
	var cartItemValue;
	var targetElement = $(targetProduct).find(selector);
	if (attr === 'value') {
		cartItemValue = targetElement.val();
	} else {
		cartItemValue = targetElement.attr(attr);
	}
	return cartItemValue;
}
// html 태그로부터 필요한 정보를 추출하는 함수
function createCartItemFromTags(targetProduct, type) {
	
	var cartItem = [];
	var typeCartSelector = cartSelectorData[type];
	
	for (var index in typeCartSelector) {
		
		var cartSelectorDatum = typeCartSelector[index];
		// html()이 아닌 속성으로 값을 가져와야 하는 경우에 대한 처리
		if (typeof cartSelectorDatum !== 'string') {
			var cartItemValue = retrieveValueFromTarget(targetProduct, cartSelectorDatum['selector'], cartSelectorDatum['attr']);
			cartItem.push(cartItemValue);
		} else {
			var cartItemValue = $(targetProduct).find(cartSelectorDatum).html();
			cartItem.push(cartItemValue);
		}
	}
	// 수량 선택이 불가능한 경우 (목록에서 장바구니 추가할 경우)
	if (type === 'list') {
		cartItem.splice(cartIndexes.indexOf('quantity'), 0, 1);
	}
	
	return cartItem;
}
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
			if (item[cartProductIdIndex] == productId) {
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
		cartItem[cartQuantityIndex] = parseInt(cartItem[cartQuantityIndex]) + parseInt(foundItem[cartQuantityIndex]);
		// 기존의 상품을 수정
		value = replaceInAllCases(value, createItemString(foundItem), createItemString(cartItem), true);
	
	} else {
		
		// 장바구니에 상품이 있을 경우 상품 구분자 추가
		if (value.length > 0) {
			value = value + cartItemDelimiter;
		}
		
		value += createItemString(cartItem); 
	}
	setInstanceEncodedCookie('cart', value);
	
}
// 장바구니에서 상품을 제거하는 함수
function removeItemFromCart(cartItem) {
	
	// 쿠키로부터 장바구니 정보를 가져옴
	var value = getCookie('cart');
	// 지워야 하는 상품을 문자열 정보로 변환
	var deleteItem = createItemString(cartItem);
	// 제거
	value = replaceInAllCases(value, deleteItem, '');
	// 쿠키값 저장
	setInstanceEncodedCookie('cart', value);
	
}
// 가격 문자열을 숫자로 변환해주는 함수
function priceStringToNumber(priceString) {
	return parseInt(priceString.substr(0, priceString.length - 1).replace(',',''));
}
// 합계를 구해주는 함수 
function getTotalPrice(quantity, price) {
	var quantityValue = parseInt(quantity);
	var priceValue = priceStringToNumber(price);
	
	return quantityValue * priceValue;
}
// 전달 받은 상품 출력해주는 함수
function printItemsCommon(targetSelector, items, needDeleteButton) {
	
	$(targetSelector).html('');
	
	var totalPrice = 0;
	
	for ( var index in items) {
		var item = items[index];
		
		totalPrice += getTotalPrice(item[cartIndexes.indexOf('quantity')], item[cartIndexes.indexOf('price')]);
		
		// 장바구니에서 하나의 상품을 표시할 때 사용하는 html
		var itemToAppend = '<div class="shp__single__product">'
			+  '<input type="hidden" id="productId" value="###' + cartIndexes.indexOf('productId') + '###">'
			+  '<input type="hidden" id="maxQuantity" value="###' + cartIndexes.indexOf('maxQuantity') + '###">'
			+  '	<div class="shp__pro__thumb">'
			+  '		<img src="###' + cartIndexes.indexOf('imagePath') + '###" alt="product images">'
			+  '	</div>'
			+  '	<div class="shp__pro__details">'
			+  '		<h2><a>###' + cartIndexes.indexOf('productName') + '###</a></h2>'
			+  '		수량: <span class="quantity">###' + cartIndexes.indexOf('quantity') + '###</span>'
			+  '		<span class="shp__price">###' + cartIndexes.indexOf('price') + '###</span>'
			+  '	</div>';
		if (needDeleteButton) {
			itemToAppend 
			+= ('	<div class="remove__btn">'
			+  '		<a href="' + cartIndexes.indexOf('productId') + '" title="Remove this item"><i class="zmdi zmdi-close"></i></a>'
			+  '	</div>');
		}
			
			itemToAppend +=  '</div>';
		
		for (var i = 0; i < item.length; i++) {
			// 값을 변화시켜주기 위한 정규표현식
			var regExp = new RegExp('###' + i + '###', 'gm');
			
			itemToAppend = itemToAppend.replace(regExp, item[i]);
			
		}
		$(targetSelector).append(itemToAppend);
		
	}
	$(targetSelector).next().find('.total__price').html(totalPrice.toLocaleString() + '원');
	
}
// 장바구니 정보를 이용해 화면에 출력해주는 함수
function printCart() {
	
	// 쿠키로부터 장바구니 정보를 가져옴
	var value = getCookie('cart');

	var items = [];
	
	if (value.length > 1) {
		var cartItems = value.split(cartItemDelimiter);
		for ( var index in cartItems) {
			items.push(convertStringToItem(cartItems[index]));
		}
	}
	
	printItemsCommon('div.shp__cart__wrap', items, true);
	
}

function printCartTotal() {
	var total = 0;
	var subTotals = $('td.product-subtotal');
	
	for (var i = 0; i < subTotals.length; i++) {
		total += priceStringToNumber(subTotals.eq(i).html());
	}
	
	$('.order-total .amount').html(total.toLocaleString() + '원');
}

$(function(){
	// 장바구니 화면 출력 
	printCart();
	
	// 목록에서 장바구니 추가 버튼 클릭 시
	$(document).on('click', 'ul.product__action li:nth-child(3) a', function(event){
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
			addItemToCart(item);
			printCart();
			snackbar('장바구니에 추가되었습니다.');
		}
	});
	
	// 장바구니 삭제 이벤트 처리
	$(document).on('click', '.remove__btn a', function(){
		event.preventDefault();
		
		var targetProduct = $(this).parents('div.shp__single__product');
		var item = createCartItemFromTags(targetProduct, 'cart');
		removeItemFromCart(item);
		printCart();
	});
	
	// 상세 보기에서 장바구니 추가 버튼 클릭 시
	$(document).on('click', '.addtocart-btn a:nth-child(2)', function(){
		event.preventDefault();
		
		var targetProduct = $(this).parents('.modal-product');
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
		} else if (item[cartIndexes.indexOf('maxQuantity')] < (originalQuantity + parseInt(item[quantityIndex]))) {
			snackbar('재고가 부족합니다.');
		} else {
			addItemToCart(item);
			printCart();
			snackbar('장바구니에 추가되었습니다.');
		}
		
	});
})