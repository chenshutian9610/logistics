package web.dao;

import web.beans.Member;

import java.util.List;

public interface MemberDao {
    public void insert(Member member);

    public void update(Member member);

    public List<Member> select(String name);

    public void delete(Member member);

    public Member getById(int id);

    public Member getByName(String name);
}
