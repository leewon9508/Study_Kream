package com.icia.kream.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.DealDTO;

@Repository
public class DealDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 거래 상세 조회
	public DealDTO dealView(int dnumber) {
		System.out.println("ddao + dealView : " + dnumber);
		return sql.selectOne("dealm.dealview", dnumber);
	}

	// 거래 관리 데이터 추가 하기
	public DealDTO dealWrite(DealDTO deal) {
		System.out.println("ddao + dealWrite : " + deal);
		sql.insert("dealm.dealwrite", deal);
		int key = deal .getDnumber();
		System.out.println("key : " + key);
		return sql.selectOne("dealm.dealview", key);
	}

	// 구매 내역 갯수
	public int bidListCount(String dbidid) {
		System.out.println("ddao + bidListCount : " + dbidid);
		return sql.selectOne("dealm.bidlistcount", dbidid);
	}

	// 구매 내역 리스트
	public List<DealDTO> bidHistory(Map<String, String> bidMap) {
		System.out.println("ddao + bidHistory : " + bidMap);
		return sql.selectList("dealm.bidhistory", bidMap);
	}

	// 구매 내역 갯수
	public int askListCount(String daskid) {
		System.out.println("ddao + askListCount : " + daskid);
		return sql.selectOne("dealm.asklistcount", daskid);
	}

	// 구매 내역 리스트
	public List<DealDTO> askHistory(Map<String, String> askMap) {
		System.out.println("ddao + askHistory : " + askMap);
		return sql.selectList("dealm.askhistory", askMap);
	}

}
