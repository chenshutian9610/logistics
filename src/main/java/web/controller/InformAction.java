package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Inform;
import web.service.InformService;
import web.service.Page;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InformAction extends ActionSupport {
    static int current, page;
    Map session = ActionContext.getContext().getSession();
    Map request = (Map) ActionContext.getContext().get("request");
    //	parameters
    int id;
    String index, real;
    @Resource(name = "informService")
    private InformService informService;
    private Inform i;

    /*
     * getter && setter
     */
    public Inform getI() {
        return i;
    }

    public void setI(Inform i) {
        this.i = i;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReal() {
        return real;
    }

    public void setReal(String real) {
        this.real = real;
    }

    public String entry() {
        if (request.get("index") == null) {
            current = 1;
            request.put("index", 1);
        }
        Page<Inform> pages = new Page<Inform>();
        List<Inform> objs = informService.select("");
        List<Inform> result = pages.get(current, 2, objs);
        page = pages.page == 0 ? 1 : pages.page;                //
        request.put("page", page);
        request.put("result", result);
        return "inform";
    }

    /*
     * 	navigator
     */
    public String index() {
        switch (index) {
            case "1":
                current = 1;
                break;
            case "0":
                current = page;
                break;
            case "<":
                current = current == 1 ? 1 : current - 1;
                break;
            case ">":
                current = current == page ? page : current + 1;
                break;
        }
        request.put("index", current);
        return entry();
    }

    public String info() {
        Inform inform = informService.getById(id);
        request.put("inform", inform);
        request.put("readonly", "readonly");
        return "info";
    }

    public String publish() {
        String login = (String) session.get("login");
        /*
         * 	管理员模式-修改
         */
        if ("update".equals(real)) {
            request.put("inform", i);
            request.put("readonly", "");
            request.put("real", "?real=updateNow");
            System.out.println("q");
            return "info";
        } else if ("updateNow".equals(real)) {
            System.out.println("w");
            request.put("inform", i);
            request.put("readonly", "");
            System.out.println("e");
            if (i.getTitle().length() == 0 || i.getInfo().length() == 0) {
                request.put("real", "?real=updateNow");
                request.put("message", "请输入标题与内容");
                return "info";
            }
            System.out.println("f");
            Inform inform = informService.getById(i.getId());
            i.setDate(inform.getDate());
            informService.update(i);
            request.put("message", "修改成功");
            request.put("readonly", "readonly");
            return "info";
        } else {
            Inform inform = new Inform();
            inform.setTitle("");
            inform.setInfo("");
            request.put("inform", inform);
            request.put("readonly", "");
            if ("insert".equals(real)) {
                request.put("real", "?real=insertNow");
                return "info";
            } else if ("insertNow".equals(real)) {
                if (i.getTitle().length() == 0 || i.getInfo().length() == 0) {
                    request.put("message", "请输入标题与内容");
                    request.put("real", "?real=insertNow");
                    return "info";
                }
                i.setDate((new Date()).toString());
                informService.insert(i);
                request.put("message", "发布成功");
                return entry();
            }
            request.put("message", real);
            return entry();
        }
    }

    /*
     * 	管理员模式-删除
     */
    public String delete() {
        informService.delete(informService.getById(id));
        request.put("message1", "删除成功");
        return entry();
    }
}
