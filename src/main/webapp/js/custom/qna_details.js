function write(category, title, content, regdate) {
	var String = '<h2>1:1 문의 상세보기</h2>'
	           + '<div class="input-group">'
	           + '  <table class="table table-striped table-bordered">'
	           + '    <thead>'
	           + '      <tr>'
	           + '        <th>글 제목</th>'
	           + '        <td colspan="3"><input type="text" class="form-control" value=""></td>'
	           + '      </tr>'
	           + '      <tr>'
	           + '        <th>카테고리</th>'
	           + '        <td><input type="text" class="form-control" value=""></td>'
	           + '        <th>등록일</th>'
	           + '        <td><input type="text" class="form-control" value=""></td>'
	           + '      </tr>'
	           + '    </thead>'
	           + '    <tbody>'
	           + '      <tr>'
	           + '        <td colspan="4">'
	           + '          <textarea class="form-control" rows="15"></textarea>'
	           + '        </td>'
	           + '      </tr>'
	           + '    </tbody>'
	           + '  </table>'
	           + '</div>';
	           + '<input type="button" value="댓글">';
	
	$('.col-md-12').append(String);
}