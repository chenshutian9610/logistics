package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import web.dao.*;
import web.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class StartAction extends ActionSupport implements ModelDriven<Member> {

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private CorporationDao corporationDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private CarDao carDao;
    @Autowired
    private InformDao informDao;

    private HttpServletRequest request = ServletActionContext.getRequest();
    private Map session = ActionContext.getContext().getSession();

    private Member member = new Member();

    @Override
    public Member getModel() {
        return member;
    }

    /* 页面初始化 */
    public String init() {
        List<Corporation> corporations = corporationDao.selectAll(5);
        List<Inform> informs = informDao.selectAll(5);
        request.setAttribute("corporations", corporations);
        request.setAttribute("informs", informs);
        if (request.getAttribute("show")!=null){
            System.out.println(" init init init init ");
            return "init";
        }
        List<Car> cars = carDao.selectAll(5);
        List<Goods> goods = goodsDao.selectAll(5);
        request.setAttribute("cars", cars);
        request.setAttribute("goods", goods);
        return "init";
    }

    /* 登陆 */
    public String login() {
        String identity = request.getParameter("identity");
        if ("会员".equals(identity)) {
            Member result = memberDao.selectByName(member.getName());
            if (result != null) {
                if (member.getPwd().equals(result.getPwd())) {
                    session.clear();
                    session.put("login", "true");
                    session.put("member", result);
                    session.put("message", "欢迎您，" + result.getName());
                    return init();
                }
            }
        } else if ("2015".equals(member.getName()) && "22".equals(member.getPwd())) {
            session.clear();
            session.put("login", "true");
            session.put("message", "管理员模式");
            return init();
        }
        request.setAttribute("message", "请输入正确的用户名与密码");
        session.clear();
        return init();
    }

    /* 退出 */
    public String logout() {
        session.clear();
        return init();
    }

    /* 注册 */
    public String register() {
        String repwd = ServletActionContext.getRequest().getParameter("repwd");
        if (member.getPwd().equals(repwd)) {
            try {
                memberDao.insert(member);
            } catch (DataIntegrityViolationException e) {
                request.setAttribute("message", "该用户名已被注册");
                return "register";
            }
            request.setAttribute("message", "注册成功，请返回主页登陆");
            return "register";
        } else request.setAttribute("message", "错误！前后密码不一致");
        return "register";
    }

    /* 找回密码 */
    public String findPWD() {
        String name = member.getName();
        String email = member.getEmail();
        String question = member.getQuestion();
        String answer = member.getAnswer();
        Member result = memberDao.selectByName(name);
        if(result==null){
            request.setAttribute("message", "不存在此用户");
            return "findPWD";
        }
        if (question.equals(result.getQuestion()) && answer.equals(result.getAnswer()) && email.equals(result.getEmail())) {
            String pwd = result.getPwd();
            String message = pwd.length() == 0 ? "空" : "'" + pwd + "'";
            request.setAttribute("message", "您的密码为" + message);
            return "findPWD";
        }
        request.setAttribute("message", "错误！您输入的信息没被匹配到数据");
        return "findPWD";
    }

    /* 搜索 */
    public String search() {
        String condition = request.getParameter("condition");
        switch (request.getParameter("status")) {
            case "用户名":
                Member result = memberDao.selectByName(condition);
                if (result == null) {
                    request.setAttribute("search_message", "没有匹配的数据");
                } else {
                    List<Goods> goods = goodsDao.selectByMemberName(condition);
                    List<Car> cars = carDao.selectByMemberName(condition);
                    Corporation corporation = corporationDao.selectByMemberName(condition);
                    request.setAttribute("member", result);
                    request.setAttribute("show", "member");
                    request.setAttribute("cor", corporation);
                    request.setAttribute("goods", goods);
                    request.setAttribute("cars", cars);
                }
                break;
            case "起始地":
                List<Goods> goods = goodsDao.selectByStart(condition);
                List<Car> cars = carDao.selectByStart(condition);
                if (goods.size() == 0 && cars.size() == 0) {
                    request.setAttribute("search_message", "没有匹配的数据");
                    return init();
                }
                request.setAttribute("show", "start");
                request.setAttribute("goods", goods);
                request.setAttribute("cars", cars);
                break;
            case "公司名":
                List<Corporation> cors = corporationDao.selectByVagueName(condition);
                if (cors.size() == 0) {
                    request.setAttribute("search_message", "没有匹配的数据");
                    return init();
                }
                request.setAttribute("show", "corporation");
                request.setAttribute("cors", cors);
                break;
        }
        return init();
    }
}
