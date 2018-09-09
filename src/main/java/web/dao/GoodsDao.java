package web.dao;

import web.beans.Goods;
import web.beans.Member;

import java.util.List;

public interface GoodsDao {
    public void insert(Goods goods);

    public void update(Goods goods);

    public List<Goods> select(String name);

    public void delete(Goods goods);

    public Goods getById(int id);

    public List<Goods> getByMember(Member member);
}
