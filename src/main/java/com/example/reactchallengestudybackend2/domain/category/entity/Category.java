package com.example.reactchallengestudybackend2.domain.category.entity;

import com.example.reactchallengestudybackend2.common.BaseTimeEntity;
import com.example.reactchallengestudybackend2.domain.category.dto.request.CategoryUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "categories")
public class Category extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CategoryStatus status;

    @OneToMany(mappedBy = "category")
    private List<CategoryBridge> categoryBridges;

    @Builder
    public Category(Long id, String categoryName, CategoryStatus status) {
        this.id = id;
        this.categoryName = categoryName;
        this.status = status;
    }

    public void update(CategoryUpdateDto requestDto) {
        this.categoryName = requestDto.getCategoryName();
        this.status = requestDto.getStatus();
    }

    public void updateStatus() {
        this.status = CategoryStatus.REMOVE;
    }
}
