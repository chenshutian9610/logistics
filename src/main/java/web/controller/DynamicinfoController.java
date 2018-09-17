package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Dynamicinfo;
import web.domain.DynamicinfoExample;
import web.mapper.DynamicinfoMapper;

@Controller
@RequestMapping("/dynamicInfo")
public class DynamicinfoController extends BaseController<Dynamicinfo>{
    @Autowired private DynamicinfoMapper mapper;

    @RequestMapping("/entry")
    public String entry(ModelMap map){
        entry(mapper,new DynamicinfoExample(),map);
        return "dynamicInfo";
    }

    @RequestMapping("/info")
    public String info(String id, ModelMap map){
        return "dynamicInfo"+info(id,mapper,map);
    }

    @RequestMapping("/publish")
    public String publish(Dynamicinfo object,ModelMap map){
        return entry(publish(object,mapper,map));
    }

    @RequestMapping("/update")
    public String update(Dynamicinfo object,ModelMap map){
        return entry(update(object,mapper,map));
    }

    @RequestMapping("/delete")
    public String delete(String id,ModelMap map){
        return entry(delete(id,mapper,map));
    }
}
