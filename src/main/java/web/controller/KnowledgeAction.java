package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Corporation;
import web.beans.Knowledge;
import web.beans.Member;
import web.service.CorporationService;
import web.service.KnowledgeService;
import web.service.MemberService;
import web.service.Page;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class KnowledgeAction extends ActionSupport {
    static int current, page;
    @Resource(name = "knowledgeService")
    KnowledgeService knowledgeService;
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
    private Knowledge k;

    /*
     * getter && setter
     */
    public Knowledge getK() {
        return k;
    }

    public void setK(Knowledge k) {
        this.k = k;
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
        Page<Knowledge> pages = new Page<Knowledge>();
        List<Knowledge> objs = knowledgeService.select("");
        List<Knowledge> result = pages.get(current, 2, objs);
        page = pages.page == 0 ? 1 : pages.page;                //
        request.put("page", page);
        request.put("result", result);
        return "knowledge";
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
        Knowledge knowledge = knowledgeService.getById(id);
        member = memberService.getById(knowledge.getMember().getId());
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
        request.put("knowledge", knowledge);
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
                request.put("knowledge", k);
                request.put("readonly", "");
                System.out.println("q");
                return "info";
            } else if ("管理员模式".equals((String) session.get("message"))) {
                System.out.println("w");
                request.put("knowledge", k);
                request.put("readonly", "");
                System.out.println("e");
                if (k.getTitle().length() == 0 || k.getInfo().length() == 0) {
                    request.put("message", "请输入标题与内容");
                    return "info";
                }
                System.out.println("f");
                Knowledge knowledge = knowledgeService.getById(k.getId());
                k.setDate(knowledge.getDate());
                k.setMember(knowledge.getMember());
                knowledgeService.update(k);
                request.put("message", "修改成功");
                return "info";
            }

            Knowledge knowledge = new Knowledge();
            knowledge.setTitle("");
            knowledge.setInfo("");
            request.put("knowledge", knowledge);
            request.put("readonly", "");
            if ("true".equals(real)) {

                if (k.getTitle().length() == 0 || k.getInfo().length() == 0) {
                    request.put("message", "请输入标题与内容");
                    return "info";
                }
                k.setDate((new Date()).toString());
                member = (Member) session.get("member");
                if (member != null) {
                    k.setMember(member);
                    knowledgeService.insert(k);
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
        knowledgeService.delete(knowledgeService.getById(id));
        request.put("message1", "删除成功");
        return entry();
    }

}
