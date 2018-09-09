package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.Knowledge;
import web.dao.KnowledgeDao;

import javax.annotation.Resource;
import java.util.List;

@Service("knowledgeService")
@Transactional
public class KnowledgeService {
    @Resource(name = "knowledgeDao")
    KnowledgeDao knowledgeDao;

    public KnowledgeDao getKnowledgeDao() {
        return knowledgeDao;
    }

    public void setKnowledgeDao(KnowledgeDao knowledgeDao) {
        this.knowledgeDao = knowledgeDao;
    }

    public void insert(Knowledge knowledge) {
        knowledgeDao.insert(knowledge);
    }

    public void update(Knowledge knowledge) {
        knowledgeDao.update(knowledge);
    }

    public List<Knowledge> select(String title) {
        return knowledgeDao.select(title);
    }

    public void delete(Knowledge knowledge) {
        knowledgeDao.delete(knowledge);
    }

    public Knowledge getById(int id) {
        return knowledgeDao.getById(id);
    }
}
