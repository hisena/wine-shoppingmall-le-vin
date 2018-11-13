function reviewDetails(grade, title, content, regdate, reviewId, productId) {
	var String = '<table class="table table-striped table-bordered">'
	           + '  <thead>'
	           + '    <tr>'
	           + '      <input type="hidden" value="'+ reviewId +'" id="reviewId">'
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
	           + '<input type="button" value="댓글" id="reply" style="float: left" onclick="replyListReview(' + reviewId + ')">'
	           + '<input type="button" value="목록" id="back" onclick="getReviewList('+ productId +')">'
	           + '<input type="button" value="수정" id="update" style="margin: 0 10px 20px 0">'
	           + '<input type="button" value="삭제" id="delete" style="margin: 0 10px 20px 0" onclick="deleteReview(' + reviewId + ')">'
	           + '<div class="col-md-12" id="productReplySection">'
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
				snackbar('알수없는 오류가 발생했습니다.')
			}
		});
	});
}
// 구매후기 삭제
function deleteReview(id) {
	$.ajax(Utils.baseUrl + "product/review-remove.mall", {
		method: "post",
		data: {
			"reviewId": id
		},
		dataType: "json",
		success: function(data) {
			if (data.removeReviewResult) {
				$('.product_details').empty();
				getReviewList($('#productId').val());
			}
		},
		error: function(data) {
			snackbar('알수없는 오류가 발생했습니다.')
		}
	});
}
// 구매후기 댓글 리스트 출력
function replyListReview(id) {
	$('#productReplySection').empty();
	$.ajax(Utils.baseUrl + "product/review-comment-list.mall", {
		method: "get",
		data: {
			"reviewId": id
		},
		dataType: "json",
		success: function(data) {
			var reviewCommentResult = data.reviewCommentResult;
			var String = '<input type="text" class="form-control" style="width: 80%; float: left; display: inline-block">'
				       + '<input type="button" class="btn btn-default" value="댓글쓰기" onclick="replyWriteReview()" style="float: left; display: inline-block">'
					   + '<table class="table table-striped table-bordered" style="">'
				       + '  <tr>'
				       + '    <th style="width: 5%">댓글번호</th>'
				       + '    <th style="width: 20%">이메일</th>'
				       + '    <th style="width: 50%">내용</th>'
				       + '    <th style="width: 8%">등록일</th>'
				       + '    <th style="width: 17%"></th>'
				       + '  </tr>';
			if (reviewCommentResult != 'false') {
				for (var i = 0; i < reviewCommentResult.length; i++) {
					String += '<tr>'
					if (reviewCommentResult[i].deleteYN == 'Y') {
						String += '<td colspan="5" style="text-align: center;">삭제된 댓글입니다.</td>';
					} else {
						String += '<td id="commentId">'+ reviewCommentResult[i].reviewId +'</td>'
					            + '<td>'+ reviewCommentResult[i].email +'</td>'
					            + '<td><input type="text" value="'+ reviewCommentResult[i].content +'" id="replyContent"></td>'
					            + '<td>'+ reviewCommentResult[i].regdate +'</td>'
					            + '<td>'
					            + '<input type="button" value="수정" onclick="replyUpdateReview()" id="replyUpdate">'
					            + '<input type="button" value="삭제" onclick="replyDeleteReview()" id="replyDelete" style="margin-right: 10px">'
					            + '</td>';
					}
					String += '</tr>';
				}
				String += '</table>';
				$('#productReplySection').append(String);
				$('#productReplySection').toggle();
			} else {
				String += '<tr><td colspan="5" style="text-align: center;">등록된 댓글이 없습니다.</td></tr></table>'
				$('#productReplySection').append(String);
				$('#productReplySection').toggle();
			}
		},
		error: function(data) {
			snackbar('알수없는 오류가 발생했습니다.')
		}
	});
}
// 구매후기 댓글 등록
function replyWriteReview() {
	var email = getCookie("email");
	$.ajax(Utils.baseUrl + "product/review-comment-add.mall", {
		method: "post",
		data: {
			"email": email,
			"reviewId": $('#reviewId').val(),
			"content": $('input[type=text]').eq(4).val(),
			"productId": $('#productId').val()
		},
		dataType: "json",
		success: function(data) {
			if (data.addReviewCommentResult) {
				replyListReview();
			}
		},
		error: function(data) {
			snackbar('알수없는 오류가 발생했습니다.')
		}
	});
}
// 구매하기 댓글 삭제
function replyDeleteReview() {
	$.ajax(Utils.baseUrl + "product/review-comment-remove.mall", {
		method: "post",
		data: {
			"commentId": $('#commentId').text()
		},
		dataType: "json",
		success: function(data) {
			if (data.removeReviewCommentResult) {
				replyListReview();
			}
		},
		error: function(data) {
			snackbar('알수없는 오류가 발생했습니다.')
		}
	});
}
// 구매하기 댓글 수정
function replyUpdateReview() {
	$.ajax(Utils.baseUrl + "product/review-comment-edit.mall", {
		method: "post",
		data: {
			"commentId": $('#commentId').text(),
			"content": $('#replyContent').val()
		},
		dataType: "json",
		success: function(data) {
			if (data.editReviewCommentResult) {
				replyListReview();
			}
		},
		error: function(data) {
			snackbar('알수없는 오류가 발생했습니다.')
		}
	});
}