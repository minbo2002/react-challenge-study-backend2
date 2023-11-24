package com.example.reactchallengestudybackend2.domain.board.dto.request;

import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardCreateDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    public BoardCreateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board toEntity() {

        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
