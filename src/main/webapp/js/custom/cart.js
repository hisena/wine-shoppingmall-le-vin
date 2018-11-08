/**
 * 장바구니 관련 처리를 담당하는 자바 스크립트 
 */
// 장바구니 상품 (배열)의 각 인덱스의 의미
var cartProductIdIndex = 0;
var cartProductNameIndex = 1;
var cartQuantityIndex = 2;
var cartPriceIndex = 3;
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
	
	return result.substr(0, result.length - 1);
}
// 문자열을 상품 정보로 바꿔주는 함수
function convertStringToItem(cartItemString) {
	return cartItemString.split(itemInfoDelimiter);
}
// 장바구니에 상품을 추가하는 함수
function addItemToCart(cartItem) {
	
	var value = getCookie('cart');
	// 기존 장바구니에 상품이 있을 경우
	if (value.length > 0) {
		// 장바구니 안의 모든 상품을 순회하며 추가하는 상품과 일치하는 상품이 있는지 확인
		var cartItems = value.split(cartItemDelimiter);
		for ( var index in cartItems) {
			var item = convertStringToItem(cartItems[index]);
			// 일치하는 상품이 있을 경우 추가하는 상품의 수량을 수정
			if (cartItem[cartProductIdIndex] == item[cartProductIdIndex]) {
				cartItem[cartQuantityIndex] += item[cartQuantityIndex];
				break;
			}
		}
	}
	
	var newItem = createItemString(cartItem);
	
	setInstanceCookie('cart', value + newItem);
	
}
// 장바구니에서 상품을 제거하는 함수
function removeItemFromCart() {
	
}
// 장바구니 정보를 바탕으로 합계를 구해주는 함수
function getTotalPrice() {
	
}
// 장바구니 정보를 이용해 화면에 출력해주는 함수
function printCart() {
	
}