package com.icia.kream.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.kream.dto.MemberDTO;
import com.icia.kream.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberApiController {
	
	@Autowired
	private MemberService ms;
	
	// 1. 데이터 베이스 커넥션 시간 너무 오래걸림
	// 2. Ajax 비동기 통신 - contentType = 'application/json'
		// 	- JSON 직렬화, 역직렬화
	// 3. 스프링(서블릿) 웹 MVC 라이프 사이클 
	@PostMapping("/member/login/kakao")
	public Object socialLogin(HttpSession session, @RequestBody String data) {
		ObjectMapper objectMapper = new ObjectMapper();
		log.debug("data: {}", data);
		Map<String, String> dataMap = new HashMap<> ();

		try {
			dataMap = objectMapper.readValue(data, Map.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		MemberDTO member = new MemberDTO();
//		member.setLoginAt(data.get("loginTime"));
//		member.setMaccount(data.get("email"));
//		member.setMname(data.get("nickname"));
		
		session.setAttribute("loginMember", dataMap.get("email"));
		session.setAttribute("loginMemberName", dataMap.get("nickname"));
		log.debug("로그인 시간  : {}", dataMap.get("loginTime"));
		
		return data;
	}
	
	// 회원 임시 비번 발송 처리
	@PostMapping(value="/member/sendtemppw")
	public String memberPwFindProcess(@RequestBody Map<String, Object> params) {
		String mid = String.valueOf(params.get("mid"));
		System.out.println("mid : " + mid);
		try {
			String tempFullPW = ms.createTempPW(mid);
			String dbSavedPW = tempFullPW.substring(0, 148);
			String sendPW = tempFullPW.substring(148);
			ms.updateTempPW(mid, dbSavedPW);
			ms.sendTempPW(mid, sendPW);
		} catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}	
	
}
