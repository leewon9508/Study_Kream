package com.icia.kream.dto;

import lombok.Data;

@Data
public class CommentDTO {
	private int cnumber;
	private int cbnumber;
	private String cwriter;
	private String ccontents;
	private String cdate;
}
