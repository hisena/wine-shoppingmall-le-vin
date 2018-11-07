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
		.insertAfter(element);
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
// 유효성 검색 함수를 이용해 메시지를 추가하는 함수
Utils.createMessageUsingValidationFunction = function createMessageUsingValidationFunction(validationFunction){
	
	var inputElement = Validation.getTargetInput(validationFunction);
	
	var metaDatum = Validation.metaData[validationFunction.name];
	var message = metaDatum['message'];
	
	Utils.createMessage(message, inputElement);
};

// 유효성 검사 함수를 이용해 메시지를 삭제하는 함수
Utils.deleteMessageUsingValidationFunction = function deleteMessageUsingValidationFunction(validationFunction) {
	var inputElement = Validation.getTargetInput(validationFunction);
	Utils.deleteMessage(inputElement);
};

// 유효성 검사 함수들을 이용해 모든 메시지를 삭제하는 함수
Utils.deleteAllMessagesUsingValidationFunction = function deleteAllMessagesUsingValidationFunction(validationFunctions) {
	
	for (var i = 0; i < validationFunctions.length; i++) {

		var inputElement = Validation.getTargetInput(validationFunctions[i]);
		Utils.deleteMessage(inputElement);
		
	}
	
};