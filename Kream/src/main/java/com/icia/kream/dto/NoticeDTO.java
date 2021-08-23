package com.icia.kream.dto;

import lombok.Data;

@Data
public class NoticeDTO {
	//	공지사항 게시판 관리
	private int nnumber;		//글번호		pk
	private String nwriter;		//작성자		fk
	private String ntitle;		//제목
	private String ncontents;	//내용
	private String ndate;		//작성일자(DATE)
}
