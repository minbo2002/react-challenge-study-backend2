package com.example.reactchallengestudybackend2.domain.comment.controller;

import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentCreateRequestDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.response.CommentResponse;
import com.example.reactchallengestudybackend2.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/{boardId}")
    public ResponseEntity<CommentResponse> createComment(@PathVariable() Long boardId,
                                                         @RequestBody CommentCreateRequestDto requestDto) {

        CommentResponse comment = commentService.createComment(boardId, requestDto);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // 특정 게시판에 포함된 댓글 리스트 조회
    @GetMapping("/board/{boardId}/list")
    public ResponseEntity<List<CommentResponse>> getCommentList(@PathVariable Long boardId) {

        List<CommentResponse> commentList = commentService.getCommentList(boardId);

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    // 댓글 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long id) {

        CommentResponse comment = commentService.getComment(id);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long id,
                                                         @RequestBody CommentUpdateRequestDto requestDto) {

        CommentResponse comment = commentService.updateComment(id, requestDto);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);
    }
}
