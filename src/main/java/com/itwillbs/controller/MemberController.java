package com.itwillbs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.service.MemberService;

@Controller
public class MemberController {

	// 객체 생성
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert() {
		// /WEB-INF/views/insert.jsp

		return "insert";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/insert
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert2() {
		// /WEB-INF/views/member/join.jsp
		// 가상주소 변경없이 jsp 이동 - 그냥 주소 적기
		return "member/join";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/insertPro
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberVO memberVO) {
		System.out.println("MemberController insertPro()");
		System.out.println(memberVO.getUserid());
		System.out.println(memberVO.getUserpw());
		System.out.println(memberVO.getUsername());
		System.out.println(memberVO.getUseremail());
		// 회원가입
		memberService.insertMember(memberVO);
		// 로그인 페이지로 이동
		// 주소가 변경되면서 가상주소로 이동 - redirect:/ 적기
		// Response.sendRedirect()
		return "redirect:/member/login";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/login
	// /WEB-INF/views/member/login.jsp
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		// 가상주소 변경없이 jsp 이동
		// 로그인 페이지로 이동
		// /WEB-INF/views/member/login.jsp
		return "member/login";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/loginPro
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(HttpSession session, MemberVO vo) {
		System.out.println("MemberController loginPro()");
		// 로그인 처리
		MemberVO vo2 = memberService.loginMember(vo);
		if (vo2 != null) {
			// 아이디 비밀번호 일치
			// 세션값 생성
			session.setAttribute("loginID", vo.getUserid());
			// 메인 페이지로 이동 main/main.jsp
			// 주소가 변경되면서 가상주소로 이동 - redirect:/
			return "redirect:/main/main";
		} else {
			// 아이디 비밀번호 틀림
			// System.out.println("틀림");
			// member/msg.jsp
			return "member/msg";
		}
	}

	// 가상주소 -> http://localhost:8088/FunWeb/main/main
	// /WEB-INF/views/main/main.jsp
	@RequestMapping(value = "/main/main", method = RequestMethod.GET)
	public String main() {
		// 가상주소 변경없이 jsp 이동
		// 메인 페이지로 이동
		// /WEB-INF/views/main/main.jsp
		return "main/main";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/logout
	// 로그아웃 처리 메세지 System.out.println("MemberController logout()");
	// 가상주소 redirect:/main/main 이동
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("MemberController logout()");
		// 로그아웃 - 세션값 초기화 하기
		session.invalidate();
		// 메인 페이지로 이동 main/main.jsp
		// 주소가 변경되면서 가상주소로 이동 - redirect:/
		return "redirect:/main/main";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/update
	// => /WEB-INF/views/member/update.jsp
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		// 세션값 가져오기
		String id = (String) session.getAttribute("loginID");
		// 수정할 정보 가지고 감. - 디비작업
		MemberVO vo = memberService.getMember(id);

		// request.setAttribute("vo", vo);
		// 스프링에서 데이터를 담아서 가져갈 때 사용
		model.addAttribute("vo", vo);

		// 가상주소 변경없이 jsp 이동
		// /WEB-INF/views/member/update.jsp
		return "member/update";
	}

	// 가상주소 -> http://localhost:8088/FunWeb/member/updatePro
	// 수정 처리 메세지 System.out.println("MemberController updatePro()");
	// /WEB-INF/views/main/main.jsp
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(MemberVO vo) {
		System.out.println("MemberController updatePro()");
		// loginMember 비밀번호 일치 여부 확인
		MemberVO vo2 = memberService.loginMember(vo);
		// 아이디 비밀번호 일치 수정처리 -> /main/main 이동
		// 아이디 비밀번호 틀림 msg.jsp 뒤로이동

		if (vo2 != null) {
			// 아이디 비밀번호 일치
			// 수정처리
			int result = memberService.updateMember(vo);
			// 메인 페이지로 이동 main/main.jsp
			// 주소가 변경되면서 가상주소로 이동 - redirect:/
			return "redirect:/main/main";
		} else {
			// 아이디 비밀번호 틀림
			// System.out.println("틀림");
			// member/msg.jsp
			return "member/msg";
		}
		// 업데이트(수정) 처리

		// 메인 페이지로 이동 main/main.jsp
		// 주소가 변경되면서 가상주소로 이동 - redirect:/
	}

	
	
	// 리스트
	// 가상주소 -> http://localhost:8088/FunWeb/member/list
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {
		
		// /WEB-INF/views/member/join.jsp
		// 가상주소 변경없이 jsp 이동 - 그냥 주소 적기
		return "member/list";
	}

}
