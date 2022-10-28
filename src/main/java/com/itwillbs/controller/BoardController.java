package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;

@Controller
public class BoardController {
	//객체생성
	@Autowired
	private BoardService boardService;
	
	
	// 글쓰기페이지로 이동
	// 가상주소 -> http://localhost:8088/FunWeb/board/write
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write() {
		
		// /WEB-INF/views/center/write.jsp
		// 가상주소 변경없이 jsp 이동 - 그냥 주소 적기
		return "center/write";
	}
	
	// 글쓰기
	// 가상주소 -> http://localhost:8088/FunWeb/board/writePro
	@RequestMapping(value = "/board/writePro", method = RequestMethod.POST)
	public String writePro(BoardVO vo) {
		System.out.println(vo);
		//글쓰기 작업
		//BoardService/BoardServiceImpl
		//BoardDAO/BoardDAOImpl
		//boardMapper.xml
		//BoardVO
		
		boardService.boardWrite(vo);
		
		// 가상주소 변경하면서 이동
		return "redirect:/board/list";
	}
	
	
	//글목록
	// http://localhost:8088/FunWeb/board/list
	// http://localhost:8088/FunWeb/board/list?pageNum=3
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, Model model) {
		// 목록 데이터를 들고
		
		// 페이지번호 받아오기
		String pageNum = request.getParameter("pageNum");
		//페이지넘버가 있는 경우, 없는 경우가 있으니까
		//없을 때는 1로 설정해준다
		if(pageNum == null) {
			pageNum="1";
		}
		
		int pageSize = 10; //페이지 사이즈는 15로 설정
		int currentPage = Integer.parseInt(pageNum); //현재페이지
		
		// 페이지 관련 VO를 만들어서 한번에 담아서 가져가야한다
		// PageVO에 페이징 관련 정보를 담아서 감
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum);
		vo.setPageSize(pageSize);
		vo.setCurrentPage(currentPage);
		
		//게시판 글리스트를 가져온다
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		//전체 글의 개수
		int cnt = boardService.getBoardCount();
		//페이지 처리
		int pageCount = cnt/pageSize + (cnt%pageSize == 0? 0:1);
		int pageBlock = 3;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		vo.setCnt(cnt);
		vo.setPageCount(pageCount);
		vo.setPageBlock(pageBlock);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("vo", vo);
		
		// /WEB-INF/views/center/notice.jsp
		// 가상주소 변경없이 jsp 이동 - 그냥 주소 적기
		return "center/notice";
	}

	
	//검색하기
	//boardList랑 같이 해도 된다 search값이 있냐 없냐만 다르게 하면 되니까
	// http://localhost:8088/FunWeb/board/listSearch
	// http://localhost:8088/FunWeb/board/listSearch?pageNum=3
	@RequestMapping(value = "/board/listSearch", method = RequestMethod.GET)
	public String listSearch(HttpServletRequest request, Model model) {
		// 검색어 가져오기
		String search = request.getParameter("search");
		// like '%검색어%'
		String search2 = "%"+search+"%";
		
		// 페이지번호 받아오기
		String pageNum = request.getParameter("pageNum");
		//페이지넘버가 있는 경우, 없는 경우가 있으니까
		//없을 때는 1로 설정해준다
		if(pageNum == null) {
			pageNum="1";
		}
		
		int pageSize = 10; //페이지 사이즈는 15로 설정
		int currentPage = Integer.parseInt(pageNum); //현재페이지
		
		// 페이지 관련 VO를 만들어서 한번에 담아서 가져가야한다
		// PageVO에 페이징 관련 정보를 담아서 감
		PageVO vo = new PageVO();
		vo.setPageNum(pageNum);
		vo.setPageSize(pageSize);
		vo.setCurrentPage(currentPage);
		//검색어 담아주기 - %있는 것 - 디비작업할 때
		vo.setSearch(search2);
		
		//게시판 글리스트를 가져온다
		List<BoardVO> boardList = boardService.getBoardListSearch(vo);
		
		//전체 글의 개수
		int cnt = boardService.getBoardCountSearch(vo);
		//페이지 처리
		int pageCount = cnt/pageSize + (cnt%pageSize == 0? 0:1);
		int pageBlock = 3;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage + pageBlock -1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		vo.setCnt(cnt);
		vo.setPageCount(pageCount);
		vo.setPageBlock(pageBlock);
		vo.setStartPage(startPage);
		vo.setEndPage(endPage);
		//검색어를 %없는 걸 다시 담아준다 - jsp로 넘어갈 때는
		vo.setSearch(search);
		
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("vo", vo);
		
		// /WEB-INF/views/center/noticeSearch.jsp
		// 가상주소 변경없이 jsp 이동 - 그냥 주소 적기
		return "center/noticeSearch";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
