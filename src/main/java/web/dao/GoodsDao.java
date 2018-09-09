package web.dao;

import org.springframework.stereotype.Repository;
import web.domain.Goods;

import java.util.List;

@Repository
public class GoodsDao extends BaseDao<Goods> {
    public List<Goods> selectByMemberName(String name) {
        return (List<Goods>) session.find("from Goods g where g.member.name=?", name);
    }

    public List<Goods> selectByStart(String start) {
        return (List<Goods>) session.find("from Goods g where g.start like '%" + start + "%'");
    }
}
