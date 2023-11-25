package com.example.reactchallengestudybackend2.domain.category.service;

import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryCreateDto;
import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryUpdateDto;
import com.example.reactchallengestudybackend2.domain.category.dto.response.CategoryResponse;
import com.example.reactchallengestudybackend2.domain.category.dto.response.CategoryWithBoardListDto;

import java.util.List;

public interface CategoryService {

    // 카테고리 생성
    CategoryResponse createCategory(CategoryCreateDto requestDto);

    // 카테고리 리스트 조회
    List<CategoryResponse> getCategories();

    // 특정 카테고리의 게시판 전체 조회
    CategoryWithBoardListDto getCategoryWithBoardList(Long categoryId);

    // 카테고리 수정
    CategoryResponse updateCategory(Long id, CategoryUpdateDto requestDto);

    // 카테고리 삭제 (데이터 삭제가 아닌 상태값 변경)
    CategoryResponse deleteCategory(Long id);
}
