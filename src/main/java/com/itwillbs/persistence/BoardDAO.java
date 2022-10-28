package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardDAO {
	//글번호 가져오기
	public Integer getMaxNo();
	
	//글쓰기
	public void boardWrite(BoardVO vo);
	
	//글목록 가져오기
	public List<BoardVO> getBoardList(PageVO vo);
	
	//전체 글 개수
	public int getBoardCount();
	
	//검색하기
	public List<BoardVO> getBoardListSearch(PageVO vo);

	//검색의 전체 글 개수
	public int getBoardCountSearch(PageVO vo);
}
