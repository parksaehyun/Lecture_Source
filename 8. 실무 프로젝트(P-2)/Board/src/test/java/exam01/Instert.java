package exam01;

import configs.DBConn2;
import board.Board;
import mappers.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class Instert {
    @Test
    void test1() {
        SqlSession session = DBConn2.getSession();
        Board board = Board.builder()
                .POSTER("SCHOOL")
                .SUBJECT("ENGLISH")
                .CONTENT("SPEAKING")
                .build();
        int plus1 = session.insert("mappers.BoardMapper.plusBoard", board);
        System.out.println(plus1);
    }

    @Test
    void test2() {
        SqlSession session = DBConn2.getSession();
        BoardMapper mapper = session.getMapper(BoardMapper.class);
        Board board = Board.builder()
                .POSTER("SCHOOL")
                .SUBJECT("ENGLISH")
                .CONTENT("SPEAKING")
                .build();
        int plus1 = mapper.plusBoard(board);
        System.out.println(plus1);
    }
}
