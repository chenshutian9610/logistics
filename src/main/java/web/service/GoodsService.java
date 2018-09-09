package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.Goods;
import web.beans.Member;
import web.dao.GoodsDao;

import javax.annotation.Resource;
import java.util.List;

@Service("goodsService")
@Transactional
public class GoodsService {
    @Resource(name = "goodsDao")
    private GoodsDao goodsDao;

    public GoodsDao getGoodsDao() {
        return goodsDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void insert(Goods goods) {
        goodsDao.insert(goods);
    }

    public void update(Goods goods) {
        goodsDao.update(goods);
    }

    public List<Goods> select(String name) {
        return goodsDao.select(name);
    }

    public void delete(Goods goods) {
        goodsDao.delete(goods);
    }

    public Goods getById(int id) {
        return goodsDao.getById(id);
    }

    public List<Goods> getByMember(Member member) {
        return goodsDao.getByMember(member);
    }
}
