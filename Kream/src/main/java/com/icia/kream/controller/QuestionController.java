package com.icia.kream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.QuestionDTO;
import com.icia.kream.service.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService qs;
	private ModelAndView mav;

	// 1대1 문의 페이지 이동 + 1대1 문의 목록 출력
	@RequestMapping(value = "/questionpage")
	public ModelAndView questionPage(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + questionPage");
		mav = qs.questionPage(page);
		return mav;
	}

	// 1대1 문의 글 작성페이지 이동
	@RequestMapping(value = "/questionwritepage")
	public String questionWritePage() {
		System.out.println("controller + questionWritePage");
		return "questionwrite";
	}

	// 1대1 문의 글 작성
	@RequestMapping(value = "/questionwrite")
	public ModelAndView questionWrite(@ModelAttribute QuestionDTO question) {
		System.out.println("controller + questionWrite");
		mav = qs.questionWrite(question);
		return mav;
	}

	// 1대1 문의 글 상세 조회
	@RequestMapping(value = "/questionview")
	public ModelAndView questionView(@RequestParam("qnumber") int qnumber) {
		System.out.println("controller + questionView : " + qnumber);
		mav = qs.questionView(qnumber);
		return mav;
	}
	
	// 1대1 문의 글 삭제
	@RequestMapping(value = "/questiondelete")
	public ModelAndView questionDelete(@RequestParam("qnumber") int qnumber) {
		System.out.println("controller + questionDelete : " + qnumber);
		mav = qs.questionDelete(qnumber);
		return mav;
	}
	
	// 1대1 문의 글 수정 요청
	@RequestMapping(value = "/questionupdate")
	public ModelAndView questionUpdate(@RequestParam("qnumber") int qnumber) {
		System.out.println("controller + questionupdate : " + qnumber);
		mav = qs.questionUpdate(qnumber);
		return mav;
	}
	
	// 1대1 문의 글 수정 처리
	@RequestMapping(value = "/questionupdateprocess")
	public ModelAndView questionUpdateProcess(@ModelAttribute QuestionDTO question) {
		System.out.println("controller + questionUpdateProcess : " + question);
		mav = qs.questionUpdateProcess(question);
		return mav;
	}
}
