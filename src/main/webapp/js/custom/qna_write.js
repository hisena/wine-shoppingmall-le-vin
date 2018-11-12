function qnaWrite() {
	var email = getCookie("email");
	var String = '<table class="table table-striped table-bordered">'
               + '  <thead>'
               + '    <tr>'
               + '      <th style="vertical-align:middle; text-align: center;">글 제목</th>'
               + '      <td colspan="3"><input type="text" class="form-control"></td>'
               + '    </tr>'
               + '    <tr>'
               + '      <th style="vertical-align:middle; text-align: center;">카테고리</th>'
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
               + '<input type="button" value="글쓰기" id="create">';

	$('#qnaSection').append(String);
	
	// 게시글 쓰기
	$(document).on('click', '#create', function(event) {
		$.ajax(Utils.baseUrl + "privateqna/qna-add.mall", {
			method: "post",
			data: {
				"email": email,
				"category": $('input[type="text"]:eq(2)').val(),
				"title": $('input[type="text"]:eq(1)').val(),
				"content": $('textarea').val()
			},
			dataType: "json",
			success: function(data) {
				if (data.addQnaResult) {
					getQnaList();
				}
			},
			error: function(data) {
				alert('에러발생');
			}
		});
	});
}