package com.example.reactchallengestudybackend2.domain.category.controller;

import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryCreateDto;
import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryUpdateDto;
import com.example.reactchallengestudybackend2.domain.category.dto.response.CategoryResponse;
import com.example.reactchallengestudybackend2.domain.category.dto.response.CategoryWithBoardListDto;
import com.example.reactchallengestudybackend2.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 생성
    @PostMapping("")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryCreateDto requestDto) {
        log.info("requestDto: {}", requestDto);

        CategoryResponse category = categoryService.createCategory(requestDto);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // 카테고리 리스트
    @GetMapping("/list")
    public ResponseEntity<List<CategoryResponse>> getCategories() {

        List<CategoryResponse> categories = categoryService.getCategories();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // 특정 카테고리의 게시판 전체 조회
    @GetMapping("/{categoryId}/board/list")
    public ResponseEntity<CategoryWithBoardListDto> getCategoryWithBoardList(@PathVariable Long categoryId) {

        CategoryWithBoardListDto categories = categoryService.getCategoryWithBoardList(categoryId);

        return new ResponseEntity<CategoryWithBoardListDto>(categories, HttpStatus.OK);
    }

    // 카테고리 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id,
                                                           @RequestBody CategoryUpdateDto requestDto) {

        CategoryResponse category = categoryService.updateCategory(id, requestDto);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // 카테고리 삭제 (데이터 삭제가 아닌 상태값 변경)
    @PatchMapping("/status/{id}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Long id) {

        CategoryResponse category = categoryService.deleteCategory(id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
