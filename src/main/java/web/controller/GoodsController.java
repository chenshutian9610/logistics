package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Goods;
import web.domain.GoodsExample;
import web.mapper.GoodsMapper;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods>{
    @Autowired private GoodsMapper mapper;

    @RequestMapping("/entry")
    public String entry(ModelMap map){
        entry(mapper,new GoodsExample(),map);
        return "goods";
    }

    @RequestMapping("/info")
    public String info(String id, ModelMap map){
        return "goods"+info(id,mapper,map);
    }

    @RequestMapping("/publish")
    public String publish(Goods object,ModelMap map){
        return entry(publish(object,mapper,map));
    }

    @RequestMapping("/update")
    public String update(Goods object,ModelMap map){
        return entry(update(object,mapper,map));
    }

    @RequestMapping("/delete")
    public String delete(String id,ModelMap map){
        return entry(delete(id,mapper,map));
    }
}
