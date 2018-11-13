/**
 * 상품 필터 관련 함수 집합 
 * 
 * @author 이승은
 */

// 필터 관련 정보를 저장하는 객체
var Filter = {};

function calculateQuantiles(minValue, maxValue) {
	minValue = parseInt(minValue);
	maxValue = parseInt(maxValue); 
	var range = maxValue - minValue;
	var quantile2 = Math.floor(range * 2 / 4) + minValue;
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
function initiateFilters(selectedFilters) {
	// 와인 종류
	var kind = Filter.kind;
	$('.kind .filter__list').empty();
	for (var i = 0; i < kind.length; i++) {
		var li = '<li>' + 
					'<a href="'+ kind[i] +'">' +
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
		'<a href="'+ region[i]['regionId'] +'">' +
		region[i]['regionName'] +
		'</a>' +
		'</li>';
		$('.region .filter__list').append(li);
	}
	
	// 도수
	var alcohol = Filter.alcohol;
	var alcoholValues = selectedFilters ? selectedFilters.alcohol : calculateQuantiles(alcohol['min'], alcohol['max']);
	$( "#alcohol-range" ).slider({
      range: true,
      min: alcohol['min'],
      max: alcohol['max'],
      values: alcoholValues,
      slide: function( event, ui ) {
        $( "#alcohol-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
      }
    });
	$( "#alcohol-amount" ).val( $( "#alcohol-range" ).slider( "values", 0 ) +
		      " - " + $( "#alcohol-range" ).slider( "values", 1 ) );
	
	// 당도
	var sugarContent = Filter.sugarContent;
	var sugarContentValues = selectedFilters ? selectedFilters.sugarContent : calculateQuantiles(sugarContent['min'], sugarContent['max']);
	$( "#sugar-range" ).slider({
		range: true,
		min: sugarContent['min'],
		max: sugarContent['max'],
		values: sugarContentValues,
		slide: function( event, ui ) {
			$( "#sugar-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		}
	});
	$( "#sugar-amount" ).val( $( "#sugar-range" ).slider( "values", 0 ) +
			" - " + $( "#sugar-range" ).slider( "values", 1 ) );
	
	// 바디
	var body = Filter.body;
	var bodyValues = selectedFilters ? selectedFilters.body : calculateQuantiles(body['min'], body['max']);
	$( "#body-range" ).slider({
		range: true,
		min: body['min'], 
		max: body['max'],
		values: bodyValues,
		slide: function( event, ui ) {
			$( "#body-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		}
	});
	$( "#body-amount" ).val( $( "#body-range" ).slider( "values", 0 ) +
			" - " + $( "#body-range" ).slider( "values", 1 ) );
	
	// 가격
	var price = Filter.price;
	var priceValues = selectedFilters ? selectedFilters.price : calculateQuantiles(price['min'], price['max']);
	$( "#price-range" ).slider({
		range: true,
		min: price['min'], 
		max: price['max'],
		values: priceValues,
		slide: function( event, ui ) {
			$( "#price-amount" ).val( ui.values[ 0 ] + " - " + ui.values[ 1 ] );
		}
	});
	$( "#price-amount" ).val( $( "#price-range" ).slider( "values", 0 ) +
			" - " + $( "#price-range" ).slider( "values", 1 ) );
}