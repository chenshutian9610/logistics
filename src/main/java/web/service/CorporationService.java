package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.Corporation;
import web.beans.Member;
import web.dao.CorporationDao;

import javax.annotation.Resource;
import java.util.List;

@Service("corporationService")
@Transactional
public class CorporationService {
    @Resource(name = "corporationDao")
    CorporationDao corporationDao;

    public CorporationDao getCorporationDao() {
        return corporationDao;
    }

    public void setCorporationDao(CorporationDao corporationDao) {
        this.corporationDao = corporationDao;
    }

    public void insert(Corporation corporation) {
        corporationDao.insert(corporation);
    }

    public void update(Corporation corporation) {
        corporationDao.update(corporation);
    }

    public List<Corporation> select(String name) {
        return corporationDao.select(name);
    }

    public void delete(Corporation corporation) {
        corporationDao.delete(corporation);
    }

    public Corporation getById(int id) {
        return corporationDao.getById(id);
    }

    public Corporation getByMember(Member member) {
        return corporationDao.getByMember(member);
    }
}
