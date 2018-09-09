package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.Member;
import web.dao.MemberDao;

import javax.annotation.Resource;
import java.util.List;

@Service("memberService")
@Transactional
public class MemberService {
    @Resource(name = "memberDao")
    MemberDao memberDao;

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void insert(Member member) {
        memberDao.insert(member);
    }

    public void update(Member member) {
        memberDao.update(member);
    }

    public List<Member> select(String name) {
        return memberDao.select(name);
    }

    public void delete(Member member) {
        memberDao.delete(member);
    }

    public Member getById(int id) {
        return memberDao.getById(id);
    }

    public Member getByName(String name) {
        return (Member) memberDao.getByName(name);
    }
}
