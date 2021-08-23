package com.icia.kream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.BidDTO;
import com.icia.kream.service.AskService;
import com.icia.kream.service.BidService;

@Controller
public class BidController {

	@Autowired
	private BidService bs;
	private ModelAndView mav;

	@Autowired
	private AskService as;

	// 구매 입찰 페이지 이동 + 구매 입찰 리스트 출력
	@RequestMapping(value = "/bidpage")
	public ModelAndView bidPage(@RequestParam("pnumber") int pnumber,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("bidpnumber") int bidpnumber) {
		System.out.println("controller + bid : " + pnumber + "+" + page + "+" + bidpnumber);
		mav = bs.bidPage(pnumber, page, bidpnumber);
		return mav;
	}

	// 구매 입찰 등록하기
	@RequestMapping(value = "/bidinput")
	public ModelAndView bidInPut(@ModelAttribute BidDTO bid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("pnumber") int pnumber, @RequestParam("bidpnumber") int bidpnumber) {
		System.out.println("controller + bidInPut : " + bid + "+" + bidpnumber);
		mav = bs.bidInPut(bid, page, pnumber, bidpnumber);
		return mav;
	}

	// 구매 입찰 아이디 중복 확인
	@RequestMapping(value = "/bididcheck")
	public @ResponseBody String bidIdCheck(@RequestParam("bidid") String bidid,
			@RequestParam("bidpnumber") int bidpnumber) {
		System.out.println("controller + bidIdCheck : " + bidid + "+" + bidpnumber);
		String result = bs.bidIdCheck(bidid, bidpnumber);
		return result;
	}

	// 마이페이지 + 구매 입찰 한 리스트 출력
	@RequestMapping(value = "/bidlistpage")
	public ModelAndView bidListPage(@RequestParam("bidid") String bidid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + bidListPage : " + bidid + "+" + page);
		mav = bs.bidListPage(bidid, page);
		return mav;
	}

	// 구매 입찰 내용 수정 요청
	@RequestMapping(value = "/bidupdate")
	public ModelAndView bidUpDate(@RequestParam("bidnumber") int bidnumber) {
		System.out.println("controller + bidUpDate : " + bidnumber);
		mav = bs.bidUpDate(bidnumber);
		return mav;
	}

	// 구매 입찰 내용 수정
	@RequestMapping(value = "/bidupdateprocess")
	public ModelAndView bidUpDateProcess(@ModelAttribute BidDTO bid) {
		System.out.println("controller + bidUpDateProcess : " + bid);
		mav = bs.bidUpDateProcess(bid);
		return mav;
	}

	// 구매 입찰 취소
	@RequestMapping(value = "/biddelete")
	public ModelAndView bidDelete(@RequestParam("bidnumber") int bidnumber, @RequestParam("bidid") String bidid) {
		System.out.println("controller + bidDelete : " + bidnumber + "+" + bidid);
		mav = bs.bidDelete(bidnumber, bidid);
		return mav;
	}

	// 즉시 구매 페이지 이동 + 판매 입찰 리스트 출력
	@RequestMapping(value = "/bidimmediatelypage")
	public ModelAndView bidImmediatelyPage(@RequestParam("pnumber") int pnumber,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("askpnumber") int askpnumber) {
		System.out.println("controller + bidImmediatelyPage : " + pnumber + "+" + page + "+" + askpnumber);
		mav = bs.bidImmediatelyPage(pnumber, page, askpnumber);
		return mav;
	}
}
