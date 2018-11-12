/**
 * 마이페이지에서 주문 관련 처리를 수행하는 함수 집합
 * 
 * @author 이승은
 */

// 주문 상태를 계산해주는 함수
function computeOrderState(order) {
	var result = '상품 준비 중';
	
	// 배송 시작일이 있다면 배송 중 상태
	if (order.startDate) {
		result = '배송 중';
	}
	
	// 취소 일자가 있다면 주문 취소 상태
	if (order.cancelDate) {
		result = '주문 취소';
	}
	
	// 배송 일자가 있다면 배송 완료 상태
	if(order.deliveryCompleteDate) {
		result = '배송 완료';
	}
	
	// (반품) 등록일이 있다면 반품 신청 상태
	// 반품 완료일 재사용을 위해 변수 선언
	var completeDate = order.completeDate;
	if (order.regdate && !completeDate) {
		result = '반품 대기';
	} else if (order.regdate && completeDate) {
		result = '반품 완료';
	}
	
	return result;
}

// 목록을 가져오는 함수 재사용을 위해 선언
function getOrderList(currentPage){
	$.ajax(Utils.baseUrl + "order/list.mall", {
		method: "get",
		data: 'email=' + getCookie('email') + '&currentPage=' + currentPage,
		contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
		success: function(data) {
			var orderList = data.orderList;
			var pageInfo = data.pageInfo;
			
			$('.order-wrapper').empty();
			
			var tableBasic = '<div class="table-content table-responsive">' + 
                             '	<table>' +
                             '     <thead>' +
                             '       <tr>' +
                             '         <th>주문 번호</th>' +
                             '     	   <th>총 주문 금액</th>' +
                             '     	   <th>주문일</th>' +
                             '     	   <th>결제 완료 여부</th>' +
                             '     	   <th>주문상태</th>' +
                             '     	</tr>' +
                             '     </thead>' +
                             '     <tbody>' + 
                             '	   </tbody>' +
                			 '  </table>' +
          	  				 '</div>';
			$('.order-wrapper').html(tableBasic);
			
			
			for ( var index in orderList) {
				var order = orderList[index];
				var itemToAppend = '<tr>' +
                                   '   <td>' + order.orderId + '</td>' +
                                   '   <td>' + order.orderMoney + '</td>' + 
                                   '   <td>' + order.orderDate + '</td>' +
                                   '   <td>' + (order.isPaid === 'Y'? '결제 완료' : '결제 대기') + '</td>' + 
                                   '   <td>' + computeOrderState(order) + '</td>' +
                                   '</tr>'
                $('.table-content tbody').append(itemToAppend);
			}
			
			// 페이지네이션 보이기
			$('.pagination-lg').css('display','block');
			
			// 페이지네이션
			$('.pagination-lg').empty();
			page(pageInfo.currentPage, pageInfo.endPage, pageInfo.next, pageInfo.prev, pageInfo.startPage, pageInfo.totalCount)
		},
		error: function(error) {
			snackbar('주문 목록을 불러오는 중 오류가 발생했습니다. 잠시 후 시도해주시길 바랍니다.');
		}
	});
}

// 상세보기를 위한 함수 재사용을 위해 선언
function getOrderDetail(orderId){
	
	var currentPage = $('.pagination-lg li.active a').attr('href');
	
	$.ajax(Utils.baseUrl + "order/detail.mall", {
		method: "get",
		data: 'orderId=' + orderId,
		contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
		success: function(data) {
			var order = data.orderInfo;
			order['paymentStatus'] = order.isPaid === 'Y'? '결제 완료' : '결제 대기';
			var orderStatus = computeOrderState(order);
			order['orderStatus'] = orderStatus;
			order['deliveryComments'] = order['deliveryComments']? order['deliveryComments'] : '';
			order['currentPage'] = currentPage;
			// 주문 관련 정보
			var itemToAppend = orderDetailTemplate.slice();
			for ( var key in order) {
				var regExp = new RegExp('###' + key + '###', 'gm');
				var value =  order[key] ?  order[key] : '';
				itemToAppend = itemToAppend.replace(regExp, value);
			}
			
			$('.payment-form .section-title-3').html('주문 확인하기');
			$('.order-wrapper').html(itemToAppend);
			
			// 상태에 따라 버튼 바꾸기
            var aTag = '';
            if (orderStatus === '상품 준비 중') {
            	aTag = '<a onclick="cancelOrder(' + orderId + ')">주문 취소하기</a>'
            } else if (orderStatus === '배송 중') {
            	aTag = '<a href="https://www.doortodoor.co.kr/parcel/pa_004.jsp" target="_blank">배송 조회하기</a>';
            } else if (orderStatus === '배송 완료') {
            	aTag = '<a onclick="refund(' + orderId + ')">반품하기</a>';
            }
            $('.button-wrapper').prepend(aTag);
			
			// 상품 정보
			var items = [];
			var products = data.productInfo;
			for ( var index in products) {
				var item = [];
				var product = products[index];
				
				var productId = product.productId;
				item[cartIndexes.indexOf('productId')] = productId;
				item[cartIndexes.indexOf('productName')] = product.productNameKor;
				item[cartIndexes.indexOf('quantity')] = product.quantity;
				item[cartIndexes.indexOf('price')] = product.price.toLocaleString() + '원';
				item[cartIndexes.indexOf('imagePath')] = Utils.getImagePath(productId);
				items.push(item);
			}
			printItemsCommon('.shp__cart__wrap', items);
			
			// 페이지네이션 숨기기
			$('.pagination-lg').css('display','none');
		},
		error: function(error) {
			snackbar('주문 상세 정보를 불러오는 중 오류가 발생했습니다. 잠시 후 시도해주시길 바랍니다.');
		}
	})
}

