package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Inform;

import javax.annotation.Resource;
import java.util.List;

@Repository("informDao")
public class InformDao {
    @Resource(name = "template")
    private HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }


    public void insert(Inform inform) {
        template.save(inform);
    }


    public void update(Inform inform) {
        template.update(inform);
    }


    public List<Inform> select(String title) {
        return template.find("from Inform where title like '%" + title + "%'");
    }


    public void delete(Inform inform) {
        template.delete(inform);
    }


    public Inform getById(int id) {
        return template.get(Inform.class, id);
    }

}
