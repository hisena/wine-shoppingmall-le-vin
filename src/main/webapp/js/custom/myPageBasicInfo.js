/**
 * 
 */
function getMyPageBasicInfo() {
 	// 통신 
	$.ajax(Utils.baseUrl + 'user/basic-info-list.mall', {
		data: 'email=' + getCookie('email'),
		dataType: "JSON",
		method: "GET",
		success: function(data) {
			
			$('input[name="userName"]').val(data.userName);
			$('input[name="email"]').val(data.email);
			$('input[name="mobile"]').val(data.mobile);
		},
		error: function(error) {
			snackbar('개인정보를 불러오던 중 오류가 발생했습니다. 잠시 후 시도해주세요.');
		}
	});
			
}