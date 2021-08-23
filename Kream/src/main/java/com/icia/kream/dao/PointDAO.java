package com.icia.kream.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.AccountDTO;
import com.icia.kream.dto.MemberDTO;

@Repository
public class PointDAO {

	@Autowired
	private SqlSessionTemplate sql;

	// 포인트 충전
	public int pointAccount(MemberDTO member) {
		System.out.println("pdao + pointAccount : " + member);
		return sql.update("pim.pointaccount", member);
	}

	// 포인트 충전 후 상세 조회
	public MemberDTO memberPoint(String mid) {
		System.out.println("pdao + memberPoint : " + mid);
		return sql.selectOne("pim.memberpoint", mid);
	}

	// 포인트 충전 ACCOUNT 테이블 데이터 추가
	public int pointAccountPlus(AccountDTO account) {
		System.out.println("pdao + pointAccountPlus : " + account);
		return sql.update("pim.pointaccountplus", account);
	}

	// 포인트 충전 리스트 갯수
	public int ListCount(String mid) {
		System.out.println("pdao + ListCount : " + "+" + mid);
		return sql.selectOne("pim.listcount", mid);
	}

	// 포인트 충전 리스트 출력
	public List<AccountDTO> pointAccountList(Map<String, String> searchMap) {
		System.out.println("pdao + pointAccountList : " + searchMap);
		return sql.selectList("pim.pointaccountlist", searchMap);
	}

	// 포인트 사용 내역 보기 (구매) + 갯수
	public int useListCount(String dbidid) {
		System.out.println("pdao + ListCount : " + "+" + dbidid);
		return sql.selectOne("pim.uselistcount", dbidid);
	}

	// 포인트 사용 내역 보기 (구매) + 리스트
	public List<AccountDTO> pointAccountUseList(Map<String, String> searchMap) {
		System.out.println("pdao + pointAccountList : " + searchMap);
		return sql.selectList("pim.pointaccountuselist", searchMap);
	}

	// 즉시 구매 or 판매 + Member 잔액 확인
	public String pointCheck(Map<String, String> chekMap) {
		System.out.println("pdao + pointCheck : " + chekMap);
		return sql.selectOne("pim.pointcheck", chekMap);
	}

	// 즉시 구매 + 구매자 포인트 감소
	public int pointMinus(MemberDTO member) {
		System.out.println("pdao + pointMinus : " + member);
		return sql.update("pim.pointminus", member);
	}

	// 즉시 구매 + member 포인트 + 결과 확인
	public MemberDTO memberPointResult(String mid) {
		System.out.println("pdao + memberPointResult : " + mid);
		return sql.selectOne("pim.memberpointresult", mid);
	}

	// 즉시 구매 + 판매자 포인트 증가
	public int pointPlus(MemberDTO member) {
		System.out.println("pdao + pointPlus : " + member);
		return sql.update("pim.pointplus", member);
	}

	public MemberDTO pointPage(String mid) {
		System.out.println("pdao + pointPage : " + mid);
		return sql.selectOne("pim.pointpage", mid);
	}

}
