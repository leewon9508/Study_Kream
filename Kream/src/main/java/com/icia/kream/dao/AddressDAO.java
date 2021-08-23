package com.icia.kream.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.AddressDTO;
import com.icia.kream.dto.DealDTO;

@Repository
public class AddressDAO {

	@Autowired
	public SqlSessionTemplate sql;

	// 주소 등록
	public void addressWrite(AddressDTO address) {
		System.out.println("adao + addressWrite : " + address);
		sql.insert("addm.addresswrite", address);
	}

	// 주소 상세 조회
	public AddressDTO addressView(String id) {
		System.out.println("adao + addressView : " + id);
		return sql.selectOne("addm.addressview", id);
	}

	// 주소 삭제
	public void addressDelete(String id) {
		System.out.println("adao + addressDelete : " + id);
		sql.delete("addm.addressdelete",id);
	}

	

}
