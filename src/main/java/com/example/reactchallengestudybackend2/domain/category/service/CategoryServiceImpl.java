package com.example.reactchallengestudybackend2.domain.category.service;

import com.example.reactchallengestudybackend2.common.exception.CustomApiException;
import com.example.reactchallengestudybackend2.common.exception.ResponseCode;
import com.example.reactchallengestudybackend2.domain.board.dto.response.BoardResponse;
import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryCreateDto;
import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryUpdateDto;
import com.example.reactchallengestudybackend2.domain.category.dto.response.CategoryResponse;
import com.example.reactchallengestudybackend2.domain.category.dto.response.CategoryWithBoardListDto;
import com.example.reactchallengestudybackend2.domain.category.entity.Category;
import com.example.reactchallengestudybackend2.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // 카테고리 생성
    @Transactional
    @Override
    public CategoryResponse createCategory(CategoryCreateDto requestDto) {

        Category category = requestDto.toEntity();
        log.info("category = {}", category);

        Category saveCategory = categoryRepository.save(category);

        return CategoryResponse.toDto(saveCategory);
    }

    // 카테고리 리스트 조회
    @Override
    public List<CategoryResponse> getCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(CategoryResponse::toDto)
                .collect(Collectors.toList());
    }

    // 특정 카테고리의 게시판 전체 조회
    @Override
    public CategoryWithBoardListDto getCategoryWithBoardList (Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_CATEGORY));

        return CategoryWithBoardListDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .boards(category.getCategoryBridges().stream()
                        .map(categoryBridge -> BoardResponse.toDto(categoryBridge.getBoard()))
                        .collect(Collectors.toList()))
                .build();
    }

    // 카테고리 수정
    @Transactional
    @Override
    public CategoryResponse updateCategory(Long id, CategoryUpdateDto requestDto) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_CATEGORY));

        category.update(requestDto);

        return CategoryResponse.toDto(category);
    }

    // 카테고리 삭제 (데이터 삭제가 아닌 상태값 REMOVE 변경)
    @Transactional
    @Override
    public CategoryResponse deleteCategory(Long id) {

            Category category = categoryRepository.findById(id)
                    .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_CATEGORY));

            category.updateStatus();

            return CategoryResponse.toDto(category);
    }
}
