package com.example.reactchallengestudybackend2.domain.board.service;

import com.example.reactchallengestudybackend2.common.exception.CustomApiException;
import com.example.reactchallengestudybackend2.common.exception.ResponseCode;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardCreateRequestDto;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardUpdateRequestDto;
import com.example.reactchallengestudybackend2.domain.board.dto.response.BoardResponse;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import com.example.reactchallengestudybackend2.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    // 게시판 생성
    @Transactional
    @Override
    public BoardResponse createBoard(BoardCreateRequestDto requestDto) {

        Board saveBoard = boardRepository.save(requestDto.toEntity());

        return BoardResponse.toDto(saveBoard);
    }

    // 게시판 리스트 조회(페이징, 검색)
    @Override
    public Page<BoardResponse> getBoardList(Pageable pageable, String title, String content) {

        return boardRepository.findBoardList(pageable, title, content)
                .map(BoardResponse::toDto);
    }

    // 게시판 조회
    @Override
    public BoardResponse getBoard(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_BOARD));

        return BoardResponse.toDto(board);
    }

    // 게시판 수정
    @Transactional
    @Override
    public BoardResponse updateBoard(Long id, BoardUpdateRequestDto requestDto) {

        log.info("updateBoard id : {}", id);
        log.info("requestDto : {}", requestDto);

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_BOARD));

        board.update(requestDto.getTitle(), requestDto.getContent());

        return BoardResponse.toDto(board);
    }

    // 게시판 삭제
    @Transactional
    @Override
    public void deleteBoard(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_BOARD));

        boardRepository.delete(board);
    }
}
