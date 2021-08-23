package com.icia.kream.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.icia.kream.dto.NoticeDTO;
import com.icia.kream.dto.PageDTO;

@Repository
public class NoticeDAO {
	
	@Autowired
	private SqlSessionTemplate sql;
	
	// 고객센터 페이지 이동 + 공지사항 목록 출력 + 글 갯수
	public int ListCount() {
		System.out.println("dao + ListCount");
		return sql.selectOne("nt.listcount");
	}

	// 고객센터 페이지 이동 + 공지사항 목록 출력 + 리스트
	public List<NoticeDTO> noticePage(PageDTO paging) {
		System.out.println("qdao + noticePage : " + paging);
		return sql.selectList("nt.noticepage", paging);
	}
	
	// 공지사항 글 작성
	public void noticeWrite(NoticeDTO notice) {
		System.out.println("qdao + noticeWrite " + notice);
		sql.insert("nt.noticewrite", notice);
	}
	
	// 공지사항 글 조회
	public NoticeDTO noticeView(int nnumber) {
		System.out.println("qdao + noticeView : " + nnumber);
		return sql.selectOne("nt.noticeview",nnumber);
	}
	
	// 공지사항 글 삭제 (관리자 전용)
	public void noticeDelete(int nnumber) {
		System.out.println("qdao + noticeDelete : " + nnumber);
		sql.delete("nt.noticedelete",nnumber);
	}

}
