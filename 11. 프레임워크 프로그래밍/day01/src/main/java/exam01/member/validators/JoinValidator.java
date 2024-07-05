package exam01.member.validators;

import exam01.global.validators.Validator;
import exam01.member.controllers.RequestJoin;
import exam01.member.dao.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JoinValidator implements Validator<RequestJoin> {

    @Autowired
    //@Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    //@Qualifier("memberDao")
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void check(RequestJoin form) {

    }
}