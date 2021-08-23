package com.icia.kream.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.MemberDTO;
import com.icia.kream.service.MemberService;
import com.icia.kream.service.ProductService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService ms;
	
	private ModelAndView mav;
	
	@Autowired
	private ProductService ps;
	
	@Autowired
	private HttpSession session;
	
	// home.jsp에서 회원가입 페이지로 단순 이동
	@RequestMapping(value="/memberjoinpage")
	public String joinPage() {
		return "memberjoin";
	}
	
	// dto단위로 묶어서 service로 전달
	@RequestMapping(value="/memberjoin")
	public ModelAndView memberJoin(@ModelAttribute MemberDTO member) {
		mav = ms.memberJoin(member);
		// 회원가입 완료 후 로그인페이지 출력
		return mav;
	}
	
	// ID 중복확인 메소드
	@RequestMapping(value="/idcheck")
	public @ResponseBody String idCheck(@RequestParam("mid") String mid) {
		String result = ms.idCheck(mid);
		return result;
	}
	
	// 로그인 화면 출력
	@RequestMapping(value="/memberloginpage")
	public String loginPage() {
		return "memberlogin";
	}
	
	// 로그인 처리 메소드 
	@RequestMapping(value="/memberlogin")
	public ModelAndView memberLogin(@ModelAttribute MemberDTO member) { 
		System.out.println("로그인");
		mav = ms.memberLogin(member);	
		return mav;
	}
	
	// 마이페이지
	@RequestMapping(value="/mypage")
	public ModelAndView myPage(@RequestParam("id") String id) {
		System.out.println("controller + mypage : " + id);
		mav = ms.myPage(id);
		return mav;
		}
	
	// 수정화면
	@RequestMapping(value="/memberupdate")
	public ModelAndView memberUpdate() {
		mav = ms.memberUpdate();
		return mav;
	}
	
	// 수정처리
	@RequestMapping(value="/memberupdateprocess")
	public ModelAndView memberUpdateProcess(@ModelAttribute MemberDTO member) {
		mav = ms.memberUpdateProcess(member);
		return mav;
	}
	
	// 로그아웃처리 
	@RequestMapping(value="/logout")
	public ModelAndView logout(@RequestParam(value="page", required=false, defaultValue="1") int page) {
		session.invalidate();
		mav = ps.productPaging(page);
		return mav;
		}
	
	// 회원목록 출력
	@RequestMapping(value="/memberlist")
	public ModelAndView memberList() {
		mav = ms.memberList();
		return mav;
	}
	
	// 회원 상세조회
	@RequestMapping(value="/memberview")
	public ModelAndView memberView(@RequestParam("mid") String mid) {
		mav = ms.memberView(mid);
		return mav;
	}
	
	// (ajax)회원 상세조회 
	@RequestMapping(value="/memberviewajax")
	public @ResponseBody MemberDTO memberViewAjax(@RequestParam("mid") String mid) {
		MemberDTO member = ms.memberViewAjax(mid);
		return member;
	}
	
	// 회원삭제처리
	@RequestMapping(value="/memberdelete")
	public ModelAndView memberDelete(@RequestParam("mid") String mid) {
		mav = ms.memberDelete(mid);
		return mav;
	}
	
	// 회원 이메일 ID 찾기 페이지 이동
	@RequestMapping(value="/memberidfind")
	public String memberIdFind() {
		return "memberidfind";
	}
	
	// 회원 이메일 ID 찾기 처리
	@RequestMapping(value="/idfindprocess")
	public ModelAndView idFindProcess(@RequestParam("mphone") String mphone) {
		System.out.println("controller + idfindprocess : " + mphone);
		mav = ms.idFindProcess(mphone);
		return mav;
	}
	
	// 회원 비밀번호 찾기 페이지
	@RequestMapping(value="/memberpwfind")
	public String memberPwFind() {
	return "memberpwfind";
	}
	
}
