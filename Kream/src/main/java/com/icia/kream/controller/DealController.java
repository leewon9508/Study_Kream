package com.icia.kream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.DealDTO;
import com.icia.kream.service.DealService;

@Controller
public class DealController {

	@Autowired
	private DealService ds;
	private ModelAndView mav;

	// 거래 상세 조회
	@RequestMapping(value = "/dealview")
	private ModelAndView dealView(@RequestParam("dnumber") int dnumber, @RequestParam("pnumber") int pnumber,
			@RequestParam("id") String id) {
		System.out.println("controller + dealView : " + dnumber + "+" + pnumber + "+" + id);
		mav = ds.dealView(dnumber, pnumber, id);
		return mav;
	}

	// 구매 내역
	@RequestMapping(value = "/bidhistory")
	private ModelAndView bidHistory(@RequestParam("dbidid") String dbidid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + bidHistory : " + dbidid);
		mav = ds.bidHistory(dbidid, page);
		return mav;
	}

	// 판매 내역
	@RequestMapping(value = "/askhistory")
	private ModelAndView askHistory(@RequestParam("daskid") String daskid,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + askHistory : " + daskid);
		mav = ds.askHistory(daskid, page);
		return mav;
	}

	// 즉시 구매 처리
	@RequestMapping(value = "/biddirect")
	private ModelAndView bidDirect(@ModelAttribute DealDTO deal, @RequestParam("id") String id,
			@RequestParam("pnumber") int pnumber, @RequestParam("asknumber") int asknumber) {
		System.out.println("controller + bidDirect : " + deal);
		System.out.println("controller + bidDirect : " + id + "+" + pnumber + "+" + asknumber);
		mav = ds.bidDirect(deal, id, pnumber, asknumber);
		return mav;
	}

	// 즉시 판매 처리
	@RequestMapping(value = "/askdirect")
	private ModelAndView askDirect(@ModelAttribute DealDTO deal, @RequestParam("id") String id,
			@RequestParam("pnumber") int pnumber, @RequestParam("bidnumber") int bidnumber) {
		System.out.println("controller + askDirect : " + deal);
		System.out.println("controller + askDirect : " + id + "+" + pnumber + "+" + bidnumber);
		mav = ds.askDirect(deal, id, pnumber, bidnumber);
		return mav;
	}

}
