function getReviewList(productId) {
	// 처음 로딩시 ajax통신
	$.ajax(Utils.baseUrl + "product/review-list.mall", {
		method: "get",
		dataType: "json",
		data: {
			"productId": productId
		},
		success: function(data) {
			var reviewList = data.reviewList;
			var pageInfo = data.pageInfo;
			var String = '<table class="table table-hover">'
				       + '  <tr class="danger">'
		               + '    <th>번호</th>'
			           + '    <th>제목</th>'
			           + '    <th>작성자</th>'
			           + '    <th>작성일</th>'
			           + '    <th>별점</th>'
		               + '  </tr>';
			// 구매후기 리스트 출력
			for (var i = 0; i < reviewList.length; i++) {
            	if (reviewList[i].deleteYN == 'Y') {
            		String += '<tr><td colspan="5" style="text-align: center;">삭제된 게시글입니다.</td></tr>';
            	} else {
            		String += '<tr onclick="readReview(' + reviewList[i].reviewId + ')">'
		                    + '  <td>'+ reviewList[i].reviewId +'</td>'
    	                    + '  <td>'+ reviewList[i].title +'</td>'
    	                    + '  <td>'+ reviewList[i].email +'</td>'
    	                    + '  <td>'+ reviewList[i].regdate  +'</td>'
    	                    + '  <td>'+ reviewList[i].grade  +'</td>'
                            + '</tr>';
            	}
			}
			String += '</table>'
    		        + '<input type="button" value="글쓰기" id="write" onclick="writeReview()">'
    		        + '<div class="row mt--60">'
    		        + '  <div class="col-md-12">'
                    + '    <ul class="pagination pagination-lg">'
                    + '    </ul>'
                    + '  </div>'
                    + '</div>'
			$('.product_details').append(String);
			// 페이지네이션
			$('.pagination-lg').empty();
			page(pageInfo.currentPage, pageInfo.endPage, pageInfo.next, pageInfo.prev, pageInfo.startPage, pageInfo.totalCount);
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
// 구매후기 상세보기
function readReview(id) {
	$.ajax(Utils.baseUrl + "product/review-detail.mall", {
		method: "get",
		data: {
			"reviewId": id
		},
		dataType: "json",
		success: function(data) {
			var review = data.review;
			// 게시글 상세보기 함수
			$('.product_details').empty();
			reviewDetails(review.grade, review.title, review.content, review.regdate, review.productId)
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}
// 게시글 쓰기
function writeReview() {
	$('.product_details').empty();
	reviewCreate($('#productId').val());
}