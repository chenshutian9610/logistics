package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Member;
import web.mapper.MemberMapper;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private StartController controller;

    @RequestMapping("/login")
    public String login(Member member, String identity, ModelMap map, HttpSession session) {
        String name = member.getName();
        String pwd = member.getPwd();
        if ("管理员".equals(identity)) {
            if ("2015".equals(name) && "22".equals(pwd)) {
                session.setAttribute("message", "管理员模式");
                return controller.init(map);
            } else {
                map.put("message", "用户与密码不匹配");
                session.invalidate();
                return controller.init(map);
            }
        }
        Member result=memberMapper.getMemberByName(name);
        if (result==null||!pwd.equals(result.getPwd())) {
            map.put("message", "用户与密码不匹配");
            session.invalidate();
            return controller.init(map);
        }
        session.setAttribute("message", "欢迎您，" + name);
        session.setAttribute("memId",result.getId());
        return controller.init(map);
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, ModelMap map) {
        session.invalidate();
        return controller.init(map);
    }

    @RequestMapping("/registering")
    public String register(Member member, ModelMap map) {
        try {
            memberMapper.insertSelective(member);
        } catch (DuplicateKeyException exception) {
            map.put("message", "此用户名已被注册");
            return "register";
        }
        map.put("message", "注册成功");
        return "register";
    }

    @RequestMapping("/findPwd")
    public String findPwd(Member member, ModelMap map) {
        Member result = memberMapper.getByName(member.getName());
        String pwd = null;
        if (result != null
                && member.getEmail().equals(result.getEmail())
                && member.getQuestion().equals(result.getQuestion())
                && member.getAnswer().equals(result.getAnswer())) {
            pwd = result.getPwd();
            pwd = (pwd == null || pwd.equals("")) ? "空" : "'" + pwd + "'";
            map.put("message", "您的密码为" + pwd);
            return "findPWD";
        }
        map.put("message", "找不到匹配的数据");
        return "findPWD";
    }
}
