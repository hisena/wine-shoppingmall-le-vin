/**
 * 상품 필터 관련 함수 집합 
 * 
 * @author 이승은
 */

// 필터 관련 정보를 저장하는 객체
var Filter = {};
// 사용자가 선택한 필터 정보를 저장하는 객체
var selectedFilters = {
	alcohol:{},
	sugarContent:{},
	body:{},
	price:{},
};

function calculateQuantiles(minValue, maxValue) {
	minValue = parseInt(minValue);
	maxValue = parseInt(maxValue); 
	var range = maxValue - minValue;
	var quantile2 = Math.floor(range * 1 / 4) + minValue;
	var quantile3 = Math.floor(range * 3 / 4) + minValue;
	
	return [quantile2, quantile3];
}
/**
 * 
 */
function getProductRangeValues() {
	$.ajax(Utils.baseUrl + "product/filter/range-get.mall", {
		method: "get",
		dataType: "json",
		async: false,
		success: function(data) {
			Filter.kind = data.kind;
			Filter.region = data.region;
			Filter.alcohol = data.alcohol;
			Filter.sugarContent = data.sugarContent;
			Filter.body = data.body;
			Filter.price = data.price;
			return Filter;
		},
		error: function(data) {
			snackbar('필터 조건의 범위를 가져올 수 없습니다.');
		}
	});
}
function initiateFilters() {
	// 와인 종류
	var kind = Filter.kind;
	$('.kind .filter__list').empty();
	for (var i = 0; i < kind.length; i++) {
		var li = '<li>' + 
					'<a href="'+ kind[i] +'" onclick="event.preventDefault(); toggleFilterValue(this, \'kind\')">' +
					kind[i] +
					'</a>' +
				'</li>';
		$('.kind .filter__list').append(li);
	}
	
	// 생산지
	var region = Filter.region;
	$('.region .filter__list').empty();
	for (var i = 0; i < region.length; i++) {
		var li = '<li>' + 
		'<a href="'+ region[i]['regionId'] +'" onclick="event.preventDefault(); toggleFilterValue(this, \'regionId\')">' +
		region[i]['regionName'] +
		'</a>' +
		'</li>';
		$('.region .filter__list').append(li);
	}
	
	// 도수
	var alcohol = Filter.alcohol;
	if (!selectedFilters.alcohol.min || !selectedFilters.alcohol.max) {
		var calculatedAlcohol = calculateQuantiles(alcohol['min'], alcohol['max']);
		selectedFilters.alcohol.min = calculatedAlcohol[0];
		selectedFilters.alcohol.max = calculatedAlcohol[1];
	}
	$( "#alcohol-range" ).slider({
      range: true,
      min: alcohol['min'],
      max: alcohol['max'],
      values: [selectedFilters.alcohol.min, selectedFilters.alcohol.max],
      slide: function( event, ui ) {
        $( "#alcohol-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
      }
    });
	$( "#alcohol-amount" ).val( $( "#alcohol-range" ).slider( "values", 0 ) +
		      " - " + $( "#alcohol-range" ).slider( "values", 1 ) );
	
	// 당도
	var sugarContent = Filter.sugarContent;
	if (!selectedFilters.sugarContent.min || !selectedFilters.sugarContent.max) {
		var calculatedSugarContent = calculateQuantiles(sugarContent['min'], sugarContent['max']);
		selectedFilters.sugarContent.min = calculatedSugarContent[0];
		selectedFilters.sugarContent.max = calculatedSugarContent[1];
	}
	$( "#sugar-range" ).slider({
		range: true,
		min: sugarContent['min'],
		max: sugarContent['max'],
		values: [selectedFilters.sugarContent.min, selectedFilters.sugarContent.max],
		slide: function( event, ui ) {
			$( "#sugar-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		}
	});
	$( "#sugar-amount" ).val( $( "#sugar-range" ).slider( "values", 0 ) +
			" - " + $( "#sugar-range" ).slider( "values", 1 ) );
	
	// 바디
	var body = Filter.body;
	if (!selectedFilters.body.min || !selectedFilters.body.max) {
		var calculatedBody = calculateQuantiles(body['min'], body['max']);
		selectedFilters.body.min = calculatedBody[0];
		selectedFilters.body.max = calculatedBody[1];
	}
	$( "#body-range" ).slider({
		range: true,
		min: body['min'], 
		max: body['max'],
		values: [selectedFilters.body.min, selectedFilters.body.max],
		slide: function( event, ui ) {
			$( "#body-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		}
	});
	$( "#body-amount" ).val( $( "#body-range" ).slider( "values", 0 ) +
			" - " + $( "#body-range" ).slider( "values", 1 ) );
	
	// 가격
	var price = Filter.price;
	if (!selectedFilters.price.min || !selectedFilters.price.max) {
		var calculatedPrice = calculateQuantiles(price['min'], price['max']);
		selectedFilters.price.min = calculatedPrice[0];
		selectedFilters.price.max = calculatedPrice[1];
	}
	$( "#price-range" ).slider({
		range: true,
		min: price['min'], 
		max: price['max'],
		values: [selectedFilters.price.min, selectedFilters.price.max],
		slide: function( event, ui ) {
			$( "#price-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		}
	});
	$( "#price-amount" ).val( $( "#price-range" ).slider( "values", 0 ) +
			" - " + $( "#price-range" ).slider( "values", 1 ) );
}

function toggleFilterValue(target, key) {
	$(target).toggleClass('active');
	var value = $(target).attr('href');
	if (!selectedFilters[key]) {
		selectedFilters[key] = value;
	} else {
		delete selectedFilters[key];
	}
}

function toggleIsFilterUsed() {
	console.log('hello');
	var value = $('#isFilterUsed').val() === 'true';
	console.log(!value);
	$('#isFilterUsed').val(!value);
}

function getFilteredListData() {
	$.ajax(Utils.baseUrl + "product/filter/list.mall", {
		method: "get",
		data: {
			"email": email,
			"articleId": $('input[type="hidden"]').val(),
			"category": $('input[type="text"]:eq(2)').val(),
			"title": $('input[type="text"]:eq(1)').val(),
			"content": $('textarea').val()
		},
		dataType: "json",
		success: function(data) {
			
		},
		error: function(data) {
			snackbar('필터가 적용된 상품을 가져오는 데 실패했습니다. 잠시 후 시도해주세요.');
		}
	});
}