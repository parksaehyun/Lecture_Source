package exam01;

import configs.DBConn;
import mappers.MemberMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import member.Member;

import java.util.List;

public class Ex01 {
    @Test
    void test1() {
        SqlSession session = DBConn.getSession();
        List<Member> members = session.selectList("");
        members.forEach(System.out::println);mappers.MemberMapper.getList
    }

    @Test
    void test2() {
        SqlSession session = DBConn.getSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        List<Member> members = mapper.getList();
        members.forEach(System.out::println);
    }

    @Test
    void test3() {
        SqlSession session = DBConn.getSession();
        MemberMapper mapper = session.getMapper(MemberMapper.class);
        List<Member> members = mapper.getList2();
        members.forEach(System.out::println);
    }
}