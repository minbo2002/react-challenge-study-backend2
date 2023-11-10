package com.example.reactchallengestudybackend2.domain.board.controller;

import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardCreateRequestDto;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.reactchallengestudybackend2.domain.board.dto.response.BoardResponse;
import com.example.reactchallengestudybackend2.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping("")
    public ResponseEntity<BoardResponse> createBoard(@RequestBody @Valid BoardCreateRequestDto requestDto) {

        BoardResponse createBoard = boardService.createBoard(requestDto);

        return new ResponseEntity<>(createBoard, HttpStatus.CREATED);
    }

    // 게시판 리스트 조회(페이징, 검색)
    @GetMapping("/list")
    public ResponseEntity<Page<BoardResponse>> getBoardList(Pageable pageable,
                                                            @RequestParam(required = false) String title,
                                                            @RequestParam(required = false) String content) {

        Page<BoardResponse> boardList = boardService.getBoardList(pageable, title, content);

        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    // 게시판 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long id) {

        BoardResponse board = boardService.getBoard(id);

        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    // 게시판 수정
    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(@PathVariable Long id,
                                                     @RequestBody BoardUpdateRequestDto requestDto) {

        BoardResponse updateBoard = boardService.updateBoard(id, requestDto);

        return new ResponseEntity<>(updateBoard, HttpStatus.OK);
    }

    // 게시판 삭제
    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {

        boardService.deleteBoard(id);
    }
}
