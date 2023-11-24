package com.example.reactchallengestudybackend2.domain.comment.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentCreateDto {

    private String writer;
    private String content;

    @Builder
    public CommentCreateDto(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

}
