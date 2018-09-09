package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import web.beans.*;
import web.service.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginAction extends ActionSupport implements ModelDriven<Member> {
    Map request = (Map) ActionContext.getContext().get("request");
    Map session = ActionContext.getContext().getSession();
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "goodsService")
    private GoodsService goodsService;
    @Resource(name = "carService")
    private CarService carService;
    @Resource(name = "informService")
    private InformService informService;
    @Resource(name = "corporationService")
    private CorporationService corporationService;
    private Member member = new Member();
    //	parameters
    private String repwd, kind, condition;

    //	Override
    public Member getModel() {
        return member;
    }

    /*
     * 	getter && setter
     */
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRepwd() {
        return repwd;
    }

    public void setRepwd(String repwd) {
        this.repwd = repwd;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    /*
     * 	页面初始化
     */
    public String init() {
        List<Corporation> cors = corporationService.select("");
        List<Car> cars = carService.select("");
        List<Goods> goods = goodsService.select("");
        List<Inform> informs = informService.select("");
        if (cors == null)
            cors = new ArrayList<Corporation>();
        if (cars == null)
            cars = new ArrayList<Car>();
        if (goods == null)
            goods = new ArrayList<Goods>();
        if (informs == null)
            informs = new ArrayList<Inform>();
        request.put("cors", cors);
        request.put("cars", cars);
        request.put("goods", goods);
        request.put("informs", informs);
        return "start";
    }

    /*
     * 	member || manager login
     */
    public String login() {
        if (member.getName().length() == 0) {
            request.put("message", "请输入用户名");
            return init();
        }
        if (kind.equals("会员")) {
            List<Member> members = memberService.select(member.getName());
            for (Member find : members) {
                if (find.getPwd().equals(member.getPwd())) {
                    session.clear();
                    session.put("login", "true");
                    session.put("member", find);
                    session.put("id", find.getId());
                    session.put("name", member.getName());
                    session.put("message", "欢迎您，" + member.getName());
                    return init();
                }
            }
        } else if (member.getName().equals("2015") && member.getPwd().equals(("22"))) {
            session.clear();
            session.put("login", "true");
            session.put("name", "2015");
            session.put("message", "管理员模式");
            return init();
        }
        request.put("message", "登录失败");
        return init();
    }

    /*
     * 	button of 退出
     */
    public String logout() {
        session.clear();
        //return "login";
        return init();
    }

    /*
     * 	注册账号
     */
    public String register() {
        /*
         * 	没有 null 判断，应该是 modeldriven 的作用
         */
        if (member.getName().length() == 0) {
            request.put("message", "错误！请输入名字");
            return "register_error";
        }
        if (member.getEmail().length() == 0) {
            request.put("message", "错误！请输入邮箱");
            return "register_error";
        }
        if (member.getQuestion().length() == 0) {
            request.put("message", "错误！请输入问题");
            return "register_error";
        }
        if (member.getPwd().equals(repwd)) {
            memberService.insert(member);
            request.put("message", "注册成功");
            return init();
        } else {
            request.put("message", "错误！前后密码不一致");
        }
        return "register_error";
    }

    /*
     * 	找回密码
     */
    public String findPWD() {
        String name = member.getName();
        String email = member.getEmail();
        String question = member.getQuestion();
        String answer = member.getAnswer();
        if (name.length() == 0) {
            request.put("message", "错误！请输入用户名");
            return "find";
        }
        if (email.length() == 0) {
            request.put("message", "错误！请输入邮箱");
            return "find";
        }
        if (question.length() == 0) {
            request.put("message", "错误！请输入问题");
            return "find";
        }
        List<Member> members = memberService.select(name);
        for (Member find : members) {
            if (question.equals(find.getQuestion()) && answer.equals(find.getAnswer()) && email.equals(find.getEmail())) {
                String pwd = find.getPwd();
                String message = pwd.length() == 0 ? "空" : pwd;
                request.put("message", "您的密码为" + message);
                return "find";
            }
        }
        request.put("message", "错误！您输入的信息没被匹配到数据");
        return "find";
    }

    /*
     * 	search
     */
    public String search() {
        if (condition == null || condition.length() == 0) {
            request.put("search_message", "请输入条件");
            return init();
        }
        switch (kind) {
            case "用户名":
                Member member = memberService.getByName(condition);
                if (member == null) {
                    request.put("search_message", "没有匹配的数据");
                } else {
                    request.put("member", member);
                    request.put("occupy", "member");
                    List<Goods> goods = goodsService.getByMember(member);
                    List<Car> cars = carService.getByMember(member);
                    Corporation corporation = corporationService.getByMember(member);
                    if (corporation != null)
                        request.put("cor", corporation);
                    request.put("g_s", goods);
                    request.put("c_s", cars);
                }
                break;
            case "起始地":
                List<Goods> goods = goodsService.select("");
                List<Car> cars = carService.select("");
                List<Goods> g_s = new ArrayList<Goods>();
                List<Car> c_s = new ArrayList<Car>();
                for (Goods g : goods)
                    if (g.getStart().contains(condition))
                        g_s.add(g);
                request.put("g_s", g_s);
                for (Car c : cars)
                    if (c.getStart().contains(condition))
                        c_s.add(c);
                request.put("c_s", c_s);
                if (g_s.size() == 0 && c_s.size() == 0)
                    request.put("search_message", "没有匹配的数据");
                else
                    request.put("occupy", "start");
                break;
            case "公司名":
                List<Corporation> cors = corporationService.select(condition);
                if (cors == null || cors.size() == 0)
                    request.put("search_message", "没有匹配的数据");
                else {
                    request.put("cor_s", cors);
                    request.put("occupy", "cor");
                }
            default:
                break;
        }
        System.out.println(request.get("occupy"));
        return init();
    }
}
