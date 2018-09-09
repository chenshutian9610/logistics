package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Corporation;
import web.beans.DynamicInfo;
import web.beans.Member;
import web.service.CorporationService;
import web.service.DynamicInfoService;
import web.service.MemberService;
import web.service.Page;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DynamicInfoAction extends ActionSupport {
    static int current, page;
    @Resource(name = "infoService")
    DynamicInfoService infoService;
    Map session = ActionContext.getContext().getSession();
    Map request = (Map) ActionContext.getContext().get("request");
    //	parameters
    int id;
    String index, real;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "corporationService")
    private CorporationService corporationService;
    private Member member;
    private DynamicInfo d;

    /*
     * getter && setter
     */
    public DynamicInfo getD() {
        return d;
    }

    public void setD(DynamicInfo d) {
        this.d = d;
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
        Page<DynamicInfo> pages = new Page<DynamicInfo>();
        List<DynamicInfo> objs = infoService.select("");
        List<DynamicInfo> result = pages.get(current, 2, objs);
        page = pages.page == 0 ? 1 : pages.page;                //
        request.put("page", page);
        request.put("result", result);
        return "dynamicInfo";
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
        DynamicInfo dynamicInfo = infoService.getById(id);
        member = memberService.getById(dynamicInfo.getMember().getId());
        String corporation = "";
        int phone = 0;
        if (member.getCorporation() == null) {
            phone = member.getPhone();
            corporation = "个人";
        } else {
            Corporation cor = corporationService.getById(member.getCorporation().getId());
            phone = cor.getPhone();
            corporation = cor.getName();
        }
        request.put("phone", phone);
        request.put("by", corporation);
        request.put("member", member);
        request.put("dynamicInfo", dynamicInfo);
        request.put("readonly", "readonly");
        return "info";
    }

    public String publish() {
        String login = (String) session.get("login");
        if ("true".equals(login)) {
            /*
             * 	管理员模式-修改
             */
            if ("管理员模式".equals((String) session.get("message")) && real == null) {
                request.put("dynamicInfo", d);
                request.put("readonly", "");
                System.out.println("q");
                return "info";
            } else if ("管理员模式".equals((String) session.get("message"))) {
                System.out.println("w");
                request.put("dynamicInfo", d);
                request.put("readonly", "");
                System.out.println("e");
                if (d.getTitle().length() == 0 || d.getInfo().length() == 0) {
                    request.put("message", "请输入标题与内容");
                    return "info";
                }
                System.out.println("f");
                DynamicInfo dynamicInfo = infoService.getById(d.getId());
                d.setDate(dynamicInfo.getDate());
                d.setMember(dynamicInfo.getMember());
                infoService.update(d);
                request.put("message", "修改成功");
                return "info";
            }

            DynamicInfo dynamicInfo = new DynamicInfo();
            dynamicInfo.setTitle("");
            dynamicInfo.setInfo("");
            request.put("dynamicInfo", dynamicInfo);
            request.put("readonly", "");
            if ("true".equals(real)) {

                if (d.getTitle().length() == 0 || d.getInfo().length() == 0) {
                    request.put("message", "请输入标题与内容");
                    return "info";
                }
                d.setDate((new Date()).toString());
                member = (Member) session.get("member");
                if (member != null) {
                    d.setMember(member);
                    infoService.insert(d);
                    request.put("message", "发布成功");
                } else {
                    request.put("message", "您未登录");
                }
                request.put("index", 1);
                return entry();
            }
            return "info";
        } else {
            request.put("message", "登录之后才可以进行发布操作");
        }
        request.put("index", current);
        return entry();
    }

    /*
     * 	管理员模式-删除
     */
    public String delete() {
        infoService.delete(infoService.getById(id));
        request.put("message1", "删除成功");
        return entry();
    }

}
