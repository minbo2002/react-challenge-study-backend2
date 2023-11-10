package com.example.reactchallengestudybackend2.domain.board.repository;

import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.title like %:title% and b.content like %:content% order by b.id desc")
    Page<Board> findBoardList(Pageable pageable,
                              @Param("title") String title,
                              @Param("content") String content);
}
