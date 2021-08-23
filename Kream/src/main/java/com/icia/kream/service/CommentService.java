package com.icia.kream.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.kream.dao.CommentDAO;
import com.icia.kream.dto.CommentDTO;

@Service
public class CommentService {
	@Autowired
	private CommentDAO cdao;

	//댓글
	public List<CommentDTO> commentWrite(CommentDTO comment) {
		System.out.println("service + commentwrite : " + comment);
		// 댓글 등록
		int writeResult = cdao.commentWrite(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		if (writeResult > 0) {
			// 댓글 목록 출력
			commentList = cdao.CommentList(comment.getCbnumber());
		} else {
			commentList = null; 
		}
		return commentList;
	}

	// 댓글 삭제
	public List<CommentDTO> commentDelete(CommentDTO comment) {
		System.out.println("service + commentDelete : " + comment);
		// 댓글 삭제
		cdao.commentDelete(comment);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		System.out.println(comment.getCbnumber());
		// 댓글 목록 출력
		commentList = cdao.CommentList(comment.getCbnumber());
		return commentList;
	}

}
