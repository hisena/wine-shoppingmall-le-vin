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
	           + '<input type="button" value="댓글" id="reply" style="float: left">'
	           + '<input type="button" value="수정" id="update">'
	           + '<input type="button" value="삭제" id="delete" style="margin-right: 10px" onclick="deletePrivateQna(' + articleId + ')">';
	
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