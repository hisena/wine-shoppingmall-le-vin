// 인증번호 확인을 위한 타이머 생성
var timer;
$(function() {
	$(document).on('click', '.forget > a', function(event) {
		var minute = 3;
		var second = 0;
		
		if (timer === null || timer === undefined) {
			
    	    timer = setInterval(function() {
    			$(".countTimeMinute").html(minute);
    			$(".countTimeSecond").html(second);
    			$(".countTimeMinute").html(minute);
    			$(".countTimeSecond").html(second);
    			
    			if (second==0 && minute==0) {
    				alert('타이머 종료');
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
	});
});