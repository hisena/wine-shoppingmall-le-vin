// Router 등록
$(function(){
	$(document).on('ready', function(event) {
		route(event, null);
	});
	$(document).on('click', '#login_register', function(event) {
		route(event, '#login_register');
	});
	$(document).on('click', '#item', function(event) {
		route(event, '#item');
	});
});

// Router 생성
function defaultRoute(fileLocation) {
	$('.fixed__footer').empty();
	$('.fixed__footer').load(fileLocation, getScript);
}

function route(event, id) {
	event.preventDefault();
	var page;
	if (id == null || id == undefined) {
		page = 'components/main.html';
	} else {
		page = $(id).attr('href');
	}
	defaultRoute(page);
}

// 스크립트파일 동적 추가
function getScript(){
	$.getScript('js/main.js');
}