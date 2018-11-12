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