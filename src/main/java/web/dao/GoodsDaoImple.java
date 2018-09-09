package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Goods;
import web.beans.Member;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("goodsDao")
public class GoodsDaoImple implements GoodsDao {
    @Resource(name = "template")
    HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public void insert(Goods goods) {
        template.save(goods);
    }

    @Override
    public void update(Goods goods) {
        template.update(goods);
    }

    @Override
    public List<Goods> select(String name) {
        List<Goods> goods = template.find("from Goods where name like '%" + name + "%'");
        return goods;
    }

    @Override
    public void delete(Goods goods) {
        template.delete(goods);
    }

    @Override
    public Goods getById(int id) {
        return template.get(Goods.class, id);
    }

    public List<Goods> getByMember(Member member) {
        List<Goods> all = template.find("from Goods");
        List<Goods> result = new ArrayList<Goods>();
        for (Goods g : all) {
            if (member.getId() == g.getMember().getId())
                result.add(g);
        }
        return result;
    }
}
