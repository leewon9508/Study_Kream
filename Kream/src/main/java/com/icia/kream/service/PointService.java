package com.icia.kream.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.icia.kream.dao.PointDAO;
import com.icia.kream.dto.AccountDTO;
import com.icia.kream.dto.MemberDTO;
import com.icia.kream.dto.PageDTO;
import com.icia.kream.dto.QuestionDTO;

@Service
public class PointService {

	@Autowired
	public PointDAO pdao;
	public ModelAndView mav;

	@Autowired
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;

	// 포인트 충전
	public ModelAndView pointAccount(MemberDTO member, String mid, AccountDTO account) {
		System.out.println("service + pointAccount : " + member + "+" + mid);
		mav = new ModelAndView();
		// MEMBER 테이블 포인트 추가
		int memberResult = pdao.pointAccount(member);
		// ACCOUNT 테이블 데이터 추가
		int accountResult = pdao.pointAccountPlus(account);
		// MEMBER 테이블 MID account 조회
		MemberDTO memberPoint = pdao.memberPoint(mid);
		mav.addObject("member", memberPoint);
		mav.setViewName("redirect:/pointpage?mid=" + mid);
		return mav;
	}

	// 포인트 충전 내역
	public ModelAndView pointAccountList(int page, String mid) {
		System.out.println("service + pointAccountList : " + "+" + page);
		mav = new ModelAndView();
		// 포인트 충전 내역 갯수 체크
		int listCount = pdao.ListCount(mid);
		System.out.println("listCount 값 : " + listCount);
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		// 포인트 충전 내역
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("mid", mid);
		String startRow2 = String.valueOf(startRow);
		String endRow2 = String.valueOf(endRow);
		searchMap.put("startRow", startRow2);
		searchMap.put("endRow", endRow2);
		List<AccountDTO> accountlist = pdao.pointAccountList(searchMap);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println(accountlist);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("accountList", accountlist);
		mav.setViewName("pointchargelist");
		return mav;
	}

	// 포인트 사용 내역 보기 (구매)
	public ModelAndView pointUsePage(int page, String dbidid) {
		System.out.println("service + pointUsePage : " + "+" + page);
		mav = new ModelAndView();
		// 포인트 사용 내역 보기 (구매) + 갯수
		int listCount = pdao.useListCount(dbidid);
		System.out.println("listCount 값 : " + listCount);
		int startRow = (page - 1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		System.out.println("paging값" + paging.toString());
		// 포인트 사용 내역 보기 (구매) + 리스트
		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("dbidid", dbidid);
		String startRow2 = String.valueOf(startRow);
		String endRow2 = String.valueOf(endRow);
		searchMap.put("startRow", startRow2);
		searchMap.put("endRow", endRow2);
		List<AccountDTO> pointaccountuselist = pdao.pointAccountUseList(searchMap);
		int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
		int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println(pointaccountuselist);
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("pointaccountuselist", pointaccountuselist);
		mav.setViewName("pointuselist");
		return mav;
	}

	// 즉시 구매 or 판매 + Member 잔액 확인
	public String pointCheck(String mid, int maccount) {
		System.out.println("service + pointCheck : " + mid);
		Map<String, String> chekMap = new HashMap<String, String>();
		chekMap.put("mid", mid);
		String maccount2 = String.valueOf(maccount);
		chekMap.put("maccount", maccount2);
		String checkResult = pdao.pointCheck(chekMap);
		System.out.println("checkResult : " + checkResult);
		String result = "";
		if (checkResult != null) {
			result = "ok";
			System.out.println("result : " + result);
		} else {
			result = "no";
			System.out.println("result : " + result);
		}
		return result;
	}

	// 즉시 구매 + 구매자 포인트 감소
	public String pointMinus(MemberDTO member, String mid) {
		System.out.println("service + pointMinus : " + member + "+" + mid);
		// 즉시 구매 + 구매자 포인트 감소
		int memberPoint = pdao.pointMinus(member);
		// 즉시 구매 + 구매자 포인트 + 결과 확인
		MemberDTO memberPointResult = pdao.memberPointResult(mid);
		System.out.println("memberPointResult 잔액 : " + memberPointResult);
		String result = "";
		if (memberPoint != 0) {
			result = "ok";
			System.out.println("result : " + result);
		} else {
			result = "no";
			System.out.println("result : " + result);
		}
		return result;
	}

	// 즉시 구매 + 판매자 포인트 증가
	public String pointPlus(MemberDTO member, String mid) {
		System.out.println("service + pointPlus : " + member + "+" + mid);
		// 즉시 구매 + 판매자 포인트 증가
		int memberPoint = pdao.pointPlus(member);
		// 즉시 구매 + member 포인트 + 결과 확인
		MemberDTO memberPointResult = pdao.memberPointResult(mid);
		System.out.println("memberPointResult 잔액 : " + memberPointResult);
		String result = "";
		if (memberPoint != 0) {
			result = "ok";
			System.out.println("result : " + result);
		} else {
			result = "no";
			System.out.println("result : " + result);
		}
		return result;
	}

	public ModelAndView pointPage(String mid) {
		System.out.println("service + pointPage : " + mid);
		mav = new ModelAndView();
		MemberDTO memberPoint = pdao.pointPage(mid);
		mav.addObject("member", memberPoint);
		mav.setViewName("point");
		return mav;
	}

}
