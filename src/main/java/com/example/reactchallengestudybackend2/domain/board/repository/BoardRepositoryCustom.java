package com.example.reactchallengestudybackend2.domain.board.repository;

import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardSearchDto;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> getBoards(Pageable pageable, BoardSearchDto searchDto);
}
