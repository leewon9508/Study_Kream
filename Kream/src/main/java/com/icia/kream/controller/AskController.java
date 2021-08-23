package com.icia.kream.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.AskDTO;
import com.icia.kream.dto.BidDTO;
import com.icia.kream.service.AskService;
import com.icia.kream.service.BidService;

@Controller
public class AskController {

	@Autowired
	private AskService as;
	private ModelAndView mav;

	@Autowired
	private BidService bs;

	// 판매 입찰 페이지 이동 + 판매 입찰 리스트 출력
	@RequestMapping(value = "/askpage")
	public ModelAndView askPage(@RequestParam("askpnumber") int askpnumber, @RequestParam("pnumber") int pnumber,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + ask : " + pnumber + "+" + askpnumber + "+" + page);
		mav = as.askPage(pnumber, askpnumber, page);
		return mav;
	}

	// 판매 입찰 등록하기
	@RequestMapping(value = "/askinput")
	public ModelAndView askInPut(@ModelAttribute AskDTO ask,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("pnumber") int pnumber, @RequestParam("askpnumber") int askpnumber) {
		System.out.println("controller + askInPut : " + ask);
		mav = as.askInPut(ask, page, pnumber, askpnumber);
		return mav;
	}

	// 판매 입찰 아이디 중복 확인
	@RequestMapping(value = "/askidcheck")
	public @ResponseBody String askIdCheck(@RequestParam("askid") String askid,
			@RequestParam("askpnumber") int askpnumber) {
		System.out.println("controller + askIdCheck : " + askid + "+" + askpnumber);
		String result = as.askIdCheck(askid, askpnumber);
		return result;
	}

	// 마이페이지 + 판매 입찰 한 리스트 출력
	@RequestMapping(value = "/asklistpage")
	public ModelAndView askListPage(@RequestParam("askid") String askid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + askListPage : " + askid + "+" + page);
		mav = as.askListPage(askid, page);
		return mav;
	}

	// 판매 입찰 내용 수정 요청
	@RequestMapping(value = "/askupdate")
	public ModelAndView askUpDate(@RequestParam("asknumber") int asknumber) {
		System.out.println("controller + askUpDate : " + asknumber);
		mav = as.askUpDate(asknumber);
		return mav;
	}

	// 판매 입찰 내용 수정
	@RequestMapping(value = "/askupdateprocess")
	public ModelAndView askUpDateProcess(@ModelAttribute AskDTO ask) {
		System.out.println("controller + askUpDateProcess : " + ask);
		mav = as.askUpDateProcess(ask);
		return mav;
	}

	// 판매 입찰 취소
	@RequestMapping(value = "/askdelete")
	public ModelAndView askDelete(@RequestParam("asknumber") int asknumber, @RequestParam("askid") String askid) {
		System.out.println("controller + askDelete : " + asknumber + "+" + askid);
		mav = as.askDelete(asknumber, askid);
		return mav;
	}

	// 즉시 판매 페이지 이동 + 구매 입찰 리스트 출력
	@RequestMapping(value = "/askimmediatelypage")
	public ModelAndView askImmediatelyPage(@RequestParam("pnumber") int pnumber,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@RequestParam("bidpnumber") int bidpnumber) {
		System.out.println("controller + askImmediatelyPage : " + pnumber + "+" + page + "+" + bidpnumber);
		mav = as.askImmediatelyPage(pnumber, page, bidpnumber);
		return mav;
	}
}
