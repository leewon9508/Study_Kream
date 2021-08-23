package com.icia.kream.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icia.kream.dto.CommentDTO;
import com.icia.kream.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService cs;

	// 댓글 등록 + 출력
	@RequestMapping(value = "/commentwrite")
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO comment) {
		System.out.println("controller + commentWriter : " + comment);
		List<CommentDTO> commentList = cs.commentWrite(comment);
		return commentList;
	}

	// 댓글 삭제
	@RequestMapping(value = "/commentDelete")
	public @ResponseBody List<CommentDTO> commentDelete(@ModelAttribute CommentDTO comment) {
		System.out.println("controller + commentDelete" + comment);
		List<CommentDTO> commentList = cs.commentDelete(comment);
		return commentList;
	}
}
