package com.example.reactchallengestudybackend2.domain.comment.service;

import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentCreateDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentUpdateDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    // 댓글 생성
    CommentResponse createComment(Long boardId, CommentCreateDto requestDto, String writer);

    // 특정 게시판에 포함된 댓글 리스트 조회
    List<CommentResponse> getCommentList(Long boardId);

    // 댓글 조회
    CommentResponse getComment(Long id);

    // 댓글 수정
    CommentResponse updateComment(Long id, CommentUpdateDto requestDto);

    // 댓글 삭제
    void deleteComment(Long id);
}
