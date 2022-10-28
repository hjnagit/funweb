package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;

public interface BoardService {
	
	//글쓰기
	public void boardWrite(BoardVO vo);
	
	//글목록 가져오기
	public List<BoardVO> getBoardList(PageVO vo);
	
	//전체 글 개수
	public int getBoardCount();
	
	//검색하기
	public List<BoardVO> getBoardListSearch(PageVO vo);
	
	//검색하기의 전체 글 개수
	public int getBoardCountSearch(PageVO vo);
}
