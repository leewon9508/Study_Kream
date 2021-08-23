package com.icia.kream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.AccountDTO;
import com.icia.kream.dto.MemberDTO;
import com.icia.kream.service.PointService;

@Controller
public class PointController {

	@Autowired
	public PointService ps;
	public ModelAndView mav;

	// 포인트 화면 이동 + 상세 조회
	@RequestMapping(value = "/pointpage")
	public ModelAndView pointPage(@RequestParam("mid") String mid) {
		System.out.println("controller + pointpage : " + mid);
		mav = ps.pointPage(mid);
		return mav;
	}

	// 회원 포인트 조회
	@RequestMapping(value = "/pointclient")
	public String pointClinent() {
		System.out.println("controller + pointclient");
		return "pointclient";
	}

	// 포인트 충전 화면 이동
	@RequestMapping(value = "/pointcharge")
	public String pointAccountPage() {
		System.out.println("controller + pointaccountpage");
		return "pointcharge";
	}

	// 포인트 충전
	@RequestMapping(value = "/pointpluspage")
	public ModelAndView pointPlus(@RequestParam("mid") String mid, @RequestParam("maccount") String maccount) {
		System.out.println("controller + pointPlus : " + mid + "+" + maccount);
		String mid_p = mid;
		String maccount_p = maccount;
		mav = new ModelAndView();
		System.out.println("controller + mid_p : " + mid_p);
		System.out.println("controller + maccount_p : " + maccount_p);
		mav.addObject("mid", mid_p);
		mav.addObject("maccount", maccount_p);
		mav.setViewName("pointplus");
		return mav;
	}

	// 포인트 충전 카카오
	@RequestMapping(value = "/pointaccount")
	public ModelAndView pointAccount(@ModelAttribute MemberDTO member, @RequestParam("mid") String mid,
			@ModelAttribute AccountDTO account) {
		System.out.println("controller + pointAccount : " + member + "+" + mid + "+" + account);
		mav = ps.pointAccount(member, mid, account);
		return mav;
	}

	// 포인트 충전 내역 보기
	@RequestMapping(value = "/pointchargelist")
	public ModelAndView pointAccountList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("mid") String mid) {
		System.out.println("controller + pointAccountList : " + "+" + mid);
		mav = ps.pointAccountList(page, mid);
		return mav;
	}

	// 포인트 사용 내역 보기 (구매)
	@RequestMapping(value = "/pointusepage")
	public ModelAndView pointUsePage(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("dbidid") String dbidid) {
		System.out.println("controller + pointUsePage : " + "+" + dbidid);
		mav = ps.pointUsePage(page, dbidid);
		return mav;
	}

	// 즉시 구매 or 판매 + Member 잔액 확인
	@RequestMapping(value = "/pointcheck")
	public @ResponseBody String pointCheck(@RequestParam("mid") String mid, @RequestParam("maccount") int maccount) {
		System.out.println("controller + pointCheck : " + mid + "+" + maccount);
		String result = ps.pointCheck(mid, maccount);
		return result;
	}

	// 즉시 구매 + 구매자 포인트 감소
	@RequestMapping(value = "/pointminus")
	public @ResponseBody String pointMinus(@ModelAttribute MemberDTO member, @RequestParam("mid") String mid) {
		System.out.println("controller + pointMinus : " + member + "+" + mid);
		String result = ps.pointMinus(member, mid);
		return result;
	}

	// 즉시 구매 + 판매자 포인트 증가
	@RequestMapping(value = "/pointplus")
	public @ResponseBody String bidPointPlus(@ModelAttribute MemberDTO member, @RequestParam("mid") String mid) {
		System.out.println("controller + pointPlus : " + member + "+" + mid);
		String result = ps.pointPlus(member, mid);
		return result;
	}

}
