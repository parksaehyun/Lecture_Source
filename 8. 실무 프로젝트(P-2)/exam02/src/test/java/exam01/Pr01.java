package exam01;

import configs.DBConn2;
import member.Board;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

public class Pr01 {
    @Test
    void test1() {
        SqlSession session = DBConn2.getSession();
        Board board = Board.builder()
                .POSTER("SCHOOL")
                .SUBJECT("ENGLISH")
                .CONTENT("SPEAKING")
                .build();
        String plus1 = session.selectOne("mappers.BoardMapper.plusBoard");
        System.out.println(plus1);
    }
}
