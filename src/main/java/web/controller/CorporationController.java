package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Corporation;
import web.domain.CorporationExample;
import web.mapper.CorporationMapper;

@Controller
@RequestMapping("/corporation")
public class CorporationController extends BaseController<Corporation> {
    @Autowired
    private CorporationMapper mapper;

    @RequestMapping("/entry")
    public String entry(ModelMap map) {
        entry(mapper, new CorporationExample(), map);
        return "corporation";
    }

    @RequestMapping("/info")
    public String info(String id, ModelMap map) {
        return "corporation" + info(id, mapper, map);
    }

    @RequestMapping("/publish")
    public String publish(Corporation object, ModelMap map) {
        return entry(publish(object, mapper, map));
    }

    @RequestMapping("/update")
    public String update(Corporation object, ModelMap map) {
        return entry(update(object, mapper, map));
    }

    @RequestMapping("/delete")
    public String delete(String id, ModelMap map) {
        return entry(delete(id, mapper, map));
    }
}
