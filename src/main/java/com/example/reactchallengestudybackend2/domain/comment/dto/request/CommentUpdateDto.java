package com.example.reactchallengestudybackend2.domain.comment.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentUpdateDto {

    private String writer;
    private String content;

    @Builder
    public CommentUpdateDto(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
