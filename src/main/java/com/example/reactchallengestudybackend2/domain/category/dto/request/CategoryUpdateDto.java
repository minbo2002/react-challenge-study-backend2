package com.example.reactchallengestudybackend2.domain.category.dto.request;

import com.example.reactchallengestudybackend2.domain.category.entity.CategoryStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CategoryUpdateDto {

    private String categoryName;
    private CategoryStatus status;

    @Builder
    public CategoryUpdateDto(String categoryName, CategoryStatus status) {
        this.categoryName = categoryName;
        this.status = status;
    }
}
