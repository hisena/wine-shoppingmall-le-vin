function getQnaList() {
	var email = getCookie("email");
	// 처음 로딩시 ajax통신
	$.ajax(Utils.baseUrl + "privateqna/qna-list.mall", {
		method: "get",
		dataType: "json",
		data: {
			"email": email
		},
		success: function(data) {
			var qnaList = data.qnaList;
			var pageInfo = data.pageInfo;
			var String = '<div class="table-responsive">'
 	                   + '  <table id="mytable" class="table table-hover qnaDetails">'
 	                   + '    <thead>'
 	                   + '      <tr class="danger">'
 	                   + '        <th style="width: 10%; text-align: center;">번호</th>'
 	                   + '        <th style="width: 10%; text-align: center;">카테고리</th>'
 	                   + '        <th style="width: 60%;">제목</th>'
 	                   + '        <th style="width: 20%; text-align: center;">등록일</th>'
 	                   + '      </tr>'
 	                   + '    </thead>'
 	                   + '    <tbody>';
            // 상품 리스트 출력
   			$('#qnaSection').empty();
            for (var i = 0; i < qnaList.length; i++) {
            	if (qnaList[i].deleteYN == '1') {
            		String += '<tr><td colspan="4" style="text-align: center;">삭제된 게시글입니다.</td></tr>';
            	} else {
            		String += '<tr class="qnaList">'
            			    + '  <input type="hidden" value="'+ qnaList[i].articleId +'">'
		                    + '  <td style="text-align: center">'+ (qnaList.length-i) +'</td>'
    	                    + '  <td style="text-align: center">'+ qnaList[i].category +'</td>'
    	                    + '  <td>'+ qnaList[i].title +'</td>'
    	                    + '  <td style="text-align: center">'+ qnaList[i].regdate  +'</td>'
                            + '</tr>';
            	}
			}
            String += '</tbody>'
                    + '  </table>'
                    + '</div>'
                    + '<input type="button" value="글쓰기" id="write">'
                    + '<div class="row mt--60">'
                    + '  <div class="col-md-12">'
                    + '    <ul class="pagination pagination-lg">'
                    + '    </ul>'
                    + '  </div>'
                    + '</div>';
			$('#qnaSection').append(String);
			// 페이지네이션
			$('.pagination-lg').empty();
			page(pageInfo.currentPage, pageInfo.endPage, pageInfo.next, pageInfo.prev, pageInfo.startPage, pageInfo.totalCount)
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
$(function() {
	// 게시글 목록 Ajax
	getQnaList();
	// 게시글 상세보기 Ajax
	$(document).off("click").on('click', '.qnaList', function(event) {
		$.ajax(Utils.baseUrl + "privateqna/qna-detail.mall", {
			method: "get",
			data: {
				"articleId": $(event.target).parent().find('input[type="hidden"]').val()
			},
			dataType: "json",
			success: function(data) {
				var privateQna = data.privateQna;
				// 게시글 상세보기 함수
				$('#qnaSection').empty();
				qnaDetails(privateQna.category, privateQna.title, privateQna.content, privateQna.regdate, privateQna.articleId)
			},
			error: function(data) {
				alert('에러발생');
			}
		});
	});
	// 게시글 쓰기 Ajax
	$(document).off("click").on('click', 'input[type="button"]', function(event) {
		$('#qnaSection').empty();
		qnaWrite();
	});
});