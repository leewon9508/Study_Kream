package com.icia.kream.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dao.AddressDAO;
import com.icia.kream.dao.AskDAO;
import com.icia.kream.dao.BidDAO;
import com.icia.kream.dao.DealDAO;
import com.icia.kream.dao.ProductDAO;
import com.icia.kream.dto.AddressDTO;
import com.icia.kream.dto.DealDTO;
import com.icia.kream.dto.PageDTO;
import com.icia.kream.dto.ProductDTO;

@Service
public class DealService {

	@Autowired
	public DealDAO ddao;
	public ModelAndView mav;

	@Autowired
	public AddressDAO adao;

	@Autowired
	private ProductDAO pdao;

	@Autowired
	private AskDAO askdao;

	@Autowired
	private BidDAO biddao;

	@Autowired
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;
	
	// 거래 상세 조회
	public ModelAndView dealView(int dnumber, int pnumber, String id) {
		System.out.println("service + dealView : " + dnumber + "+" + pnumber + "+" + id);
		mav = new ModelAndView();
		// 거래 상세 조회
		DealDTO deal = ddao.dealView(dnumber);
		// 구매자 주소 가져오기
		AddressDTO address = adao.addressView(id);
		// 상품 상세 조회
		ProductDTO product = pdao.productView(pnumber);

		mav.addObject("address", address);
		mav.addObject("product", product);
		mav.addObject("deal", deal);
		mav.setViewName("dealview");
		return mav;
	}

	// 구매 내역
	public ModelAndView bidHistory(String dbidid, int page) {
		System.out.println("service + bidHistory : " + dbidid);
		mav = new ModelAndView();
		// 구매 내역 갯수
		int bidListCount = ddao.bidListCount(dbidid);
		System.out.println("bidListCount : " + bidListCount);
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		// 구매 내역 리스트
		Map<String, String> bidMap = new HashMap<String, String>();
		bidMap.put("dbidid", dbidid);
		String startRow2 = String.valueOf(startRow);
		String endRow2 = String.valueOf(endRow);
		bidMap.put("startRow", startRow2);
		bidMap.put("endRow", endRow2);
		List<DealDTO> bidHistory = ddao.bidHistory(bidMap);
		int maxPage = (int) (Math.ceil((double) bidListCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println("bidHistory : " + bidHistory);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("bidHistory", bidHistory);
		mav.setViewName("bidhistory");
		return mav;
	}
	
	// 판매 내역
	public ModelAndView askHistory(String daskid, int page) {
		System.out.println("service + askHistory : " + daskid);
		mav = new ModelAndView();
		// 구매 내역 갯수
		int askListCount = ddao.askListCount(daskid);
		System.out.println("askListCount : " + askListCount);
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		// 구매 내역 리스트
		Map<String, String> askMap = new HashMap<String, String>();
		askMap.put("daskid", daskid);
		String startRow2 = String.valueOf(startRow);
		String endRow2 = String.valueOf(endRow);
		askMap.put("startRow", startRow2);
		askMap.put("endRow", endRow2);
		List<DealDTO> askHistory = ddao.askHistory(askMap);
		int maxPage = (int) (Math.ceil((double) askListCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println("askHistory : " + askHistory);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("askHistory", askHistory);
		mav.setViewName("askhistory");
		return mav;
	}


	// 즉시 구매 처리
	public ModelAndView bidDirect(DealDTO deal, String id, int pnumber, int asknumber) {
		System.out.println("service + bidDirect : " + deal);
		System.out.println("service + bidDirect : " + id + "+" + pnumber + "+" + asknumber);
		mav = new ModelAndView();

		// 거래 관리 데이터 추가 하기
		DealDTO dealResult = ddao.dealWrite(deal);
		System.out.println("dealResult : " + dealResult);
		// 구매자 주소 가져오기
		AddressDTO address = adao.addressView(id);
		// 상품 상세 조회
		ProductDTO product = pdao.productView(pnumber);
		// 구매 한 판매 입찰 내용 삭제
		askdao.askDelete(asknumber);
		
		mav.addObject("deal", dealResult);
		mav.addObject("address", address);
		mav.addObject("product", product);
		mav.setViewName("dealview");
		return mav;
	}

	// 즉시 판매 처리
	public ModelAndView askDirect(DealDTO deal, String id, int pnumber, int bidnumber) {
		System.out.println("controller + askDirect : " + deal);
		System.out.println("controller + askDirect : " + id + "+" + pnumber + "+" + bidnumber);
		mav = new ModelAndView();
		
		// 거래 관리 데이터 추가 하기
		DealDTO dealResult = ddao.dealWrite(deal);
		System.out.println("dealResult : " + dealResult);
		// 구매자 주소 가져오기
		AddressDTO address = adao.addressView(id);
		// 상품 상세 조회
		ProductDTO product = pdao.productView(pnumber);
		// 구매 한 판매 입찰 내용 삭제
		biddao.bidDelete(bidnumber);
		
		mav.addObject("deal", dealResult);
		mav.addObject("address", address);
		mav.addObject("product", product);
		mav.setViewName("dealview");
		return mav;
	}

	
}
