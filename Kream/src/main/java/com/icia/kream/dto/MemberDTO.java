package com.icia.kream.dto;

import lombok.Data;

@Data
public class MemberDTO {
	//	회원 정보 관리
	private String mid;			//회원아이디		pk
	private String mpassword;	//비밀번호
	private String mname;		//회원이름
	private String mphone;		//전화번호
	private int maccount;
}
