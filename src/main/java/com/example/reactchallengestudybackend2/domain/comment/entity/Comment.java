package com.example.reactchallengestudybackend2.domain.comment.entity;

import com.example.reactchallengestudybackend2.common.BaseTimeEntity;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import com.example.reactchallengestudybackend2.domain.comment.dto.request.CommentUpdateDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comments")
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Long id, String writer, String content, Board board) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.board = board;
    }

    public void update(CommentUpdateDto requestDto) {
        this.writer = requestDto.getWriter();
        this.content = requestDto.getContent();
    }
}
