function reviewCreate(productId) {
	var email = getCookie("email");
	var String = '<table class="table table-striped table-bordered">'
               + '  <thead>'
               + '    <tr>'
               + '      <th style="vertical-align:middle; text-align: center;">글 제목</th>'
               + '      <td colspan="3"><input type="text" class="form-control"></td>'
               + '    </tr>'
               + '    <tr>'
               + '      <th style="vertical-align:middle; text-align: center;">평점</th>'
               + '      <td><input type="text" class="form-control"></td>'
               + '      <th style="vertical-align:middle; text-align: center;">등록일</th>'
               + '      <td><input type="text" class="form-control" readonly></td>'
               + '    </tr>'
               + '  </thead>'
               + '  <tbody>'
               + '    <tr>'
               + '      <td colspan="4">'
               + '        <textarea class="form-control" rows="10"></textarea>'
               + '      </td>'
               + '    </tr>'
               + '  </tbody>'
               + '</table>'
               + '<input type="button" value="목록" id="back" onclick="getReviewList('+ productId +')">'
               + '<input type="button" value="글쓰기" id="create" style="margin-right: 10px">';

	$('.product_details').append(String);
	
	// 구매후기 쓰기
	$(document).on('click', '#create', function(event) {
		$.ajax(Utils.baseUrl + "product/review-add.mall", {
			method: "post",
			data: {
				"productId": productId,
				"email": email,
				"grade": $('input[type="text"]:eq(2)').val(),
				"title": $('input[type="text"]:eq(1)').val(),
				"content": $('textarea').val()
			},
			dataType: "json",
			success: function(data) {
				if (data.addReviewResult) {
					$('.product_details').empty();
					getReviewList(productId);
				}
			},
			error: function(data) {
				snackbar('알수없는 오류가 발생했습니다.')
			}
		});
	});
}