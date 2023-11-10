package com.example.reactchallengestudybackend2.domain.comment.dto.request;

import com.example.reactchallengestudybackend2.domain.comment.entity.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentCreateRequestDto {

    private String writer;
    private String content;

    @Builder
    public CommentCreateRequestDto(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

}
