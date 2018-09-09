package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Member;

import javax.annotation.Resource;
import java.util.List;

@Repository("memberDao")
public class MemberDao {
    @Resource(name = "template")
    HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }


    public void insert(Member member) {
        template.save(member);
    }


    public void update(Member member) {
        template.update(member);
    }


    public List<Member> select(String name) {
        List<Member> members = template.find("from Member where name like '%" + name + "%'");
        return members;
    }


    public void delete(Member member) {
        template.delete(member);
    }


    public Member getById(int id) {
        return template.get(Member.class, id);
    }


    public Member getByName(String name) {
        List<Member> members = (List<Member>) template.find("from Member where name='" + name + "'");
        if (members == null || members.size() == 0)
            return null;
        return members.get(0);
    }
}
