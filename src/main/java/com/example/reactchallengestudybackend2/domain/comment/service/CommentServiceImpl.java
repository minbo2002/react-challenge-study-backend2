package com.example.reactchallengestudybackend2.domain.comment.service;

import com.example.reactchallengestudybackend2.common.exception.CustomApiException;
import com.example.reactchallengestudybackend2.common.exception.ResponseCode;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import com.example.reactchallengestudybackend2.domain.board.repository.BoardRepository;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentCreateRequestDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.reactchallengestudybackend2.domain.comment.dto.response.CommentResponse;
import com.example.reactchallengestudybackend2.domain.comment.entity.Comment;
import com.example.reactchallengestudybackend2.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 생성
    @Transactional
    @Override
    public CommentResponse createComment(Long boardId, CommentCreateRequestDto requestDto) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_BOARD));

        Comment comment = Comment.builder()
                .writer(requestDto.getWriter())
                .content(requestDto.getContent())
                .board(board)
                .build();

        Comment saveComment = commentRepository.save(comment);

        return CommentResponse.toDto(saveComment);
    }

    // 댓글 조회
    @Override
    public CommentResponse getComment(Long id) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_COMMENT));

        return CommentResponse.toDto(comment);
    }

    // 댓글 수정
    @Transactional
    @Override
    public CommentResponse updateComment(Long id, CommentUpdateRequestDto requestDto) {

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_COMMENT));

        comment.update(requestDto);

        return CommentResponse.toDto(comment);
    }

    // 댓글 삭제
    @Transactional
    @Override
    public void deleteComment(Long id) {

        commentRepository.deleteById(id);
    }
}
