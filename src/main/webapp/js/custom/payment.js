/**
 * 장바구니에서 구매로 이동하는 처리
 * 
 * @author 이승은
 */
$(function(){

	$('#goToPurchase').click(function(event){
  		var cartItems = [];
  		
  		// 장바구니 내 모든 아이템을 javascript 처리 가능한 객체로 변환
  		var targetItems = $('.shopping__cart .shp__single__product');
		for (var i = 0; i < targetItems.length; i++) {
			var targetItem = targetItems[i];
			cartItems.push(createCartItemFromTags(targetItem, 'cart'));
		}
      		
		route(event, '#' + $(this).attr('id'), '.fixed__footer', function(data){
			
			// 로그인 정보 표시
			getLoginInfo();
			
			// 로그인 안 했을 경우
			var loginUser = getCookie('email');
			if (loginUser.length <= 0) {
				$('.wc-proceed-to-checkout').html('로그인 후 이용 부탁드립니다.');
			}
			
			$('.table-responsive tbody').html('');
			
			for ( var index in cartItems) {
				var item = cartItems[index];
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
                  	  	'<input type="number" min="1" value="###' + cartIndexes.indexOf('quantity') + '###" />' + 
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
	});
});