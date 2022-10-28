package com.itwillbs.domain;

import java.sql.Date;

public class MemberVO {
	//VO(Value Object) - 값을 저장하는 객체
	//=> JavaBean, DTO 개념 동일
	
	//도메인(domain) : 개발(프로젝트) 진행 시 가장 중요한 기능(역할) 단어(명사)
	//=> 영화프로젝트의 도메인은 -> 영화, 회원, 영화관, 예약, 결제...이런 것들
	//=> 물리적으로 분리가 가능한 단위의 개념 - 도메인이라고 한다
	//=> 디비를 분리해야하는 것
	
	//tbl_member 테이블의 정보를 저장, 전달
	
	private String userid;
	private String userpw;
	private String username;
	private String useremail;
	private Date regdate;
	private Date updatedate;
	
	//getter setter
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", useremail="
				+ useremail + ", regdate=" + regdate + ", updatedate=" + updatedate + "]";
	}
	
}
