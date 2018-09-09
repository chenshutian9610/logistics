package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Member;

import javax.annotation.Resource;
import java.util.List;

@Repository("memberDao")
public class MemberDaoImple implements MemberDao {
    @Resource(name = "template")
    HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public void insert(Member member) {
        template.save(member);
    }

    @Override
    public void update(Member member) {
        template.update(member);
    }

    @Override
    public List<Member> select(String name) {
        List<Member> members = template.find("from Member where name like '%" + name + "%'");
        return members;
    }

    @Override
    public void delete(Member member) {
        template.delete(member);
    }

    @Override
    public Member getById(int id) {
        return template.get(Member.class, id);
    }

    @Override
    public Member getByName(String name) {
        List<Member> members = (List<Member>) template.find("from Member where name='" + name + "'");
        if (members == null || members.size() == 0)
            return null;
        return members.get(0);
    }
}
