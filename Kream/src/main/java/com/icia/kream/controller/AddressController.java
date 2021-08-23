package com.icia.kream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.AddressDTO;
import com.icia.kream.service.AddressService;

@Controller
public class AddressController {

	@Autowired
	public AddressService as;
	public ModelAndView mav;
	
	// 주소 등록 페이지 이동
	@RequestMapping (value = "/addresswritepage")
	public String addressWritePage() {
		System.out.println("controller + addressWritePage");
		return "addresswrite";
	}
	
	// 주소 등록
	@RequestMapping(value = "/addresswrite")
	public ModelAndView addressWrite(@ModelAttribute AddressDTO address,@RequestParam("id") String id) {
		System.out.println("controller + addressWrite : " + address + "+" + id);
		mav = as.addressWrite(address,id);
		return mav;
	}
	
	// 주소 상세 확인
	@RequestMapping(value = "/addressview")
	public ModelAndView addressView(@RequestParam("id") String id) {
		System.out.println("controller + asddressView : " + id);
		mav = as.addressView(id);
		return mav;
	}
	
	// 주소 삭제
	@RequestMapping(value = "/addressdelete")
	public ModelAndView addressDelete(@RequestParam("id") String id) {
		System.out.println("controller + addressDelete : " + id);
		mav = as.addressDelete(id);
		return mav;
	}
}
