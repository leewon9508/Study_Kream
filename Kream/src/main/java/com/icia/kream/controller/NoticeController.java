package com.icia.kream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.NoticeDTO;
import com.icia.kream.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService ns;
	private ModelAndView mav;

	// 고객센터 페이지 이동 + 공지사항 목록 출력
	@RequestMapping(value = "/noticepage")
	public ModelAndView noticePage(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		System.out.println("controller + noticePage");
		mav = ns.noticePage(page);
		return mav;
	}

	// 공지사항 글 작성 페이지 이동 (관리자 전용)
	@RequestMapping(value = "/noticewritepage")
	public String noticeWritePage() {
		System.out.println("controller + noticeWritePage");
		return "noticewrite";
	}

	// 공지사항 글 작성
	@RequestMapping(value = "/noticewrite")
	public ModelAndView noticeWrite(@ModelAttribute NoticeDTO notice) {
		System.out.println("controller + noticeWrite : " + notice);
		mav = ns.noticeWrite(notice);
		return mav;
	}

	// 공지사항 글 조회
	@RequestMapping(value = "/noticeview")
	public ModelAndView noticeView(@RequestParam("nnumber") int nnumber) {
		System.out.println("controller + noticeView : " + nnumber);
		mav = ns.noticeView(nnumber);
		return mav;
	}
	
	// 공지사항 글 삭제 (관리자 전용)
	@RequestMapping(value="/noticedelete")
	public ModelAndView noticeDelete(@RequestParam("nnumber") int nnumber){
		System.out.println("controller + noticeDelete  : " + nnumber);
		mav = ns.noticeDelete(nnumber);
		return mav;
	}

}
