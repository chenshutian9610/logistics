package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Car;
import web.beans.Corporation;
import web.beans.Member;
import web.service.CarService;
import web.service.CorporationService;
import web.service.MemberService;
import web.service.Page;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CarAction extends ActionSupport {
    static int current, page;
    Map session = ActionContext.getContext().getSession();
    Map request = (Map) ActionContext.getContext().get("request");
    int id;
    String index, real;
    @Resource(name = "carService")
    private CarService carService;
    @Resource(name = "memberService")
    private MemberService memberService;
    @Resource(name = "corporationService")
    private CorporationService corporationService;
    private Car c;
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

    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }

    public String entry() {
        if (request.get("index") == null) {
            current = 1;
            request.put("index", current);
        }
        Page<Car> pages = new Page<Car>();
        List<Car> objs = carService.select("");
        List<Car> result = pages.get(current, 2, objs);
        page = pages.page;
        if (page == 0)
            page = 1;
        request.put("page", page);
        request.put("result", result);
        return "car";
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
        Car car = carService.getById(id);
        member = memberService.getById(car.getMember().getId());
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
        request.put("car", car);
        request.put("readonly", "readonly");
        return "info";
    }

    public String publish() {
        String login = (String) session.get("login");
        if ("true".equals(login)) {
            if ("管理员模式".equals((String) session.get("message")) && real == null) {
                request.put("car", c);
                request.put("readonly", "");
                return "info";
            } else if ("管理员模式".equals((String) session.get("message"))) {
                request.put("car", c);
                request.put("readonly", "");
                if (c.getTitle().length() == 0 || c.getStart().length() == 0) {
                    request.put("message", "请输入标题与起始地点");
                    return "info";
                }
                Car car = carService.getById(c.getId());
                c.setDate(car.getDate());
                c.setMember(car.getMember());
                carService.update(c);
                request.put("message", "修改成功");
                return "info";
            }
            Car car = new Car();
            car.setStart("");
            car.setTitle("");
            car.setInfo("");
            request.put("car", car);
            request.put("readonly", "");
            if ("true".equals(real)) {
                if (c.getTitle().length() == 0 || c.getStart().length() == 0) {
                    request.put("message", "请输入标题与起始地点");
                    return "info";
                }
                c.setDate((new Date()).toString());
                member = (Member) session.get("member");
                if (member != null) {
                    c.setMember(member);
                    carService.insert(c);
                    request.put("message", "发布成功");
                } else {
                    request.put("message", "您未登录");
                }
                return entry();
            }
            return "info";
        } else {
            request.put("message", "登录之后才可以进行发布操作");
        }
        return entry();
    }

    public String delete() {
        carService.delete(carService.getById(id));
        request.put("message1", "删除成功");
        return entry();
    }
}
