package com.example.reactchallengestudybackend2.domain.category.dto.response;

import com.example.reactchallengestudybackend2.domain.board.dto.response.BoardResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CategoryWithBoardListDto {

    private final Long id;
    private final String categoryName;
    private final List<BoardResponse> boards;

    @Builder
    public CategoryWithBoardListDto(Long id, String categoryName, List<BoardResponse> boards) {
        this.id = id;
        this.categoryName = categoryName;
        this.boards = boards;
    }
}
