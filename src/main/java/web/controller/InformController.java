package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Inform;
import web.domain.InformExample;
import web.mapper.InformMapper;

@Controller
@RequestMapping("/inform")
public class InformController extends BaseController<Inform> {
    @Autowired
    private InformMapper mapper;

    @RequestMapping("/entry")
    public String entry(ModelMap map) {
        map.put("inform",true);
        entry(mapper, new InformExample(), map);
        return "inform";
    }

    @RequestMapping("/info")
    public String info(String id, ModelMap map) {
        map.put("inform",true);
        return "inform" + info(id, mapper, map);
    }

    @RequestMapping("/publish")
    public String publish(Inform object, ModelMap map) {
        map.put("inform",true);
        return entry(publish(object, mapper, map));
    }

    @RequestMapping("/update")
    public String update(Inform object, ModelMap map) {
        return entry(update(object, mapper, map));
    }

    @RequestMapping("/delete")
    public String delete(String id, ModelMap map) {
        return entry(delete(id, mapper, map));
    }
}
