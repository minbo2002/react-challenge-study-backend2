package com.example.reactchallengestudybackend2.domain.board.controller;

import com.example.reactchallengestudybackend2.common.security.dto.PrincipalDetails;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) // CORS 허용
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping("")
    public ResponseEntity<BoardResponse> createBoard(@RequestBody @Valid BoardCreateRequestDto requestDto,
                                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {

        log.info("requestDto: {}", requestDto);
        log.info("user email: {}", principalDetails.getUser().getEmail());
        String userEmail = principalDetails.getUser().getEmail();

        BoardResponse createBoard = boardService.createBoard(requestDto, userEmail);

        return new ResponseEntity<>(createBoard, HttpStatus.CREATED);
    }

    // 게시판 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardResponse>> getBoardList() {

        List<BoardResponse> boardList = boardService.getBoards();

        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    // 게시판 리스트 조회(페이징, 검색)
    @GetMapping("/list/paging")
    public ResponseEntity<Page<BoardResponse>> getBoardList(Pageable pageable,
                                                            @RequestParam(required = false) String title,
                                                            @RequestParam(required = false) String content) {

        Page<BoardResponse> boardList = boardService.getBoardList(pageable, title, content);

        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    // 게시판 상세조회
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

    // 게시판 리스트 조회 fetchjoin 테스트
    @GetMapping("/list/fetchjoin")
    public ResponseEntity<List<BoardResponse>> getBoardListWithComment() {

        List<BoardResponse> boardList = boardService.getBoardListWithComment();

        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }
}
