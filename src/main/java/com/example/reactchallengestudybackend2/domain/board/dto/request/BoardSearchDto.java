package com.example.reactchallengestudybackend2.domain.board.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardSearchDto {

    private String title;
    private String content;

    @Builder
    public BoardSearchDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
