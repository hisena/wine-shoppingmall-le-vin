/**
 * 화면에 동적으로 html파일을 추가하는 기능
 * 
 * @author 김홍기
 */
// Router 등록
$(function(){
	$(document).on('ready', function(event) {
		route(event, null, '.fixed__footer');
	});
	$(document).on('click', '#login_register', function(event) {
		route(event, '#login_register', '.fixed__footer');
	});
	$(document).on('click', '#item', function(event) {
		route(event, '#item', '.fixed__footer');
	});
	$(document).on('click', '#productReview', function(event) {
		route(event, '#productReview', '.product_details');
		$('#productReview').attr("href", "components/item/details.html");
		$('#productReview').attr("id", "productDetails");
	});
	$(document).on('click', '#productDetails', function(event) {
		route(event, '#productDetails', '.product_details');
		$('#productDetails').attr("href", "components/item/review.html");
		$('#productDetails').attr("id", "productReview");
	});
});

// Router 생성
function defaultRoute(fileLocation, selector, data) {
	$(selector).empty();
	$(selector).load(fileLocation, data, getScript);
}

function route(event, id, selector, data) {
	event.preventDefault();
	var page;
	if (id == null || id == undefined) {
		page = 'components/main.html';
	} else {
		page = $(id).attr('href');
	}
	defaultRoute(page, selector, data);
}

// 스크립트파일 동적 추가
function getScript(){
	$.getScript('js/main.js', function(){
		var email = getCookie("email");
		if (email !== null && email !== undefined) {
			var user = email.split("@");
			if (email != "") {
				$('#user').html(user[0] + "님 로그인 중...")
				$('#user').css("display", "block");
				$('#login_register').attr("id", "item");
			}
		}
	});
}