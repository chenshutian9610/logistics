package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import web.dao.BaseDao;
import web.dao.MemberDao;
import web.domain.BaseDomain;
import web.domain.Member;
import web.tool.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public abstract class BaseAction<T extends BaseDomain> extends ActionSupport implements ModelDriven<T> {
    @Autowired
    protected MemberDao memberDao;

    protected Map session=ActionContext.getContext().getSession();
    protected HttpServletRequest request=ServletActionContext.getRequest();
    protected Object login=session.get("member");

    protected T model;

    @Override
    public T getModel() {
        return model;
    }

    public int getId(){
        return Integer.parseInt(request.getParameter("id"));
    }

    /* 页面初始化 */
    public String entry(BaseDao<T>dao){
        List<T> object=dao.selectAll();
        request.setAttribute("result",object);
        return "entry";
    }

    /* 详情 */
    public String info(BaseDao<T>dao) {
        int id=getId();
        T result = dao.selectById(id);
        Member member=dao.selectMemberByOtherId(id);
        request.setAttribute("result",result);
        request.setAttribute("object",member);
        request.setAttribute("readonly", "readonly");
        String holder=null;
        if(login!=null){
            holder=member.getId()==((Member)login).getId()?"true":"";
            request.setAttribute("holder",holder);
        }
        return "info";
    }

    /* 发布与修改 */
    public String publish(BaseDao<T>dao){
        model.setDate(DateUtils.getDate());
        model.setMember(memberDao.selectByName(((Member)login).getName()));
        String id=request.getParameter("num");
        if(id!=null&&id.length()!=0){
            model.setId(Integer.parseInt(id));
            dao.update(model);
            return entry(dao);
        }
        dao.insert(model);
        request.setAttribute("message","发布成功");
        return entry(dao);
    }

    /* 删除 */
    public String delete(BaseDao<T>dao){
        dao.deleteById(getId());
        return entry(dao);
    }
}
