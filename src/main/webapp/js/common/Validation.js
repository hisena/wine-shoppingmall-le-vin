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
			'messageTargetSelector' : '#emailConfirm',
			'message' : '올바른 이메일 형식이 아닙니다.'
		},
		'isValidPassword' : {
			'selector' : 'input[name="password"]',
			'message' : '올바른 비밀번호 형식이 아닙니다.'
		},
		'isEmailDuplicationChecked' : {
			'selector' : 'input[name="emailDuplicationCheck"]',
			'messageTargetSelector' : '#emailConfirm',
			'message' : '중복 검사를 통과하지 못했습니다.'
		},
		'isPasswordChecked' : {
			'selector' : '#passwordConfirm',
			'message' : '입력하신 비밀번호와 일치하지 않습니다.'
		},
		'isValidUserName' : {
			'selector' : 'input[name="userName"]',
			'message' : '이름은 필수 입력 사항이며, 10 바이트를 넘을 수 없습니다.'
		},
		'isValidMobile' : {
			'selector' : 'input[name="mobile"]',
			'message' : '휴대전화 번호는 필수 입력 사항이며, "010-0000-0000" 형식으로 입력해야 합니다.',
		},
		'isValidSSN' : {
			'selector' : '#ssn',
			'message' : '주민등록번호를 확인해주세요. 성인이 아니시라면 가입하실 수 없습니다.'
		},
		'isZipCodeEntered' : {
			'selector' : 'input[name="zipCode"]',
		},
		'isAddressEntered' : {
			'selector' : 'input[name="address"]',
		},
		'isZipCodeAndAddressEntered' : {
			'selector' : 'input[name="address"]',
			'message' : '우편번호 검색을 통해 우편번호와 주소를 입력해주세요.'
		},
		'isDetailedAddressEntered' : {
			'selector' : 'input[name="detailedAddress"]',
			'message' : '상세주소를 입력해주세요.'
		},
		'isValidCardNumber' : {
			'selector': '#cardNumber',
			'message' : '결제를 위해 카드 번호를 입력해주세요.'
		},
		'isValidValidityPeriod' : {
			'selector' : '#validityPeriod',
			'message' : '유효기간을 선택해주시길 바랍니다.'
		},
		'isValidCscNumber' : {
			'selector' : '#cscNumber',
			'message' : '유효한 csc 번호가 아닙니다. 확인 부탁드립니다.'
		}
	},
	// 선택자 정보를 이용해 검사 대상이 되는 input 태그를 가져오는 함수
	getTargetInput : function(validationFunction) {
		var metaDatum = Validation.metaData[validationFunction.name];
		var selector = metaDatum['selector'];

		return $(selector);
	},
	isZipCodeEntered : function(){},
	isAddressEntered : function(){},
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

// 이메일 중복 검사 여부를 확인하는 함수
Validation.isEmailDuplicationChecked = function isEmailDuplicationChecked() {
	
	if (!Validation.isValidEmail()) {
		return true;
	}
	
	return Validation.getTargetValue(Validation.isEmailDuplicationChecked) === 'true';
}

// 비밀번호의 유효성을 검증하는 함수 
// (영문 대소문자 8-16자)
Validation.isValidPassword = function isValidPassword(){
	
	var passwd = Validation.getTargetValue(Validation.isValidPassword);
	var pattern = /[0-9a-zA-Z]{8,16}/;
	
	return pattern.test(passwd);
};

// 비밀번호와 비밀번호 확인 일치 여부를 확인하는 함수
Validation.isPasswordChecked = function isPasswordChecked() {
	
	var passwordValue = Validation.getTargetValue(Validation.isValidPassword);
	var passwordCheckValue = Validation.getTargetValue(Validation.isPasswordChecked);

	return passwordValue === passwordCheckValue;
};

// 이름 값이 들어왔는지, 데이터베이스 상 최대 길이를 초과하지 않는지 확인하는 함수
Validation.isValidUserName = function isValidUserName() {
	var userName = Validation.getTargetValue(Validation.isValidUserName).trim();
	
	return userName.length > 0
		&& Utils.stringByteLength(userName) <= 10;
};

// 입력한 휴대전화 번호가 유효한지 확인하는 함수
Validation.isValidMobile = function isValidMobile() {
	var mobile = Validation.getTargetValue(Validation.isValidMobile);
	var pattern = /^(01[016789]{1})-[0-9]{3,4}-[0-9]{4}$/;
	
	return pattern.test(mobile);

};

// 입력한 주민번호가 유효한지, 성인인지 확인하는 함수
Validation.isValidSSN = function isValidSSN() {
	var ssn = Validation.getTargetValue(Validation.isValidSSN);
	var pattern = /^[0-9]{2}(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])-[012349]$/;
	
	var centryIndex = ssn.substr(ssn.indexOf('-') + 1, 1);
	
	return pattern.test(ssn) && (centryIndex === '1' || centryIndex === '2');
};

// 우편 번호 검색 여부를 확인하는 함수
Validation.isZipCodeAndAddressEntered = function isZipCodeAndAddressEntered() {
	var zipCode = Validation.getTargetValue(Validation.isZipCodeEntered).trim();
	var address = Validation.getTargetValue(Validation.isAddressEntered).trim();
	
	return zipCode.length > 0 && address.length > 0;
}

// 상세 주소 입력 여부를 확인하는 함수
Validation.isDetailedAddressEntered = function isDetailedAddressEntered(){
	var detailedAddress = Validation.getTargetValue(Validation.isDetailedAddressEntered).trim();
	
	return detailedAddress.length > 0;
}

// 카드 번호 유효성을 확인하는 함수
Validation.isValidCardNumber = function isValidCardNumber() {
	var cardNumber = Validation.getTargetValue(Validation.isValidCardNumber).trim();
	var pattern = /[0-9]{16}/;
	
	return cardNumber.match(pattern);
}

// 카드 유효 기간을 확인하는 함수
Validation.isValidValidityPeriod = function isValidValidityPeriod() {
	var validityPeriod = Validation.getTargetValue(Validation.isValidValidityPeriod);
	var pattern = /[0-9]{2}=[0-9]{2}/;
	
	return validityPeriod.match(pattern);
}

// 카드 csc 번호를 확인하는 함수
Validation.isValidCscNumber = function isValidCscNumber() {
	var cscNumber = Validation.getTargetValue(Validation.isValidCscNumber);
	var pattern = /[0-9]{3}/;
	
	return cscNumber.match(pattern);
}

// 전체 유효성 검사 함수를 실행하는 함수
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