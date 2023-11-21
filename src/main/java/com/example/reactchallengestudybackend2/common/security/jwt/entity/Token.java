package com.example.reactchallengestudybackend2.common.security.jwt.entity;

import com.example.reactchallengestudybackend2.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "token")
@ToString(exclude = {"user"})  // 무한루프에 의한 StackOverflowError 방지를 위해 user를 @ToString에서 제외
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "expired_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime expiredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void createDatetime() {
        this.createdAt = LocalDateTime.now(); // 등록일은 현재 시점으로 설정
        this.expiredAt = LocalDateTime.now().plusMonths(1); // Refresh Token 만료일은 현재로부터 한달뒤로 설정
    }

    @Builder
    public Token(String accessToken, String refreshToken, LocalDateTime createdAt, LocalDateTime expiredAt, User user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
    }
}
