package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Corporation;
import web.beans.Member;

import javax.annotation.Resource;
import java.util.List;

@Repository("corporationDao")
public class CorporationDao {
    @Resource(name = "template")
    HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }


    public void insert(Corporation corporation) {
        template.save(corporation);
    }


    public void update(Corporation corporation) {
        template.update(corporation);
    }


    public List<Corporation> select(String name) {
        List<Corporation> corporations = template.find("from Corporation where name like '%" + name + "%'");
        return corporations;
    }


    public void delete(Corporation corporation) {
        template.delete(corporation);
    }


    public Corporation getById(int id) {
        return template.get(Corporation.class, id);
    }


    public Corporation getByMember(Member member) {
        List<Corporation> all = template.find("from Corporation");
        Corporation corporation = new Corporation();
        for (Corporation cor : all) {
            if (cor.getMember().getId() == member.getId())
                corporation = cor;
        }
        return corporation;
    }

}
