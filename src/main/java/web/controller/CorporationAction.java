package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.CorporationDao;
import web.domain.Corporation;
import web.domain.Member;

public class CorporationAction extends BaseAction<Corporation>{
    @Autowired
    private CorporationDao corporationDao;

    public CorporationAction(){
        super();
        model=new Corporation();
    }

    public String entry(){
        return entry(corporationDao);
    }

    public String info() {
        return info(corporationDao);
    }

    public String publish(){
        if(memberDao.selectById(((Member)login).getId()).getCorporation()!=null){
            request.setAttribute("message","对不起，您已经注册过公司了");
            return entry();
        }
        return publish(corporationDao);
    }

    public String delete(){
        return delete(corporationDao);
    }
}
