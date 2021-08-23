package com.icia.kream.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.icia.kream.dto.PageDTO;
import com.icia.kream.dto.QuestionDTO;

@Repository
public class QuestionDAO {
	@Autowired
	private SqlSessionTemplate sql;

	// 1대1 문의 + 갯수
	public int ListCount() {
		System.out.println("qdao + ListCount");
		return sql.selectOne("qm.listcount");
	}
	
	// 1대1 문의 + 목록 출력
	public List<QuestionDTO> questionPage(PageDTO paging) {
		System.out.println("qdao + questionPage");
		return sql.selectList("qm.questionlist",paging);
	}

	
	// 1대1 문의 글 작성
	public void questionWrite(QuestionDTO question) {
		System.out.println("qdao + questionWrite : " + question);
		sql.insert("qm.questionwrite",question);
	}

	// 1대1 문의 글 조회
	public QuestionDTO questionView(int qnumber) {
		System.out.println("qdao + questionView : " + qnumber);
		return sql.selectOne("qm.questionview",qnumber);
	}

	// 1대1 문의 글 삭제
	public void questionDelete(int qnumber) {
		System.out.println("qdao + questionDelete : " + qnumber);
		sql.delete("qm.questiondelete", qnumber);
	}

	// 1대1 문의 글 수정 요청
	public QuestionDTO questionUpdate(int qnumber) {
		System.out.println("qdao + questionUpdate : " + qnumber);
		return sql.selectOne("qm.questionupdate",qnumber);
	}

	// 1대1 문의 글 수정 처리
	public int questionUpdateProcess(QuestionDTO question) {
		System.out.println("qdao + questionUpdateProcess : " + question);
		return sql.update("qm.questionupdateprocess",question);
	}



}
