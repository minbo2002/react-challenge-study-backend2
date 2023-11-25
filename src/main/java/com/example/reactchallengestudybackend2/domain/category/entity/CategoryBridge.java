package com.example.reactchallengestudybackend2.domain.category.entity;

import com.example.reactchallengestudybackend2.common.BaseTimeEntity;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Table(name = "category_bridges")
@ToString(exclude = {"board", "category"})
public class CategoryBridge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_bridge_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    public CategoryBridge(Long id, Board board, Category category) {
        this.id = id;
        this.board = board;
        this.category = category;
    }

    public static CategoryBridge of(Board board, Category category) {
        return CategoryBridge.builder()
                .board(board)
                .category(category)
                .build();
    }
}
