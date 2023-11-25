package com.example.reactchallengestudybackend2.domain.board.service;

import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardCreateDto;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardSearchDto;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardUpdateDto;
import com.example.reactchallengestudybackend2.domain.board.dto.response.BoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    // 게시판 생성
    BoardResponse createBoard(BoardCreateDto requestDto, String userEmail);

    // 게시판 리스트 조회
    List<BoardResponse> getBoards();

    // 게시판 리스트 조회(페이징, 검색)
    Page<BoardResponse> getBoardList(Pageable pageable, String title, String content);

    // 게시판 리스트 조회 Querydsl (페이징, 검색)
    Page<BoardResponse> getBoardList(Pageable pageable, BoardSearchDto searchDto);

    // 게시판 상세조회
    BoardResponse getBoard(Long id);

    // 게시판 수정
    BoardResponse updateBoard(Long id, BoardUpdateDto requestDto);

    // 게시판 삭제
    void deleteBoard(Long id);

    // 게시판 리스트 fetchjoin 조회
    List<BoardResponse> getBoardListWithComment();
}
