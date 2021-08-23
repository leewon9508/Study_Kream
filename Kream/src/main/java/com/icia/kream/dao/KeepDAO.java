package com.icia.kream.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.KeepDTO;
import com.icia.kream.dto.PageDTO;
import com.icia.kream.dto.ProductDTO;

@Repository
public class KeepDAO {

	@Autowired
	private SqlSessionTemplate sql;
	
	//	찜등록
	public void jjim(Map<String, String> jjimMap) {
		sql.insert("km.jjim",jjimMap);
	}

	// 찜 삭제
		public int jjimDelete(int knumber) {
			
			
			return sql.delete("km.jjimdelete",knumber);
		}
	
	//	찜중복체크
	public String id(Map<String, String> jjimXMap) { 
		return sql.selectOne("km.id",jjimXMap);
	}

	//	찜 목록
	public List<KeepDTO> keepList(String kid) {
		return sql.selectList("km.keeplist", kid);
	}

	
	

	



}
