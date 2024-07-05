package exam01.member.dao;

import exam01.member.entities.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MemberDao { // memberDao - 빈 이름
    //db에 저장(근데 db연동 안해서 저장한다 가정하고 작성)
    private static Map<String, Member> members = new HashMap<String, Member>();

    public void register(Member member) {
        members.put(member.getEmail(), member);
    }

    public Member get(String email) {
        return members.get(email);
    }

    public List<Member> getList() {
        List<Member> data = new ArrayList<>(members.values());
        return data;
    }
}
