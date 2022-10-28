package com.itwillbs.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	//마이바티스 객체생성
	@Autowired
	private SqlSession sqlSession;
	
	//boardMapper 가상이름 정의
	private static final String NAMESPACE = "com.itwillbs.mapper.BoardMapper";
	
	
	//글번호
	@Override
	public Integer getMaxNo() {
		return sqlSession.selectOne(NAMESPACE+".getMaxNo");
	}
	
	//글쓰기
	@Override
	public void boardWrite(BoardVO vo) {
		sqlSession.insert(NAMESPACE+".boardWrite", vo);
	}
	
	//글목록 가져오기
	@Override
	public List<BoardVO> getBoardList(PageVO vo) {
		return sqlSession.selectList(NAMESPACE+".getBoardList", vo);
	}

	//전체 글 개수
	@Override
	public int getBoardCount() {
		return sqlSession.selectOne(NAMESPACE+".getBoardCount");
	}
	
	//검색하기
	@Override
	public List<BoardVO> getBoardListSearch(PageVO vo) {
		return sqlSession.selectList(NAMESPACE+".getBoardListSearch", vo);
	}
	
	//검색의 전체 글 개수
	@Override
	public int getBoardCountSearch(PageVO vo) {
		return sqlSession.selectOne(NAMESPACE+".getBoardCountSearch", vo);
	}
	
	
	
}
