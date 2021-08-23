package com.icia.kream.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.kream.dao.ProductDAO;
import com.icia.kream.dto.ProductDTO;
import com.icia.kream.dto.PageDTO;

@Service
public class ProductService {
	@Autowired
	private ProductDAO pdao;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	// 제품등록처리
	public ModelAndView registrationProcess(ProductDTO product)throws IllegalStateException, IOException {
		System.out.println("제품등록처리 Service");
		mav = new ModelAndView();
		
		MultipartFile pfile = product.getPfile();
		String pfilename = pfile.getOriginalFilename();
		pfilename = System.currentTimeMillis() + "-" + pfilename;
		String savePath = "C:\\Users\\sixty\\OneDrive\\바탕 화면\\Kream_0812\\src\\main\\webapp\\resources\\ProductImage\\"+pfilename;
		if(!pfile.isEmpty()) {
			pfile.transferTo(new File(savePath));
		}
		
		product.setPfilename(pfilename);
		pdao.registrationProcess(product);
		
		mav.setViewName("redirect:/paging");
		
		return mav;
	}
	
	//상품목록화면출력(페이징)
		public ModelAndView productListPaging(int page) {
			System.out.println("상품목록화면출력(페이징) Service");
			mav = new ModelAndView();
			int listCount = pdao.listCount();
			int startRow = (page-1) * PAGE_LIMIT + 1;
			int endRow = page * PAGE_LIMIT;
			PageDTO paging = new PageDTO();
			paging.setStartRow(startRow);
			paging.setEndRow(endRow);
			List<ProductDTO> productList = pdao.productPaging(paging);
			int maxPage = (int)(Math.ceil((double)listCount / PAGE_LIMIT));
			int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
			int endPage = startPage + BLOCK_LIMIT - 1;
			
			if(endPage > maxPage)
				endPage = maxPage;
			
			paging.setPage(page);
			paging.setStartPage(startPage);
			paging.setEndPage(endPage);
			paging.setMaxPage(maxPage);
			
			mav.addObject("paging", paging);
			mav.addObject("productList", productList);
			mav.setViewName("productlistpaging");
			return mav;
		}
	
	//홈화면에서상품목록화면페이징처리
	private static final int PAGE_LIMIT = 5;
	private static final int BLOCK_LIMIT = 5;
	
	public ModelAndView productPaging(int page) {
		System.out.println("홈화면에서상품목록화면페이징처리 Service");
		mav = new ModelAndView();
		int listCount = pdao.listCount();
		int startRow = (page-1) * PAGE_LIMIT + 1;
		int endRow = page * PAGE_LIMIT;
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<ProductDTO> productList = pdao.productPaging(paging);
		int maxPage = (int)(Math.ceil((double)listCount / PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
		int endPage = startPage + BLOCK_LIMIT - 1;
		
		if(endPage > maxPage)
			endPage = maxPage;
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("productList", productList);
		mav.setViewName("home");
		
		return mav;
	}

	//상품 등록 시 상품 상세 조회 화면 이동
		public ModelAndView productView(int pnumber, int page) {
			System.out.println("상품 등록 시 상품 상세 조회 화면 이동 Service");
			mav = new ModelAndView();
			
			ProductDTO product = pdao.productView(pnumber);
			
			mav.addObject("page",page);
			
			mav.addObject("product",product);
			mav.setViewName("productview");
			
			return mav;
		}
	
	// 상품수정화면이동
	public ModelAndView productUpdate(int pnumber) {
		System.out.println("상품수정화면이동 Service");
		mav = new ModelAndView();
		ProductDTO product = pdao.productView(pnumber);
		mav.addObject("productUpdate",product);
		mav.setViewName("productupdate");
		return mav;
	}
	
	// 상품수정처리
	public ModelAndView productUpdateProcess(ProductDTO product) {
		System.out.println("상품수정처리 Service");
		mav = new ModelAndView();
		
		int updateResult = pdao.productUpdateProcess(product);
		if(updateResult > 0) {
			mav.setViewName("redirect:/productview?pnumber=" + product.getPnumber());
		}
		return mav;
	}
	
	// 상품삭제
	public ModelAndView productDelete(int pnumber) {
		System.out.println("상품삭제 Service");
		mav = new ModelAndView();
		int deleteResult = pdao.productDelete(pnumber);
		
		mav.addObject("product",deleteResult);
		
		if(deleteResult > 0) {
			mav.setViewName("redirect:/paging");
		}
		return mav;
	}

	// 검색
		public ModelAndView productSearch(String searchType, String keyword, int page) {
			System.out.println("service + boardSearch : " + searchType + "+" + keyword);
			mav = new ModelAndView();
			Map<String, String> searchMap = new HashMap<String, String>();
			searchMap.put("type", searchType);
			searchMap.put("word", keyword);
			System.out.println("service + boardSearch : " + searchType);
			System.out.println("service + boardSearch : " + keyword);
			System.out.println("service + boardSearch : " + page);
			// 검색
			List<ProductDTO> product = pdao.productSearch(searchMap);
			if (product.size() > 0) {
				System.out.println("service + boardSearch : 검색 내용 있음");
				// 게시글
				System.out.println("service + boardSearch : " + product);
				System.out.println("service + boardSearch : " + product.size());
				// 게시글 갯수
				int listCount = pdao.sListCount(searchMap);
				System.out.println("service + listCount : " + listCount);
				int startRow = (page - 1) * PAGE_LIMIT + 1;
				int endRow = page * PAGE_LIMIT;
				PageDTO paging = new PageDTO();
				// paging.setStartRow(startRow);
				// paging.setEndRow(endRow);
				// * 스타트, 엔드 값 int 에서 String 로 변환 후 map 담아 dao에 보내준다.
				String startRow2 = String.valueOf(startRow);
				String endRow2 = String.valueOf(endRow);
				searchMap.put("startRow", startRow2);
				searchMap.put("endRow", endRow2);
				System.out.println("service + startRow : " + startRow + 1);
				System.out.println("service + endRow : " + endRow + 1);
				System.out.println("service + startRow2 : " + startRow2 + 1);
				System.out.println("service + endRow2 : " + endRow2 + 1);
				System.out.println("service + paging : " + paging.toString());
				// 페이징
				List<ProductDTO> productList = pdao.sProductPaging(searchMap);
				int maxPage = (int) (Math.ceil((double) listCount / PAGE_LIMIT));
				int startPage = (((int) (Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
				int endPage = startPage + BLOCK_LIMIT - 1;
				if (endPage > maxPage) {
					endPage = maxPage;
				}
				paging.setPage(page);
				paging.setStartPage(startPage);
				paging.setEndPage(endPage);
				paging.setMaxPage(maxPage);
				mav.addObject("productList", productList);
				mav.addObject("searchType", searchType);
				mav.addObject("keyword", keyword);
				mav.addObject("paging", paging);
				mav.setViewName("productserch");
				System.out.println("service + page : " + page);
				System.out.println("service + startPage : " + startPage);
				System.out.println("service + endPage : " + endPage);
				System.out.println("service + maxPage : " + maxPage);
				System.out.println("service + productList.size() : " + productList.size());
				System.out.println("service + productList.toString() : " + productList.toString());
			} else {
				System.out.println("service + productList : 없음");
				mav.setViewName("productserch");
			}
			return mav;
		}

}
