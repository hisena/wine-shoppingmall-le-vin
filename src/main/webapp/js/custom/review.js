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
			var String = '<tr class="danger">'
		               + '  <th>번호</th>'
			           + '  <th>제목</th>'
			           + '  <th>작성자</th>'
			           + '  <th>작성일</th>'
			           + '  <th>별점</th>'
		               + '</tr>';
			// 구매후기 리스트 풀력
			for (var i = 0; i < reviewList.length; i++) {
            	if (reviewList[i].deleteYN == 'Y') {
            		String += '<tr><td colspan="5" style="text-align: center;">삭제된 게시글입니다.</td></tr>';
            	} else {
            		String += '<tr>'
		                    + '  <td>'+ reviewList[i].reviewId +'</td>'
    	                    + '  <td>'+ reviewList[i].title +'</td>'
    	                    + '  <td>'+ reviewList[i].email +'</td>'
    	                    + '  <td>'+ reviewList[i].regdate  +'</td>'
    	                    + '  <td>'+ reviewList[i].grade  +'</td>'
                            + '</tr>';
            	}
			}
			$('.productReview').append(String);
			// 페이지네이션
			$('.pagination-lg').empty();
			page(pageInfo.currentPage, pageInfo.endPage, pageInfo.next, pageInfo.prev, pageInfo.startPage, pageInfo.totalCount);
		},
		error: function(data) {
			alert('에러발생');
		}
	});
}