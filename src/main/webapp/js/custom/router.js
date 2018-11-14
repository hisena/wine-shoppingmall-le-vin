/**
 * 화면에 동적으로 html파일을 추가하는 기능
 * 
 * @author 김홍기
 */
// Router 등록
$(function(){
	// 메인 화면
	$(document).on('ready', function(event) {
		route(event, null, '.fixed__footer', getLoginInfo);
	});
	// 로그인/회원가입 화면
	$(document).on('click', '#login_register', function(event) {
		route(event, '#login_register', '.fixed__footer', getLoginInfo);
	});
	// 마이페이지 화면
	$(document).on('click', '#myPage', function(event) {
		route(event, '#myPage', '.fixed__footer', getLoginInfo);
	});
	// 상품리스트 화면
	$(document).on('click', '#item', function(event) {
		route(event, '#item', '.fixed__footer', getLoginInfo);
	});
	// 1대1 문의 화면
	$(document).on('click', '#qna', function(event) {
		route(event, '#qna', '.fixed__footer', getLoginInfo);
	});
	// 구매 후기
	$(document).on('click', '#productReview', function(event) {
		route(event, '#productReview', '.product_details', getLoginInfo);
		$('#productReview').attr("href", "components/item/details.html");
		$('#productReview').attr("id", "productDetails");
	});
	// 상품 상세정보
	$(document).on('click', '#productDetails', function(event) {
		route(event, '#productDetails', '.product_details', getLoginInfo);
		$('#productDetails').attr("href", "components/item/review.html");
		$('#productDetails').attr("id", "productReview");
	});
});

// 디폴트 라우터 생성
function defaultRoute(fileLocation, selector, callback) {
	$(selector).empty();
	$(selector).load(fileLocation, getScript.bind(this, callback));
}

// Router 생성
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

// 스크립트파일(main.js) 동적 추가
function getScript(callback){
	//$.getScript('js/owl.carousel.min.js', function(){

		$.getScript('js/main.js', callback);

	//});
}