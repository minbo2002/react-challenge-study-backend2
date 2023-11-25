package com.example.reactchallengestudybackend2.domain.board.service;

import com.example.reactchallengestudybackend2.common.exception.CustomApiException;
import com.example.reactchallengestudybackend2.common.exception.ResponseCode;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardCreateDto;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardSearchDto;
import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardUpdateDto;
import com.example.reactchallengestudybackend2.domain.board.dto.response.BoardResponse;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import com.example.reactchallengestudybackend2.domain.board.repository.BoardRepository;
import com.example.reactchallengestudybackend2.domain.category.entity.Category;
import com.example.reactchallengestudybackend2.domain.category.entity.CategoryBridge;
import com.example.reactchallengestudybackend2.domain.category.repository.CategoryBridgeRepository;
import com.example.reactchallengestudybackend2.domain.category.repository.CategoryRepository;
import com.example.reactchallengestudybackend2.domain.user.entity.User;
import com.example.reactchallengestudybackend2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryBridgeRepository categoryBridgeRepository;

    // 게시판 생성
    @Transactional
    @Override
    public BoardResponse createBoard(BoardCreateDto requestDto, String userEmail) {

        Board board = requestDto.toEntity();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new CustomApiException(ResponseCode.USER_NOT_FOUND));

        board.setUser(user);

        Board saveBoard = boardRepository.save(board);

        // 카테고리 브릿지 생성
        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_CATEGORY));
        categoryBridgeRepository.save(CategoryBridge.of(saveBoard, category));

        return BoardResponse.toDto(saveBoard);
    }

    // 게시판 리스트 조회
    @Override
    public List<BoardResponse> getBoards() {

        List<Board> boards = boardRepository.findAll();

        return boards.stream()
                .map(BoardResponse::toDto)
                .collect(Collectors.toList());
    }

    // 게시판 리스트 조회(페이징, 검색)
    @Override
    public Page<BoardResponse> getBoardList(Pageable pageable, String title, String content) {

        return boardRepository.findBoardList(pageable, title, content)
                .map(BoardResponse::toDto);
    }

    // 게시판 리스트 조회 Querydsl (페이징, 검색)
    @Override
    public Page<BoardResponse> getBoardList(Pageable pageable, BoardSearchDto searchDto) {

        return boardRepository.getBoards(pageable, searchDto)
                .map(BoardResponse::toDto);
    }

    // 게시판 상세조회
    @Override
    public BoardResponse getBoard(Long id) {

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_BOARD));

        return BoardResponse.toDto(board);
    }

    // 게시판 수정
    @Transactional
    @Override
    public BoardResponse updateBoard(Long id, BoardUpdateDto requestDto) {

        log.info("boardId : {}", id);
        log.info("requestDto : {}", requestDto);

        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new CustomApiException(ResponseCode.NO_TARGET_BOARD));

        board.update(requestDto);

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

    // fetch join 테스트
    @Override
    public List<BoardResponse> getBoardListWithComment() {

        List<Board> boardWithComment = boardRepository.findBoardWithComment();

        return boardWithComment.stream()
                .map(BoardResponse::toDto)
                .collect(Collectors.toList());
    }
}
