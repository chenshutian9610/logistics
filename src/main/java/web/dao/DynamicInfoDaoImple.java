package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.DynamicInfo;

import javax.annotation.Resource;
import java.util.List;

@Repository("infoDao")
public class DynamicInfoDaoImple implements DynamicInfoDao {
    @Resource(name = "template")
    private HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public void insert(DynamicInfo info) {
        template.save(info);
    }

    @Override
    public void update(DynamicInfo info) {
        template.update(info);
    }

    @Override
    public List<DynamicInfo> select(String title) {
        return template.find("from DynamicInfo where title like '%" + title + "%'");
    }

    @Override
    public void delete(DynamicInfo info) {
        template.delete(info);
    }

    @Override
    public DynamicInfo getById(int id) {
        return template.get(DynamicInfo.class, id);
    }

}
