package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService service;
	
	// 게시물에 달린 댓글 목록을 반환하는 메소드
	@GetMapping("/list")  // /comment/list?boardNo=1
	public List<CommentDTO> list(@RequestParam(name = "boardNo") int boardNo) {
		
		// 댓글 목록 조회
		List<CommentDTO> list = service.getListByBoardNo(boardNo);
		
		return list;  //댓글 목록 전송
	}
	
	// 댓글 등록 처리 메소드
	@PostMapping("/register")
	public int register(CommentDTO dto) {
		
		// 스프링 시큐리티를 처리하기 전까지는 임시 아이디 사용
		dto.setWriter("user3");
		
		// 테이블에 댓글 저장
		int newNo = service.register(dto);
		
		// 처리 결과 반환
		return newNo;
	}
	
	// 댓글 삭제 메소드
	@DeleteMapping("/remove")
	public Boolean remove(@RequestParam(name = "commentNo") int commentNo) {
		
		// 댓글 삭제
		boolean result = service.remove(commentNo);
		
		// 처리 결과 반환
		return result;
	}
	
}
