package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.InformDao;
import web.domain.Inform;
import web.tool.DateUtils;

public class InformAction extends BaseAction<Inform> {
    @Autowired
    protected InformDao informDao;

    public InformAction(){
        super();
        model=new Inform();
    }

    public String entry(){
        request.setAttribute("inform",true);
        return entry(informDao);
    }

    public String info() {
        int id=getId();
        Inform result = informDao.selectById(id);
        request.setAttribute("result",result);
        request.setAttribute("readonly", "readonly");
        request.setAttribute("inform",true);
        return "info";
    }

    public String publish(){
        model.setDate(DateUtils.getDate());
        String id=request.getParameter("num");
        if(id!=null&&id.length()!=0){
            model.setId(Integer.parseInt(id));
            informDao.update(model);
            return entry();
        }
        informDao.insert(model);
        request.setAttribute("message","发布成功");
        return entry();
    }

    public String delete(){
        return delete(informDao);
    }
}
