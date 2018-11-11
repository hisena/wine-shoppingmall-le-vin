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
	$(document).on('click', '#myPage', function(event) {
		route(event, '#myPage', '.fixed__footer');
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
function defaultRoute(fileLocation, selector, callback) {
	$(selector).empty();
	$(selector).load(fileLocation, getScript.bind(this, callback));
}

function route(event, id, selector, callback) {
	event.preventDefault();
	var page;
	if (!id) {
		page = 'components/main.html';
	} else {
		page = $(id).attr('href');
	}
	defaultRoute(page, selector, callback);
}

// 스크립트파일 동적 추가
function getScript(callback){
	$.getScript('js/main.js', callback);
}