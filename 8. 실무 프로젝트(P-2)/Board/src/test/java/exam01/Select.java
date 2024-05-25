package exam01;

import board.Board;
import configs.DBConn2;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Select {
    @Test
    void test1() {
        SqlSession session = DBConn2.getSession();
        List<Board> members = session.selectList("mappers.BoardMapper.getBoard");
        members.forEach(System.out::println);
    }
}
