package com.example.reactchallengestudybackend2.domain.comment.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentUpdateRequestDto {

    private String writer;
    private String content;

    @Builder
    public CommentUpdateRequestDto(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
