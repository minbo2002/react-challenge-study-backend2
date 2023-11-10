package com.example.reactchallengestudybackend2.domain.comment.dto.response;

import com.example.reactchallengestudybackend2.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class CommentResponse {

    private final Long id;
    private final String writer;
    private final String content;
    private final Long boardId;

    static public CommentResponse toDto(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .writer(comment.getWriter())
                .content(comment.getContent())
                .boardId(comment.getBoard().getId())
                .build();
    }
}
