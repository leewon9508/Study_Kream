package com.icia.kream.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.ProductDTO;
import com.icia.kream.dto.PageDTO;

@Repository
public class ProductDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	public void registrationProcess(ProductDTO product) {
		sql.insert("pm.registrationprocess",product);
		
	}

	public ProductDTO productView(int pnumber) {
		System.out.println("pdao + productView : " + pnumber);
		return sql.selectOne("pm.productview",pnumber);
	}

	public int listCount() {
		return sql.selectOne("pm.listcount");
	}
	
	public List<ProductDTO> productPaging(PageDTO paging) {
		return sql.selectList("pm.productpaging", paging);
		}

	public int productUpdateProcess(ProductDTO product) {
		return sql.update("pm.productupdate", product);
	}

	public int productDelete(int pnumber) {
		return sql.delete("pm.productdelete",pnumber);
	}

	// 검색
	public List<ProductDTO> productSearch(Map<String, String> searchMap) {
		System.out.println("pdao + productSearch : " + searchMap);
		return sql.selectList("pm.productserch", searchMap);
	}
	// 검색 갯수
	public int sListCount(Map<String, String> searchMap) {
		System.out.println("pdao + sListCount : " + searchMap);
		return sql.selectOne("pm.slistcount", searchMap);
	}

	// 검색 페이징
	public List<ProductDTO> sProductPaging(Map<String, String> searchMap) {
		System.out.println("pdao + sProductPaging : " + searchMap);
		return sql.selectList("pm.sproductpaging", searchMap);
	}
}
