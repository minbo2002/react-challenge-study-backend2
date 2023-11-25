package com.example.reactchallengestudybackend2.domain.category.repository;

import com.example.reactchallengestudybackend2.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
}
