package com.icia.kream.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dao.AddressDAO;
import com.icia.kream.dto.AddressDTO;

@Service
public class AddressService {

	@Autowired
	public AddressDAO adao;
	public ModelAndView mav;

	// 주소 등록
	public ModelAndView addressWrite(AddressDTO address, String id) {
		System.out.println("service + addressWrite : " + address + "+" + id);
		mav = new ModelAndView();
		adao.addressWrite(address);
		// mav.addObject("product", product);
		mav.setViewName("redirect:/addressview?id=" + id);
		return mav;
	}

	// 주소 상세 조회
	public ModelAndView addressView(String id) {
		System.out.println("service + addressView : " + id);
		mav = new ModelAndView();
		AddressDTO address = adao.addressView(id); 
		System.out.println(address);
		mav.addObject("address", address);
		mav.setViewName("addressview");
		return mav;
	}

	// 주소 삭제
	public ModelAndView addressDelete(String id) {
		System.out.println("service + addressDelete : " + id);
		mav = new ModelAndView();
		adao.addressDelete(id); 
		mav.setViewName("mypage");
		return mav;
	}
}
