package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Car;
import web.domain.CarExample;
import web.mapper.CarMapper;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController<Car>{
    @Autowired private CarMapper mapper;

    @RequestMapping("/entry")
    public String entry(ModelMap map){
        entry(mapper,new CarExample(),map);
        return "car";
    }

    @RequestMapping("/info")
    public String info(String id, ModelMap map){
        return "car"+info(id,mapper,map);
    }

    @RequestMapping("/publish")
    public String publish(Car object,ModelMap map){
        return entry(publish(object,mapper,map));
    }

    @RequestMapping("/update")
    public String update(Car object,ModelMap map){
        return entry(update(object,mapper,map));
    }

    @RequestMapping("/delete")
    public String delete(String id,ModelMap map){
        return entry(delete(id,mapper,map));
    }
}
