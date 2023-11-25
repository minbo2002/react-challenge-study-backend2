package com.example.reactchallengestudybackend2.domain.comment.controller;

import com.example.reactchallengestudybackend2.common.security.dto.PrincipalDetails;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentCreateDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentUpdateDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.response.CommentResponse;
import com.example.reactchallengestudybackend2.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
                                                         @RequestBody @Valid CommentCreateDto requestDto,
                                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {

        log.info("boardId: {}", boardId);
        log.info("requestDto: {}", requestDto);
        log.info("user name: {}", principalDetails.getUser().getName());
        String writer = principalDetails.getUser().getName();

        CommentResponse comment = commentService.createComment(boardId, requestDto, writer);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // 특정 게시판에 포함된 댓글 리스트
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
                                                         @RequestBody CommentUpdateDto requestDto) {

        CommentResponse comment = commentService.updateComment(id, requestDto);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);
    }
}
