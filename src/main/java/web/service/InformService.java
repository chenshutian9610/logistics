package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.Inform;
import web.dao.InformDao;

import javax.annotation.Resource;
import java.util.List;

@Service("informService")
@Transactional
public class InformService {
    @Resource(name = "informDao")
    private InformDao informDao;

    public InformDao getInformDao() {
        return informDao;
    }

    public void setInformDao(InformDao informDao) {
        this.informDao = informDao;
    }

    public void insert(Inform inform) {
        informDao.insert(inform);
    }

    public void update(Inform inform) {
        informDao.update(inform);
    }

    public List<Inform> select(String title) {
        return informDao.select(title);
    }

    public void delete(Inform inform) {
        informDao.delete(inform);
    }

    public Inform getById(int id) {
        return informDao.getById(id);
    }
}
