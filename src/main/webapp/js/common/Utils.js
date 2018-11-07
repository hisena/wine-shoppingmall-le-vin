/**
 * 어플리케이션 내부에서 공통적으로 사용되는 유용한 함수 집합 클래스
 * 
 * @author 이승은
 */
var Utils = {
	/**
	 * message를 담은 p 태그를 생성해 특정 엘리먼트에 붙이는 함수
	 */
	createMessage : function(message, element) {
		// 동적으로 p 태그를 만듦
		var messageWrapper = $('<p/>', {
			text: message,
		})
		.addClass('message')
		.insertAfter(elemnt);
	},
	/**
	 * message를 담은 p 태그를 삭제하는 함수
	 */
	deleteMessage : function(element) {
		var nextElement = element.next();
		
		if (nextElement.hasClass('message')) {
			nextElement.remove();
		}
	}
};

