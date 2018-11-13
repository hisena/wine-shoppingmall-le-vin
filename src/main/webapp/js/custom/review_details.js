function reviewDetails(grade, title, content, regdate, reviewId, productId) {
	var String = '<table class="table table-striped table-bordered">'
	           + '  <thead>'
	           + '    <tr>'
	           + '      <input type="hidden" value="'+ reviewId +'">'
	           + '      <th style="vertical-align:middle; text-align: center;">글 제목</th>'
	           + '      <td colspan="3"><input type="text" class="form-control" value="'+ title +'"></td>'
	           + '    </tr>'
	           + '    <tr>'
	           + '      <th style="vertical-align:middle; text-align: center;">별점</th>'
	           + '      <td><input type="text" class="form-control" value="'+ grade +'"></td>'
	           + '      <th style="vertical-align:middle; text-align: center;">등록일</th>'
	           + '      <td><input type="text" class="form-control" value="'+ regdate +'" readonly></td>'
	           + '    </tr>'
	           + '  </thead>'
	           + '  <tbody>'
	           + '    <tr>'
	           + '      <td colspan="4">'
	           + '        <textarea class="form-control" rows="10">'+ content +'</textarea>'
	           + '      </td>'
	           + '    </tr>'
	           + '  </tbody>'
	           + '</table>'
	           + '<input type="button" value="댓글" id="reply" style="float: left" onclick="replyListQna(' + reviewId + ')">'
	           + '<input type="button" value="목록" id="back" onclick="getReviewList('+ productId +')">'
	           + '<input type="button" value="수정" id="update" style="margin: 0 10px 20px 0">'
	           + '<input type="button" value="삭제" id="delete" style="margin: 0 10px 20px 0" onclick="deletePrivateQna(' + reviewId + ')">'
	           + '<div class="col-md-12" id="replySection">'
	           + '</div>'
	
	$('.pagination-lg').empty();
	$('.product_details').append(String);
	
	// 구매후기 수정
	$(document).on('click', '#update', function(event) {
		$.ajax(Utils.baseUrl + "product/review-edit.mall", {
			method: "post",
			data: {
				"reviewId": reviewId,
				"grade": $('input[type="text"]:eq(2)').val(),
				"title": $('input[type="text"]:eq(1)').val(),
				"content": $('textarea').val()
			},
			dataType: "json",
			success: function(data) {
				if (data.editReviewResult) {
					$('.product_details').empty();
					getReviewList(productId);
				}
			},
			error: function(data) {
				alert('에러발생');
			}
		});
	});
}