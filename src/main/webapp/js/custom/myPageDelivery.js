/**
 * 마이페이지에서 배송지 정보를 다루는 함수 집합
 * 
 * @author 이승은
 */
function getDeliveryList() {
	
	$.ajax(Utils.baseUrl + "order/address.mall", {
		method: "get",
		data: 'email=' + getCookie('email'),
		contentType: 'application/json; charset=utf-8',
	    dataType: 'json',
		success: function(data) {
			
			$('.delivery-wrapper tbody').empty();
			
			var addresses = data.AddressInfo;
			for ( var index in addresses) {
				var address = addresses[index];
				var itemToAppend = '<tr>' +
                                   '   <td>' + address.zipCode + '</td>' +
                                   '   <td>' + address.address + '</td>' + 
                                   '   <td>' + address.detailedAddress + '</td>' +
                                   '   <td><span class="glyphicon glyphicon-remove" onclick="removeDelivery(' + address.addressId + ')"></span></td>' +
                                   '</tr>'
                $('.delivery-wrapper tbody').append(itemToAppend);
			}
			
		},
		error: function(error) {
			snackbar('배송지 목록을 불러오는 중 오류가 발생했습니다. 잠시 후 시도해주시길 바랍니다.');
		}
	});
}
function removeDelivery(addressId) {
	var message = '배송지 삭제에 실패하셨습니다. 다시 한 번 시도해주세요.';
	$.ajax(Utils.baseUrl + "user/address-info-delete.mall", {
		method: "get",
		data: {'address_id' : addressId},
	    dataType: 'json',
		success: function(data) {
			
			var result = data.deleteAddressResult === 'true';
			
			if (result) {
				
				getDeliveryList();
				message = '성공적으로 배송지를 삭제했습니다.'
			} 
			snackbar(message);
		}, 
		error: function(data) {
			snackbar(message);
		}
	});
}