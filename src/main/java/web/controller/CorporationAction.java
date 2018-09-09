package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Corporation;
import web.beans.Member;
import web.service.CorporationService;
import web.service.MemberService;
import web.service.Page;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class CorporationAction extends ActionSupport {
    static int current, page;
    Map session = ActionContext.getContext().getSession();
    Map request = (Map) ActionContext.getContext().get("request");
    int id;
    String index, real;
    @Resource(name = "corporationService")
    private CorporationService corporationService;
    @Resource(name = "memberService")
    private MemberService memberService;
    private Corporation cor;
    private Member member;

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

    public Corporation getCor() {
        return cor;
    }

    public void setCor(Corporation cor) {
        this.cor = cor;
    }

    public String entry() {
        if (request.get("index") == null) {
            current = 1;
            request.put("index", 1);
        }
        Page<Corporation> pages = new Page<Corporation>();
        List<Corporation> objs = corporationService.select("");
        List<Corporation> result = pages.get(current, 2, objs);
        page = pages.page;
        if (page == 0)
            page = 1;
        request.put("page", page);
        request.put("result", result);
        return "corporation";
    }

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
        Corporation corporation = corporationService.getById(id);
        System.out.println(corporation.getMember() == null);
        member = memberService.getById(corporation.getMember().getId());
        request.put("corporation", corporation);
        request.put("member", member);
        request.put("readonly", "readonly");
        return "info";
    }

    public String publish() {
        String login = (String) session.get("login");
        if ("true".equals(login)) {
            if ("管理员模式".equals((String) session.get("message")) && real == null) {
                if (cor.getLink() == null)
                    cor.setLink("");
                request.put("corporation", cor);
                request.put("readonly", "");
                System.out.println("a");
                return "info";
            } else if ("管理员模式".equals((String) session.get("message"))) {
                System.out.println("b");
                if (cor.getLink() == null)
                    cor.setLink("");
                request.put("corporation", cor);
                request.put("readonly", "");
                if (cor.getName().length() == 0) {
                    request.put("message", "请输入公司名称");
                    return "info";
                }
                System.out.println(cor.getMember() == null);
                System.out.println(cor.getLink() == null);
                corporationService.update(cor);
                System.out.println(corporationService.getById(cor.getId()).getMember() == null);
                request.put("message", "修改成功");
                return "info";
            }
            member = memberService.getById((int) session.get("id"));
            if (member.getCorporation() != null) {
                request.put("message", "一个用户只能注册一个公司");
                return entry();
            }
            Corporation corporation = new Corporation();
            corporation.setName("");
            corporation.setAddress("");
            corporation.setInfo("");
            corporation.setLink("");
            request.put("corporation", corporation);
            request.put("readonly", "");
            if ("true".equals(real)) {
                if (cor.getName().length() == 0) {
                    request.put("message", "请输入公司名称");
                    return "info";
                }
                member.setCorporation(cor);
                memberService.update(member);
                cor.setMember(member);
                corporationService.update(cor);
                request.put("message", "发布成功");
                return entry();
            }
            return "info";
        } else {
            request.put("message", "登录之后才可以进行发布操作");
        }
        return entry();
    }

    public String delete() {
        Corporation corporation = corporationService.getById(id);
        member = memberService.getById(corporation.getMember().getId());
        id = member.getCorporation().getId();
        member.setCorporation(null);
        memberService.update(member);
        corporationService.delete(corporationService.getById(id));
        request.put("message1", "删除成功");
        return entry();
    }
}
