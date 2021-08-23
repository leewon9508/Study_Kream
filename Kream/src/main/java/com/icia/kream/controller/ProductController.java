 package com.icia.kream.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dto.ProductDTO;
import com.icia.kream.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService ps;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	
	//제품등록화면 요청
	@RequestMapping(value="/productregistrationpage")
	public String productRegistrationPage() {
		System.out.println("제품등록화면 요청 Controller");
		return "productregistration";
	}
	
	//관리자 제품 등록 처리
	@RequestMapping(value="/productregistration")
	public ModelAndView registrationProcess(@ModelAttribute ProductDTO product)throws IllegalStateException, IOException {
		System.out.println("관리자 제품 등록 처리 Controller");
		mav=ps.registrationProcess(product);
		return mav;
	}
	
	//상품 등록 시 상품 상세 조회 화면 이동
	@RequestMapping(value="/productview")
	public ModelAndView productView(@RequestParam("pnumber") int pnumber,
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		System.out.println("상품 등록 시 상품 상세 조회 화면 이동 Controller");
		mav=ps.productView(pnumber,page);
		return mav;
	}
	
	//상품목록화면출력(페이징)
	@RequestMapping(value="/productlistpaging")
	public ModelAndView productListPaging(@RequestParam(value="page", required=false, defaultValue="1") int page) {
		System.out.println("상품목록화면출력(페이징) Controller");
		mav = ps.productListPaging(page);
		return mav;
	}
	
	//홈화면에서상품목록화면페이징처리
	@RequestMapping(value="/paging")
	public ModelAndView productPaging(@RequestParam(value="page", required=false, defaultValue="1") int page) {
		System.out.println("홈화면에서상품목록화면페이징처리 Controller");
		mav = ps.productPaging(page);
		return mav;
	}
	
	//제품수정화면요청
	@RequestMapping(value="/productupdate")
	public ModelAndView productUpdate(@RequestParam("pnumber") int pnumber) {
		System.out.println("제품수정화면요청 Controller");
		mav=ps.productUpdate(pnumber);
		return mav;
	}
	
	//제품수정처리
	@RequestMapping(value="/productupdateprocess")
	public ModelAndView productUpdateProcess(@ModelAttribute ProductDTO product) {
		System.out.println("제품수정처리 Controller");
		mav=ps.productUpdateProcess(product);
		return mav;
	}
	
	//제품삭제
	@RequestMapping(value="/productdelete")
	public ModelAndView productDelete(@RequestParam("pnumber") int pnumber) {
		System.out.println("제품삭제 Controller");
		mav=ps.productDelete(pnumber);
		return mav;
	}
	
	//제품검색					(페이지넘어갈때적용되야함)
	@RequestMapping(value="/search")
	public ModelAndView productSearch(@RequestParam("searchtype") String searchType, 
									@RequestParam("keyword") String keyword,
									@RequestParam(value="page", required=false, defaultValue="1") int page) {
		System.out.println("제품검색 Controller");
		mav = ps.productSearch(searchType, keyword, page);
		return mav;
	}
	
}
