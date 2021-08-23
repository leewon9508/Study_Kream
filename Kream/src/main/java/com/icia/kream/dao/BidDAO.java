package com.icia.kream.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.BidDTO;
import com.icia.kream.dto.PageDTO;

@Repository
public class BidDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 구매 입찰 페이지 이동 + 구매 입찰 리스트 출력 + 갯수 확인
	public int ListCount(int bidpnumber) {
		System.out.println("bdao + listCount : " + bidpnumber);
		return sql.selectOne("bidm.listcount",bidpnumber);
	}

	// 구매 입찰 페이지 이동 + 구매 입찰 리스트 출력
	public List<BidDTO> bidPage(Map<String, String> bidMap) {
		System.out.println("bdao + bidPage : " + bidMap);
		return sql.selectList("bidm.bidpage",bidMap);
	}

	// 구매 입찰 등록하기
	public void bidInPut(BidDTO bid) {
		System.out.println("bdao + bidInPut : " + bid);
		sql.insert("bidm.bidinput", bid);
	}

	// 구매 입찰 아이디 중복 확인
	public String bidIdCheck(Map<String, String> bidMap) {
		System.out.println("bdao + bidIdCheck : " + bidMap);
		return sql.selectOne("bidm.bididcheck", bidMap);
	}

	// 구매 입찰 내용 수정 요청
	public BidDTO bidUpDate(int bidnumber) {
		System.out.println("bdao + bidUpDate : " + bidnumber);
		return sql.selectOne("bidm.bidupdate", bidnumber);
	}

	// 구매 입찰 내용 수정
	public void bidUpDateProcess(BidDTO bid) {
		System.out.println("bdao + bidUpDateProcess : " + bid);
		sql.update("bidm.bidupdateprocess", bid);
	}

	// 마이페이지 + 구매 입찰 한 리스트 출력 + 갯수
	public int bidListCount(String bidid) {
		System.out.println("bdao + bidListCount : " + bidid);
		return sql.selectOne("bidm.bidlistcount",bidid);
	}
	
	// 마이페이지 + 구매 입찰 한 리스트 출력
	public List<BidDTO> bididPage(Map<String, String> bidMap) {
		System.out.println("bdao + bididPage : " + bidMap);
		return sql.selectList("bidm.bididpage",bidMap);
	}

	// 구매 입찰 취소
	public void bidDelete(int bidnumber) {
		System.out.println("bdao + bidDelete : " + bidnumber);
		sql.delete("bidm.biddelete", bidnumber);
	}
}
