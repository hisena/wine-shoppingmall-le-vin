/**
 * 유효성 검사에 관련된 클래스
 * 
 * @author 이승은
 */
var Validation = {
	// 유효성 검사에 관련된 정보를 저장하는 객체
	metaData : {
		'isValidEmail' : {
			'selector' : 'input[name="email"]',
			'message' : '올바른 이메일 형식이 아닙니다.'
		},
		'isValidPassword' : {
			'selector' : 'input[name="password"]',
			'message' : '올바른 비밀번호 형식이 아닙니다.'
		},
	},
	// 선택자 정보를 이용해 검사 대상이 되는 input 태그를 가져오는 함수
	getTargetInput : function(validationFunction) {
		var metaDatum = Validation.metaData[validationFunction.name];
		var selector = metaDatum['selector'];

		return $(selector);
	},
};
// 검사 대상이 되는 input 태그의 값을 가져오는 함수
Validation.getTargetValue = function getTargetValue(validationFunction) {

	return Validation.getTargetInput(validationFunction).val();
};

// 이메일의 유효성을 검증하는 함수
Validation.isValidEmail = function isValidEmail() {
	
	var email = Validation.getTargetValue(Validation.isValidEmail);
	var pattern = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+(\.[\w]+)+$/;
	
	return pattern.test(email);
};

// 비밀번호의 유효성을 검증하는 함수 
// (영문 대소문자 8-16자)
Validation.isValidPassword = function isValidPassword(){
	
	var passwd = Validation.getTargetValue(Validation.isValidPassword);
	var pattern = /[0-9a-zA-Z]{8,16}/;
	
	return pattern.test(passwd);
};

Validation.validAllInputs = function validAllInputs(validationFunctions) {
	// 전체 입력 값의 검증 결과를 저장하기 위한 변수
	var isAllValid = true;

	// 우선 메시지 초기화
	Utils.deleteAllMessagesUsingValidationFunction(validationFunctions);
	
	// 검증 관련 순회
	for (var i = 0; i < validationFunctions.length; i++) {

		// 관련 정보를 변수로 저장
		var validation = validationFunctions[i];
		// 순회한 입력값에 대한 결과를 저장
		var isValid = validation();
		// 유효하지 않을 경우 메시지 표시
		if (!isValid) {
			Utils.createMessageUsingValidationFunction(validation);
		}
		
		// 현재 유효성 검사값은 전체 유효성 검사 결과에 반영
		isAllValid = isAllValid && isValid;
	}
	
	return isAllValid;
};