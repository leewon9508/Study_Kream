package com.icia.kream.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.CommentDTO;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sql;

	// 댓글 등록
	public int commentWrite(CommentDTO comment) {
		System.out.println("cdao + CommentWrite : " + comment);
		return sql.insert("cm.commentwrite",comment);
	}

	// 댓글 목록 출력
	public List<CommentDTO> CommentList(int cbnumber) {
		System.out.println("cdao + commentList : " + cbnumber);
		return sql.selectList("cm.commentlist", cbnumber);
	}

	// 댓글 삭제
	public void commentDelete(CommentDTO comment) {
		System.out.println("cdao.commentDelete : " + comment);
		sql.delete("cm.commentdelete", comment);
	}


}
