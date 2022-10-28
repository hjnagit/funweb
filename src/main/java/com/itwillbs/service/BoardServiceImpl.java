package com.itwillbs.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	//객체 생성
	@Autowired
	private BoardDAO boardDAO;
	

	//글쓰기
	@Override
	public void boardWrite(BoardVO vo) {
		//pass name subject content - 넘어오는 정보
		//bno readcount re_ref re_lev re_seq date ip
		
		vo.setReadcount(0);
		vo.setRe_lev(0);
		vo.setRe_seq(0);
		//vo.setDate(new Date(System.currentTimeMillis()));
		vo.setDate(new Date(System.currentTimeMillis()));
		
		//bno
		if(boardDAO.getMaxNo() == null) {
			//게시판 글 없는 경우
			vo.setBno(1);
			vo.setRe_ref(1);
		}else {
			//게시판 글 있음 -> 최대번호+1
			vo.setBno(boardDAO.getMaxNo()+1);
			vo.setRe_ref(boardDAO.getMaxNo()+1);
		}
		
		//메서드 호출
		boardDAO.boardWrite(vo);
	}


	//글목록
	@Override
	public List<BoardVO> getBoardList(PageVO vo) {
		// startRow, endRow 구하기
		int startRow = (vo.getCurrentPage()-1)*vo.getPageSize()+1;
		int endRow = vo.getCurrentPage() * vo.getPageSize();
		
		vo.setStartRow(startRow-1); // 여기서 -1을 해준다 1번부터 가져오려면 -1을 해야한다
		vo.setEndRow(endRow);
		
		//글목록 가져오기
		return boardDAO.getBoardList(vo);
	}

	
	//전체 글 개수
	@Override
	public int getBoardCount() {
		
		return boardDAO.getBoardCount();
	}

	//검색하기
	@Override
	public List<BoardVO> getBoardListSearch(PageVO vo) {
		// startRow, endRow 구하기
		int startRow = (vo.getCurrentPage()-1)*vo.getPageSize()+1;
		int endRow = vo.getCurrentPage() * vo.getPageSize();
		
		vo.setStartRow(startRow-1); // 여기서 -1을 해준다 1번부터 가져오려면 -1을 해야한다
		vo.setEndRow(endRow);
		
		//글목록 가져오기
		return boardDAO.getBoardListSearch(vo);
	}

	//검색의 전체 글 개수
	@Override
	public int getBoardCountSearch(PageVO vo) {
		return boardDAO.getBoardCountSearch(vo);
	}
	
	
	
	

}
