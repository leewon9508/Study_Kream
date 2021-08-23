package com.icia.kream.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dao.CommentDAO;
import com.icia.kream.dao.QuestionDAO;
import com.icia.kream.dto.CommentDTO;
import com.icia.kream.dto.PageDTO;
import com.icia.kream.dto.QuestionDTO;

@Service
public class QuestionService {
	@Autowired
	private QuestionDAO qdao;
	private ModelAndView mav;
	
	@Autowired
	private CommentDAO cdao;
	
	@Autowired
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;

	// 1대1 문의 페이지 이동 + 1대1 문의 목록 출력
	public ModelAndView questionPage(int page) {
		System.out.println("service + questionPage");
		mav = new ModelAndView();
		// 1대1 문의 글 갯수
		int listCount = qdao.ListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		// 1대1 문의 리스트
		List<QuestionDTO> questionList = qdao.questionPage(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println(questionList);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("questionList", questionList);
		mav.setViewName("question");
		return mav;
	}

	// 1대1 문의 글 작성
	public ModelAndView questionWrite(QuestionDTO question) {
		System.out.println("service + questionWrite : " + question);
		mav = new ModelAndView();
		qdao.questionWrite(question);
		mav.setViewName("redirect:/questionpage");
		return mav;
	}

	// 1대1 문의 글 상세 조회
	public ModelAndView questionView(int qnumber) {
		System.out.println("service + questionWrite : " + qnumber);
		mav = new ModelAndView();
		QuestionDTO question = qdao.questionView(qnumber);
		// 댓글
		List<CommentDTO> commentList = cdao.CommentList(qnumber);
		System.out.println("댓글 : " + commentList);
		mav.addObject("commentList", commentList);
		mav.addObject("question", question);
		mav.setViewName("questionview");
		return mav;
	}

	// 1대1 문의 글 삭제
	public ModelAndView questionDelete(int qnumber) {
		System.out.println("service + questionDelete : " + qnumber);
		mav = new ModelAndView();
		qdao.questionDelete(qnumber);
		mav.setViewName("redirect:/questionpage");
		return mav;
	}

	// 1대1 문의 글 수정 요청
	public ModelAndView questionUpdate(int qnumber) {
		System.out.println("service + questionUpdate : " + qnumber);
		mav = new ModelAndView();
		QuestionDTO question = qdao.questionUpdate(qnumber);
		mav.addObject("question", question);
		mav.setViewName("questionupdate");
		return mav;
	}

	// 1대1 문의 글 수정 처리
	public ModelAndView questionUpdateProcess(QuestionDTO question) {
		System.out.println("service + questionUpdateProcess : " + question);
		mav = new ModelAndView();
		qdao.questionUpdateProcess(question);
		mav.addObject("question", question);
		mav.setViewName("questionview");
		return mav;
	}
	
}
