package com.example.reactchallengestudybackend2.domain.board.entity;

import com.example.reactchallengestudybackend2.common.BaseTimeEntity;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.reactchallengestudybackend2.domain.comment.entity.Comment;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boards")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    @Builder
    public Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void update(BoardUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
