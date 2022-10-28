package com.itwillbs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.domain.MemberVO;
import com.itwillbs.domain.PageVO;
import com.itwillbs.service.BoardService;
import com.itwillbs.service.MemberService;

@RestController
public class AjaxController {
	
	//객체 생성
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	//http://localhost:8088/FunWeb/member/idcheck
	//http://localhost:8088/FunWeb/member/idcheck?id=admin
	//http://localhost:8088/FunWeb/member/idcheck?id=adminaa
	@RequestMapping(value = "/member/idcheck", method = RequestMethod.GET)
	public ResponseEntity<String> idcheck(HttpServletRequest request) {
		// request를 사용하려면 이렇게 쓴다 HttpServletRequest request
		// request로 id값 받아오기
		String id = request.getParameter("id");
		
		//메서드 호출해서 사용하기
		MemberVO memberVO = memberService.getMember(id);
		
		//중복여부 확인하기
		String result="";
		if(memberVO !=null){
			//아이디 있음, 아이디 중복
			result="iddup";
		}else{
			//아이디 없음, 아이디 사용가능
			result="idok";
		}
		
		
		// 주소를 리턴하지 않고 결과데이터를 리턴해서 데이터를 전달해준다
		ResponseEntity<String> entity = new ResponseEntity<String>(result, HttpStatus.OK);
		return entity;
	}
	
	
	
	
	@RequestMapping(value = "/member/listJson", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> list(HttpServletRequest request) {
		
		//메서드 호출해서 사용하기
		List<MemberVO> list = memberService.getMemberList();
		
		
		// 주소를 리턴하지 않고 결과데이터를 리턴해서 데이터를 전달해준다
		ResponseEntity<List<MemberVO>> entity = 
				new ResponseEntity<List<MemberVO>>(list, HttpStatus.OK);
		// List<MemberVO> => 자동으로 json형으로 변경해주는 프로그램
		// => jackson-databind ----다른 프로그램도 있지만 이거 설치한다
		// 이렇게 리턴을 하면 자동으로 json으로 바꿔주는 프로그램이 있다
		// 이거 설치해서 사용한다
		return entity;
	}
	
	
	@RequestMapping(value = "/board/listJson", method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> list2(HttpServletRequest request) {
		PageVO vo = new PageVO();
		vo.setPageNum("1");
		vo.setPageSize(5);//최근 글 5개
		vo.setCurrentPage(1); //이거만 들고가도? 페이지넘은 안들고가도 오케이?
		
		
		//메서드 호출해서 사용하기
		List<BoardVO> list = boardService.getBoardList(vo);
		
		
		// 주소를 리턴하지 않고 결과데이터를 리턴해서 데이터를 전달해준다
		ResponseEntity<List<BoardVO>> entity = 
				new ResponseEntity<List<BoardVO>>(list, HttpStatus.OK);
		// List<BoardVO> => 자동으로 json형으로 변경해주는 프로그램
		// => jackson-databind ----다른 프로그램도 있지만 이거 설치한다
		// 이렇게 리턴을 하면 자동으로 json으로 바꿔주는 프로그램이 있다
		// 이거 설치해서 사용한다
		return entity;
	}
	
}
