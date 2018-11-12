/**
 * 와인 상품 목록과 모든 게시판에서 사용할 수 있는 페이지네이션 함수
 * 
 * @author 김홍기
 */
function page(currentPage, endPage, next, prev, startPage, totalCount) {
	var String = '';
	if (currentPage) {
		if (prev) {
			String += '<li><a href='+ (startPage - 1) +'><span class="glyphicon glyphicon-backward"></span></a></li>';
		}
		if (currentPage != 1) {
			String += '<li><a href='+ (currentPage-1) +'><span class="glyphicon glyphicon-chevron-left"></span></a></li>';
		}
		for (var i=startPage; i<=endPage; i++) {
			if (i == currentPage) {
				String += '<li class="page-item active"><a href='+ i +'>' + i + '</a></li>';
			} else {
				String += '<li><a href='+ i +'>' + i + '</a></li>';
			}
		}
		if (currentPage != endPage) {
			String += '<li><a href='+ (currentPage+1) +'><span class="glyphicon glyphicon-chevron-right"></span></a></li>';
		}
		if (next) {
			String += '<li><a href='+ (endPage + 1) +'><span class="glyphicon glyphicon-forward"></span></a></li>';
		}
	}
	
	$('.pagination-lg').append(String);
}