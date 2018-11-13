function qnaDetails(category, title, content, regdate, articleId) {
	var email = getCookie("email");
	var String = '<table class="table table-striped table-bordered">'
	           + '  <thead>'
	           + '    <tr>'
	           + '      <input type="hidden" value="'+ articleId +'">'
	           + '      <th style="vertical-align:middle; text-align: center;">글 제목</th>'
	           + '      <td colspan="3"><input type="text" class="form-control" value="'+ title +'"></td>'
	           + '    </tr>'
	           + '    <tr>'
	           + '      <th style="vertical-align:middle; text-align: center;">카테고리</th>'
	           + '      <td><input type="text" class="form-control" value="'+ category +'"></td>'
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
	           + '<input type="button" value="댓글" id="reply" style="float: left" onclick="replyListQna(' + articleId + ')">'
	           + '<input type="button" value="목록" id="back" onclick="getQnaList()">'
	           + '<input type="button" value="수정" id="update" style="margin: 0 10px 20px 0">'
	           + '<input type="button" value="삭제" id="delete" style="margin: 0 10px 20px 0" onclick="deletePrivateQna(' + articleId + ')">'
	           + '<div class="col-md-12" id="replySection">'
	           + '</div>'
	
	$('#qnaSection').append(String);
	
	// 게시글 수정
	$(document).on('click', '#update', function(event) {
		$.ajax(Utils.baseUrl + "privateqna/qna-edit.mall", {
			method: "post",
			data: {
				"email": email,
				"articleId": $('input[type="hidden"]').val(),
				"category": $('input[type="text"]:eq(2)').val(),
				"title": $('input[type="text"]:eq(1)').val(),
				"content": $('textarea').val()
			},
			dataType: "json",
			success: function(data) {
				if (data.editQnaResult) {
					getQnaList();
				}
			},
			error: function(data) {
				alert('에러발생');
			}
		});
	});
}
// 게시글 삭제
function deletePrivateQna(id) {
	$.ajax(Utils.baseUrl + "privateqna/qna-remove.mall", {
		method: "post",
		data: {
			"articleId": id
		},
		dataType: "json",
		success: function(data) {
			if (data.removeQnaResult) {
				getQnaList();
			}
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
// 댓글 리스트 출력
function replyListQna(id) {
	$('#replySection').empty();
	$.ajax(Utils.baseUrl + "privateqna/comment-list.mall", {
		method: "get",
		data: {
			"articleId": id
		},
		dataType: "json",
		success: function(data) {
			var listResult = data.listResult;
			var String = '<input type="text" class="form-control" style="width: 80%; float: left; display: inline-block">'
				       + '<input type="button" class="btn btn-default" value="댓글쓰기" onclick="replyWriteQna()" style="float: left; display: inline-block">'
					   + '<table class="table table-striped table-bordered" style="">'
				       + '  <tr>'
				       + '    <th style="width: 5%">댓글번호</th>'
				       + '    <th style="width: 20%">이메일</th>'
				       + '    <th style="width: 50%">내용</th>'
				       + '    <th style="width: 8%">등록일</th>'
				       + '    <th style="width: 17%"></th>'
				       + '  </tr>';
			if (data.listResult != 'false') {
				for (var i = 0; i < listResult.length; i++) {
					String += '<tr>'
					if (listResult[i].deleteYN == 'Y') {
						String += '<td colspan="5" style="text-align: center;">삭제된 댓글입니다.</td>';
					} else {
						String += '<td id="commentId">'+ listResult[i].commentId +'</td>'
					            + '<td>'+ listResult[i].email +'</td>'
					            + '<td><input type="text" value="'+ listResult[i].content +'" id="replyContent"></td>'
					            + '<td>'+ listResult[i].regdate +'</td>'
					            + '<td>'
					            + '<input type="button" value="수정" onclick="replyUpdateQna()" id="replyUpdate">'
					            + '<input type="button" value="삭제" onclick="replyDeleteQna()" id="replyDelete" style="margin-right: 10px">'
					            + '</td>';
					}
					String += '</tr>';
				}
				String += '</table>';
				$('#replySection').append(String);
				$('#replySection').toggle();
			} else {
				String += '<tr><td colspan="5" style="text-align: center;">등록된 댓글이 없습니다.</td></tr></table>'
				$('#replySection').append(String);
				$('#replySection').toggle();
			}
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
// 댓글 등록
function replyWriteQna() {
	var email = getCookie("email");
	$.ajax(Utils.baseUrl + "privateqna/comment-add.mall", {
		method: "post",
		data: {
			"email": email,
			"category": $('input[type=text]').eq(2).val(),
			"content": $('input[type=text]').eq(4).val(),
			"articleId": $('input[type=hidden]').val()
		},
		dataType: "json",
		success: function(data) {
			if (data.addCommentResult) {
				replyListQna();
			}
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
// 댓글 삭제
function replyDeleteQna() {
	$.ajax(Utils.baseUrl + "privateqna/comment-remove.mall", {
		method: "post",
		data: {
			"commentId": $('#commentId').text()
		},
		dataType: "json",
		success: function(data) {
			if (data.removeCommentResult) {
				replyListQna();
			}
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
// 댓글 수정
function replyUpdateQna() {
	$.ajax(Utils.baseUrl + "privateqna/comment-edit.mall", {
		method: "post",
		data: {
			"commentId": $('#commentId').text(),
			"content": $('#replyContent').val()
		},
		dataType: "json",
		success: function(data) {
			if (data.editCommentResult) {
				replyListQna();
			}
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}