package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Corporation;
import web.beans.Goods;
import web.beans.Member;
import web.service.CorporationService;
import web.service.GoodsService;
import web.service.MemberService;
import web.service.Page;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GoodsAction extends ActionSupport {
    static int current, page;
    Map session = ActionContext.getContext().getSession();
    Map request = (Map) ActionContext.getContext().get("request");
    //	parameters
    int id;
    String index, real;
    @Resource(name = "goodsService")
    private GoodsService goodsService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "corporationService")
    private CorporationService corporationService;
    private Goods g;
    private Member member;

    /*
     * getter && setter
     */
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

    //	object DI
    public Goods getG() {
        return g;
    }

    public void setG(Goods g) {
        this.g = g;
    }

    /*
     * 	goods.jsp initial
     */
    public String entry() {
        if (request.get("index") == null) {
            current = 1;
            request.put("index", 1);
        }
        Page<Goods> pages = new Page<Goods>();
        List<Goods> objs = goodsService.select("");
        List<Goods> result = pages.get(current, 2, objs);
        page = pages.page == 0 ? 1 : pages.page;                //
        request.put("page", page);
        request.put("result", result);
        return "goods";
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

    /*
     * 	goods-info.jsp initial
     */
    public String info() {
        Goods goods = goodsService.getById(id);
        member = memberService.getById(goods.getMember().getId());
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
        request.put("goods", goods);
        request.put("readonly", "readonly");
        return "info";
    }

    /*
     * 	publish goods || show goods' info
     */
    public String publish() {
        String login = (String) session.get("login");
        if ("true".equals(login)) {
            /*
             * 	管理员模式-修改
             */
            if ("管理员模式".equals((String) session.get("message")) && real == null) {
                request.put("goods", g);
                request.put("readonly", "");
                return "info";
            } else if ("管理员模式".equals((String) session.get("message"))) {
                request.put("goods", g);
                request.put("readonly", "");
                if (g.getName().length() == 0 || g.getType().length() == 0) {
                    request.put("message", "请输入名称与类型");
                    return "info";
                }
                Goods goods = goodsService.getById(g.getId());
                g.setDate(goods.getDate());
                g.setMember(goods.getMember());
                goodsService.update(g);
                request.put("message", "修改成功");
                return "info";
            }
            /*
             * goods.jsp's publish
             */
            Goods goods = new Goods();
            goods.setName("");
            goods.setStart("");
            goods.setEnd("");
            goods.setType("");
            goods.setInfo("");
            request.put("goods", goods);
            request.put("readonly", "");
            if ("true".equals(real)) {
                /*
                 * 	goods-info.jsp's publish
                 */
                if (g.getName().length() == 0 || g.getType().length() == 0) {
                    request.put("message", "请输入名称与类型");
                    return "info";
                }
                g.setDate((new Date()).toString());
                member = (Member) session.get("member");
                if (member != null) {
                    g.setMember(member);
                    goodsService.insert(g);
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
        goodsService.delete(goodsService.getById(id));
        request.put("message1", "删除成功");
        return entry();
    }
}
