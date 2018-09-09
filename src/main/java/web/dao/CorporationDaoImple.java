package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Corporation;
import web.beans.Member;

import javax.annotation.Resource;
import java.util.List;

@Repository("corporationDao")
public class CorporationDaoImple implements CorporationDao {
    @Resource(name = "template")
    HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public void insert(Corporation corporation) {
        template.save(corporation);
    }

    @Override
    public void update(Corporation corporation) {
        template.update(corporation);
    }


    @Override
    public List<Corporation> select(String name) {
        List<Corporation> corporations = template.find("from Corporation where name like '%" + name + "%'");
        return corporations;
    }

    @Override
    public void delete(Corporation corporation) {
        template.delete(corporation);
    }

    @Override
    public Corporation getById(int id) {
        return template.get(Corporation.class, id);
    }

    @Override
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
