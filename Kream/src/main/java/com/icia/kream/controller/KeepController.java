package com.icia.kream.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.KeepDTO;
import com.icia.kream.service.KeepService;

@Controller
public class KeepController {
	
	@Autowired
	private KeepService ks;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	// 찜하기
	@RequestMapping(value="/jjim")
	public ModelAndView jjim(@RequestParam("kid") String kid,
							@RequestParam("kpnumber") String kpnumber) throws Exception {
		System.out.println("찜하기 jjimController");
		mav=ks.jjim(kid,kpnumber);
		

		
		return mav;
	}
	
	//	찜삭제
	@RequestMapping(value="/jjimX")
	public ModelAndView jjimX(@RequestParam("knumber") int knumber) {
		System.out.println("찜삭제 jjimXController");
		mav=ks.jjimX(knumber);
		
		return mav;
	}
	
	// 찜리스트
	@RequestMapping(value="/jjimlist")
	public ModelAndView jjimList(@RequestParam("kid") String kid) {
		
		System.out.println("찜리스트 jjimController");
		mav = ks.jjimList(kid);
		return mav;
	}
}
