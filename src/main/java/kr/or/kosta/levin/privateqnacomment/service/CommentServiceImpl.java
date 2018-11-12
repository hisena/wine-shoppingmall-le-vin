package kr.or.kosta.levin.privateqnacomment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.leeseungeun.webframework.annotations.Bean;
import io.github.leeseungeun.webframework.annotations.Inject;
import io.github.leeseungeun.webframework.enums.BeanType;
import kr.or.kosta.levin.privateqna.domain.Pagination;
import kr.or.kosta.levin.privateqna.domain.PaginationManager;
import kr.or.kosta.levin.privateqna.domain.PrivateQna;
import kr.or.kosta.levin.privateqnacomment.dao.CommentDao;
import kr.or.kosta.levin.privateqnacomment.domain.PrivateQnaComment;

/**
 * PrivateQnaComment와 관련된 비즈니스 로직 수행을 위한 Service 객체
 * 
 * @author 류세은
 *
 */
@Bean(type = BeanType.Service)
public class CommentServiceImpl implements CommentService {

	// dao 선언
	@Inject
	private CommentDao commentDao;

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	// 1:1문의글의 댓글 등록
	@Override
	public boolean addComment(Map<String, String> parameter) throws Exception {
		// Mybatis 실행 결과를 받기 위한 변수
		boolean addResult = false;
		boolean flag = false;
		// 1:1문의글의 댓글 등록
		addResult = commentDao.createComment(parameter);
		// 등록에 성공하면
		if (addResult) {
			flag = true;
		}
		return flag;
	}
	
	//1:1문의글의 댓글 리스트 조회
	@Override
	public Map<String, Object> listComment(int parentId) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		
		//댓글 리스트
		List<PrivateQnaComment> list = commentDao.list(parentId);
		
		// controller로 넘겨 주기 위해 map에 담아주기
		map.put("commentList", list);
		
		return map;
	}
}
