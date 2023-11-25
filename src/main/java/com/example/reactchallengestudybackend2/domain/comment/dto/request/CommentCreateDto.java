package com.example.reactchallengestudybackend2.domain.comment.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CommentCreateDto {

    @NotBlank(message = "댓글 내용을 입력해 주세요.")
    private String content;

    @Builder
    public CommentCreateDto( String content) {
        this.content = content;
    }

}
