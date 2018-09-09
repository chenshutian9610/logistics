package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.KnowledgeDao;
import web.domain.Knowledge;

public class KnowledgeAction extends BaseAction<Knowledge> {
    @Autowired
    private KnowledgeDao knowledgeDao;

    public KnowledgeAction(){
        super();
        model=new Knowledge();
    }

    public String entry(){
        return entry(knowledgeDao);
    }

    public String info() {
        return info(knowledgeDao);
    }

    public String publish(){
        return publish(knowledgeDao);
    }

    public String delete(){
        return delete(knowledgeDao);
    }
}
