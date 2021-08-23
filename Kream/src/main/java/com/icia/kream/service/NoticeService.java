package com.icia.kream.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dao.NoticeDAO;
import com.icia.kream.dto.BidDTO;
import com.icia.kream.dto.NoticeDTO;
import com.icia.kream.dto.PageDTO;

@Service
public class NoticeService {

	@Autowired
	private NoticeDAO ndao;
	private ModelAndView mav;
	
	@Autowired
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;

	// 고객센터 페이지 이동 + 공지사항 목록 출력
	public ModelAndView noticePage(int page) {
		System.out.println("service + noticePage");
		mav = new ModelAndView();
		// 공지사항 글 갯수
		int listCount = ndao.ListCount();
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		// 공지사항 리스트
		List<NoticeDTO> noticeList = ndao.noticePage(paging);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println(noticeList);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("noticeList", noticeList);
		mav.setViewName("notice");
		return mav;
	}
	
	// 공지사항 글 작성
	public ModelAndView noticeWrite(NoticeDTO notice) {
		System.out.println("sevice + noticeWrite : " + notice);
		mav = new ModelAndView();
		ndao.noticeWrite(notice);
		mav.setViewName("redirect:/noticepage");
		return mav;
	}
	
	// 공지사항 글 조회
	public ModelAndView noticeView(int nnumber) {
		System.out.println("service + noticeVice : " + nnumber);
		mav = new ModelAndView();
		NoticeDTO notice = ndao.noticeView(nnumber);
		mav.addObject("notice", notice);
		mav.setViewName("noticeview");
		return mav;
	}

	// 공지사항 글 삭제 (관리자 전용)
	public ModelAndView noticeDelete(int nnumber) {
		System.out.println("service + noticeDelete : " + nnumber);
		mav = new ModelAndView();
		ndao.noticeDelete(nnumber);
		mav.setViewName("redirect:/noticepage");
		return mav;
	}


}
