package com.itwillbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.MemberVO;
import com.itwillbs.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService{
	
	//객체 생성
	@Autowired
	private MemberDAO memberDAO;
	
	
	@Override
	public void insertMember(MemberVO vo) {
		System.out.println("MemberServiceImpl insertMember(MemberVO vo)");
		memberDAO.insertMember(vo);
	}

	@Override
	public MemberVO loginMember(MemberVO vo) {
		System.out.println("MemberServiceImpl loginMember(MemberVO vo)");
		return memberDAO.loginMember(vo);
	}

	@Override
	public MemberVO loginMember(String userid, String userpw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO getMember(String id) {
		//메서드 호출
		return memberDAO.getMember(id);
	}

	@Override
	public Integer updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return memberDAO.updateMember(vo);
	}

	@Override
	public Integer deleteMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getMemberList() {
		// TODO Auto-generated method stub
		return memberDAO.getMemberList();
	}
	
}
