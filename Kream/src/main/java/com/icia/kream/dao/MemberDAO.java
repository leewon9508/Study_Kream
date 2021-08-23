package com.icia.kream.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.MemberDTO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	// 회원가입
	public int memberJoin(MemberDTO member) {
		// mm = mapper의 namespace
		return sql.insert("mm.memberjoin", member);
	}
	
	// ID 중복확인
	public String idCheck(String mid) {
		return sql.selectOne("mm.idcheck", mid);
	}
	
	// 로그인
	public String memberLogin(MemberDTO member) {
		System.out.println("로그인 성공 : " + member);
		return sql.selectOne("mm.memberlogin", member);
	}
	// 로그인 이름
	public String memberLoginName(MemberDTO member) {
		return sql.selectOne("mm.memberloginname", member);
	}

	// 회원정보 수정 화면
	public MemberDTO memberUpdate(String loginId) {
		return sql.selectOne("mm.memberupdate", loginId);
	}

	// 회원정보 수정 처리
	public int memberUpdateProcess(MemberDTO member) {
		return sql.update("mm.updateprocess", member);
	}

	// 회원목록
	public List<MemberDTO> memberList() {
		return sql.selectList("mm.memberlist");
	}
	
	// 회원 상세조회
	public MemberDTO memberView(String mid) {
		return sql.selectOne("mm.memberview", mid);
	}
	
	// 회원정보 삭제 처리
	public void memberDelete(String mid) {
		sql.delete("mm.memberdelete", mid);	
	}
	
	// 회원 이메일 ID 찾기
	public MemberDTO idFindProcess(String mphone) {
		System.out.println("mdao + idfindprocess : " + mphone);
		return sql.selectOne("mm.idfindprocess", mphone);
	}

	// 회원 임시 비번 토큰 발송을 위한 id찾기
	public MemberDTO findByMid(String mid) {
		System.out.println("mid + findByMid : " + mid);
		return sql.selectOne("mm.findByMid", mid);
	}
	
}