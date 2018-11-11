// 인증번호 확인을 위한 타이머 생성
var timer;
$(function() {
	$(document).on('click', '.forget > a', function(event) {
		clock(3,0, function() {
			alert('시간 종료')
		})
	});
	$(document).on('click', '#start', function(event) {
		clock(1,0, function () {
			$('#clock').css("display", "none");
	        $('#loginButton').attr("disabled", false);
	        setCookie('count', '', -1);
		})
	});
});
// 타이머 시간을 설정해줌
function clock(min, sec, callback) {
	var minute = min;
	var second = sec;
	
	if (timer === null || timer === undefined) {
	    timer = setInterval(function() {
			$(".countTimeMinute").html(minute);
			$(".countTimeSecond").html(second);
			
			if (second==0 && minute==0) {
				callback();
				clearInterval(timer);
			} else {
				second--;
				
				if (second < 0) {
					minute--;
					second = 59;
				}
			}
		}, 1000);
	} else {
    	clearInterval(timer);
    	timer = null;
	}
	    
    event.preventDefault();
}