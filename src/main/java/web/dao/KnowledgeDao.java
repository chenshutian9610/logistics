package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Knowledge;

import javax.annotation.Resource;
import java.util.List;

@Repository("knowledgeDao")
public class KnowledgeDao {
    @Resource(name = "template")
    private HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }


    public void insert(Knowledge knowledge) {
        template.save(knowledge);
    }


    public void update(Knowledge knowledge) {
        template.update(knowledge);
    }


    public List<Knowledge> select(String title) {
        return template.find("from Knowledge where title like '%" + title + "%'");
    }


    public void delete(Knowledge knowledge) {
        template.delete(knowledge);
    }


    public Knowledge getById(int id) {
        return template.get(Knowledge.class, id);
    }

}
