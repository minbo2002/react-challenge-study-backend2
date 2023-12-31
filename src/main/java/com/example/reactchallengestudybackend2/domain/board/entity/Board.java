package com.example.reactchallengestudybackend2.domain.board.entity;

import com.example.reactchallengestudybackend2.common.BaseTimeEntity;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardUpdateDto;
import com.example.reactchallengestudybackend2.domain.category.entity.CategoryBridge;
import com.example.reactchallengestudybackend2.domain.comment.entity.Comment;
import com.example.reactchallengestudybackend2.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "boards")
@ToString(exclude = {"user", "comments", "categoryBridges"})
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;

    @OneToMany(mappedBy = "board")
    private List<CategoryBridge> categoryBridges;

    @Builder
    public Board(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public void update(BoardUpdateDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
