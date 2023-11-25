package com.example.reactchallengestudybackend2.domain.board.repository;

import com.example.reactchallengestudybackend2.domain.board.dto.request.BoardSearchDto;
import com.example.reactchallengestudybackend2.domain.board.entity.Board;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import java.util.List;

import static com.example.reactchallengestudybackend2.domain.board.entity.QBoard.*;

@Slf4j
@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Board> getBoards(Pageable pageable, BoardSearchDto searchDto) {

        List<Board> boards = queryFactory
                .selectFrom(board)
                .leftJoin(board.categoryBridges).fetchJoin()
                .where(eqTitle(searchDto.getTitle()),
                       eqContent(searchDto.getContent()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(board.id.desc())
                .fetch();// 결과반환 용도

        JPAQuery<Long> countQuery = queryFactory
                .select(board.count())
                .from(board)
                .where(eqTitle(searchDto.getTitle()),
                       eqContent(searchDto.getContent()));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqTitle(String title) {
        if (title == null || title.isEmpty()) {
            return null;
        }
        return board.title.like("%"+title+"%");
    }

    private BooleanExpression eqContent(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        return board.content.like("%"+content+"%");
    }
}
