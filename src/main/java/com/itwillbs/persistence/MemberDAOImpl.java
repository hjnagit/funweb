package com.itwillbs.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberVO;

//@Repository : 스프링(root-context.xml)에서 파일을 DAO 파일로 인식하도록 설정

@Repository
public class MemberDAOImpl implements MemberDAO{
	//DAO에 관련된 동작을 수행

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	//디비연결 정보 필요 => 의존관계
	//mybatis를 사용할 거니까 sqlSessionFactory 객체 필요함(주입)
	//이미 생성된 객체 [root-context.xml - sqlSessionFactory객체(빈)] 를 사용
//	@Inject
//	private SqlSessionFactory factory;
	
	
	//디비연결 + MyBatis설정(mapper) + 자원해제
	@Inject
	private SqlSession sqlSession; //DI
	
	//mapper의 주소(이름) - 상수화
	//이 주소는 바뀌지 않으니까 - 그리고 계속 사용하니까
	private static final String NAMESPACE = "com.itwillbs.mapper.MemberMapper";
	
	
	
	//alt shift s -> v 
	@Override
	public String getDbTime() {
		
		//1.2. 디비연결
		//3. sql 작성
		//4. sql 실행
		//5. 데이터 처리
		//SqlSession sqlSession = factory.openSession(); ->이거 필요없다
		//selectOne -> 실행해서 데이터 1개만 리턴해 와라 라는 뜻 -> T타입
		//namespace 복사해주기
		//xml까지 찾아가게 해주는 주소
		//id .getTime로 적어주기
		//자동으로 String으로 받아와진다
		String now = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		
		log.info("##### now : " + now);
		
		return now;
	}

	
	
	
	@Override
	public void insertMember(MemberVO vo) {
		log.info("##### 1.2. 디비 연결 - sqlSession(DI객체)");
		log.info("##### 3. sql 작성 - (memberMapper.xml)");
		log.info("##### 3. pstmt 객체 생성 - sqlSession(DI객체)");
		log.info("##### 4. sql 실행 - sqlSession(DI객체)");
		//sqlSession.insert(sql 구문, 전달할 객체);
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
		//com.itwillbs.mapper.MemberMapper.insertMember
		log.info("##### 전달하는 vo 객체는 mapper에서 자동으로 매핑 후 정보 전달");
		log.info("##### DAOImpl -> mapper 이동 -> mysql 이동");
		
		log.info("##### 자원해제 - sqlSession(DI객체)");
		
		
	}




	@Override
	public MemberVO loginMember(MemberVO vo) {
		log.info("loginMember(vo) 호출");
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember", vo);
		
		//mapper에서 쿼리 실행 - 결과 저장해서 리턴
		
		return resultVO;
	}




	@Override
	public MemberVO loginMember(String userid, String userpw) {
		log.info("loginMember(userid, userpw) 호출");
		
		//mapper에 정보를 1개만 전달 가능
		//MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember", userid, userpw);
		
		//전달된 정보를 하나의 도메인 객체에 저장 후 처리
		//MemberVO vo = new MemberVO();
		//vo.setUserid(userid);
		//vo.setUserpw(userpw);
		
		//MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember", vo);
		
		//회원정보 + 게시판 정보 join해서 가져온다고 하면 하나의 도메인에 저장할 수 없다
		//회원정보 + 게시판 정보 => 하나의 도메인(MemberVO) 저정 X
		
		//=> 컬렉션을 사용(연관없는 데이터를 한번에 저장)
		//=> Map
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("컬럼명", 데이터값);
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		
		
		//MemberVO resultVO = sqlSession.selectOne(NAMESPACE+".loginMember", paramMap);
		
		
		return sqlSession.selectOne(NAMESPACE+".loginMember", paramMap);
	}




	@Override
	public MemberVO getMember(String id) {
		log.info("getMember(userid) 호출");
		log.info("mapper-sql 구문 호출 동작");
		
		String userid = id;
		
		MemberVO resultVO = sqlSession.selectOne(NAMESPACE + ".getMember", userid);
		log.info(resultVO + "");
		log.info("테스트 파일로 이동");
		
		return resultVO;
	}




	@Override
	public Integer updateMember(MemberVO uvo) {
		log.info("테스트가 -> updateMember(MemberVO uvo) 호출");
		
		//update메서드가 업데이트한 row수만큼 리턴해준다
		//insert도 마찬가지로 영향을 받은 수만큼 리턴해준다
		int result = sqlSession.update(NAMESPACE+".updateMember", uvo);
		
		log.info(" 회원 정보 수정 완료");
		//result는 0또는 1
		//=> 업데이트 성공 - 1 / 실패 - 0
		log.info(" updateMember -> 테스트 호출 ");
		//log.info("mmmmmmmmmmmmmmmmmmmmmm" + result);
		
		return result;
	}




	@Override
	public Integer deleteMember(MemberVO dvo) {
		log.info("테스트가 -> deleteMember(MemberVO uvo) 호출");
		
		return sqlSession.delete(NAMESPACE+".deleteMember", dvo);
	}




	@Override
	public List<MemberVO> getMemberList() {
		
		//db에서 vo형태의 객체가 전달되면,
		//List형태로 저장
		List<MemberVO> memberList = sqlSession.selectList(NAMESPACE+".getMemberList");
		
		//return sqlSession.selectList(NAMESPACE+".getMemberList"); 이거 간단
		return memberList;
	}
	
	
	
	
	
	
	
}
