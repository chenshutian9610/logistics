package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import web.domain.BaseDomain;
import web.domain.Corporation;
import web.domain.Example;
import web.domain.Member;
import web.mapper.CustomMapper;
import web.mapper.MemberMapper;
import web.tool.DateUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
public class BaseController<T extends BaseDomain> {
    @Autowired private MemberMapper memberMapper;
    @Autowired private HttpSession session;

    public void entry(CustomMapper<T> mapper, Example example, ModelMap map){
        List<T> result=mapper.selectByExample(example);
        map.put("result",result);
    }

    public String info(String id, CustomMapper<T>mapper, ModelMap map){
        try{
            T result=mapper.selectByPrimaryKey(Integer.parseInt(id));
            map.put("result",result);
            map.put("readonly","readonly");
            if(map.get("inform")!=null)
                return "-info";
            Member member=memberMapper.selectByPrimaryKey(result.getMemId());
            map.put("object",member);
            if(member.getId()==session.getAttribute("memId"))
                map.put("holder",true);
        }catch (Exception e){}
        return "-info";
    }

    public ModelMap publish(T object, CustomMapper<T>mapper, ModelMap map){
        object.setDate(DateUtils.getDate());
        if(object.getId()==null){
            /* inform 是管理员管理的，比较特殊 */
            if(map.get("inform")!=null){
                mapper.insertSelective(object);
                map.put("message","发布成功");
                return map;
            }
            int id=(int)session.getAttribute("memId");
            /* 一个账号只能注册一个公司 */
            if(object instanceof Corporation){
                Corporation corporation=mapper.getByMemId(id);
                if(corporation!=null){
                    map.put("message","您已经注册了名为'"+corporation.getName()+"'的公司了");
                    return map;
                }
            }
            object.setMemId(id);
            mapper.insertSelective(object);
            map.put("message","发布成功");
        }else{
            mapper.updateByPrimaryKeySelective(object);
            map.put("message","修改成功");
        }
        return map;
    }

    public ModelMap update(T object,CustomMapper<T>mapper,ModelMap map){
        object.setDate(DateUtils.getDate());
        mapper.updateByPrimaryKeySelective(object);
        map.put("message","更新成功");
        return map;
    }

    public ModelMap delete(String id,CustomMapper<T>mapper,ModelMap map){
        try{
            mapper.deleteByPrimaryKey(Integer.parseInt(id));
            map.put("message","删除成功");
        }catch (Exception e){
            map.put("message","删除失败");
        }
        return map;
    }
}