// 주문 취소를 위한 함수
function cancelOrder(orderId) {
	$.ajax(Utils.baseUrl + "order/cancel.mall", {
		method: "get",
		data: 'orderId=' + orderId,
		contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
		success: function(data) {
			getOrderDetail(orderId);
			snackbar('성공적으로 주문을 취소했습니다.');
		},
		error: function(error) {
			snackbar('주문을 취소하는 중 오류가 발생했습니다. 잠시 후 시도해주시길 바랍니다.');
		}
	});
}

// 반품을 위한 함수
function refund(orderId) {
	$.ajax(Utils.baseUrl + "order/refund.mall", {
		method: "post",
		data: 'orderId=' + orderId,
		dataType: 'json',
		success: function(data) {
			getOrderDetail(orderId);
			snackbar('성공적으로 반품 신청되었습니다.');
		},
		error: function(error) {
			snackbar('반품을 신청하는 중 오류가 발생했습니다. 잠시 후 시도해주시길 바랍니다.');
		}
	});
}

// 이벤트 등록
$(function(){
    // 페이지 로딩 시 주문 목록
	getOrderList(1);
    
    // 페이지네이션 주문 목록
    $('.pagination-lg').on('click', 'a', function(event){
    	event.preventDefault();
    	
    	var currentPage = $(this).attr('href');
    	getOrderList(currentPage);
    });
    
    // 주문 상세 보기
    $('.order-wrapper').on('click', 'tbody tr', function() {
    	
    	var orderId = $(this).find('td:first-child').html();
    	getOrderDetail(orderId);
    });
});

// 주문 상세 보기를 위한 템플릿
var orderDetailTemplate = '	<div class="orderInfo">' +
						  '	  <div class="single-checkout-box"><input type="hidden" id="orderId" value="###orderId###"></div>' + 
    					  '	  <div class="single-checkout-box"><span>결제 상태 : </span><span>###paymentStatus###</span></div>' +
    					  '	  <div class="single-checkout-box"><span>주문 상태 : </span><span>###orderStatus###</span></div>' +
    					  '	  <div class="single-checkout-box"><span>주문 일자 : </span><span>###orderDate###</span></div>' +
    					  '	  <div class="single-checkout-box"><span>수신인 이름 : </span><span>###receiverName###</span></div>' +
    					  '	  <div class="single-checkout-box"><span>수신인 연락처 : </span><span>###receiverMobile###</span></div>' + 
    					  '	  <div class="single-checkout-box"><span>배송 요청 사항 : </span><span>###deliveryComments###</span></div>' +
    					  '   <div class="single-checkout-box"><span>배송 주소 : </span><span>###zipCode###&nbsp;&nbsp;###address###&nbsp;&nbsp;###detailedAddress###</span></div>' +
    					  '</div>' +
						  '<div class="puick-contact-area">' + 
						  '	  <div class="shopping__cart__inner">' + 
						  '		<div class="shp__cart__wrap"><!-- 동적으로 추가되는 영역 --></div>' + 
						  '		   <ul class="shoping__total">' + 
						  '			 <li class="subtotal">총 금액:</li>' + 
						  '		     <li class="total__price">###orderMoney###</li>' + 
						  '		   </ul>' +
						  '		</div>' +
						  '	 </div>' +
						  '</div>' +
						  '<div class="wc-proceed-to-checkout button-wrapper" style="clear: right;">' +
                          '  <a onclick="getOrderList(###currentPage###)">목록으로 돌아가기</a>' +
						  '</div>';