package com.icia.kream.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dao.KeepDAO;
import com.icia.kream.dto.KeepDTO;
import com.icia.kream.dto.PageDTO;
import com.icia.kream.dto.ProductDTO;

@Service
public class KeepService {

	@Autowired
	private KeepDAO kdao;

	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	// 찜하기
	public ModelAndView jjim(String kid, String kpnumber) {
		mav = new ModelAndView();
		
		Map<String, String> jjimMap = new HashMap<String, String>();
		
		jjimMap.put("kid", kid);
		jjimMap.put("kpnumber", kpnumber);
		
		String idDoubleCheck = kdao.id(jjimMap);
		System.out.println("idDoubleCheck"+idDoubleCheck);
		if(idDoubleCheck == null) {
			kdao.jjim(jjimMap);
			System.out.println("찜등록 성공 Service");
			mav.setViewName("redirect:/paging");
		}
		else {
			System.out.println("찜등록 실패 Service");
			mav.setViewName("redirect:/paging");
		}
		
		return mav;
	}
	
	//	찜 삭제
	public ModelAndView jjimX(int knumber) {
		mav = new ModelAndView();
		
	
		int deleteResult = kdao.jjimDelete(knumber);
		
		mav.addObject("keep",deleteResult);
		
		if(deleteResult > 0) {
			mav.setViewName("redirect:/paging");
		}
		return mav;
	}
	
	// 찜리스트
	public ModelAndView jjimList(String kid) {
		System.out.println("찜리스트 jjimService");
		mav = new ModelAndView();
		System.out.println(kid);

		
		List<KeepDTO> keep = kdao.keepList(kid);
		System.out.println("리스트값확인" + keep);
		mav.addObject("keepList", keep);
		if(kid == null) {
			mav.setViewName("home");
		}
		mav.setViewName("jjimlist");
		return mav;
	}

	

	

}
