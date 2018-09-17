package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.*;
import web.mapper.*;

import java.util.List;

@Controller
public class StartController {
    @Autowired private MemberMapper memberMapper;
    @Autowired private CorporationMapper corporationMapper;
    @Autowired private GoodsMapper goodsMapper;
    @Autowired private CarMapper carMapper;
    @Autowired private InformMapper informMapper;

    @RequestMapping("/")
    public String init(ModelMap map){
        List<Corporation> corporations=corporationMapper.getList("corporation");
        List<Inform>informs=informMapper.getList("inform");
        map.put("corporations",corporations);
        map.put("informs",informs);
        if(map.get("show")==null){
            List<Car>cars=carMapper.getList("car");
            List<Goods>goods=goodsMapper.getList("goods");
            map.put("cars",cars);
            map.put("goods",goods);
        }
        return "index";
    }

    @RequestMapping("/search")
    public String search(String condition,String status,ModelMap map){
        List<Goods>goods=null;
        List<Car>cars=null;
        List<Corporation>corporations=null;
        boolean fail=false;
        switch (status){
            case "用户名":
                Member member=memberMapper.getByName(condition);
                if(member==null){
                    fail=true;
                    break;
                }
                System.out.println("Hello,I'm here");
                int id=member.getId();
                goods=goodsMapper.getDependencies("goods",id);
                cars=carMapper.getDependencies("car",id);
                corporations=corporationMapper.getDependencies("corporation",id);
                if(corporations.size()==0&&goods.size()==0&& cars.size()==0){
                    fail=true;
                    break;
                }
                if(corporations.size()==1)
                    map.put("corporation",corporations.get(0));
                map.put("goods",goods);
                map.put("cars",cars);
                map.put("member",member.getName());
                map.put("show","member");
                break;
            case "公司名":
                corporations=corporationMapper.getByVagueName("%"+condition+"%");
                if(corporations.size()==0){
                    fail=true;
                    break;
                }
                map.put("cors",corporations);   //  这里要避免与 init 的 corporations 冲突
                map.put("show","corporation");
                break;
            case "起始地":
                cars=carMapper.getByStart("car","%"+condition+"%");
                goods=goodsMapper.getByStart("goods","%"+condition+"%");
                if(cars.size()==0&&goods.size()==0){
                    fail=true;
                    break;
                }
                map.put("goods",goods);
                map.put("cars",cars);
                map.put("show","start");
                break;
        }
        if(fail)
            map.put("search_message","找不到匹配的数据");
        return init(map);
    }
}
