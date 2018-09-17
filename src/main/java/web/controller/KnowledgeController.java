package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import web.domain.Knowledge;
import web.domain.KnowledgeExample;
import web.mapper.KnowledgeMapper;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController extends BaseController<Knowledge> {
    @Autowired
    private KnowledgeMapper mapper;

    @RequestMapping("/entry")
    public String entry(ModelMap map) {
        entry(mapper, new KnowledgeExample(), map);
        return "knowledge";
    }

    @RequestMapping("/info")
    public String info(String id, ModelMap map) {
        return "knowledge" + info(id, mapper, map);
    }

    @RequestMapping("/publish")
    public String publish(Knowledge object, ModelMap map) {
        return entry(publish(object, mapper, map));
    }

    @RequestMapping("/update")
    public String update(Knowledge object, ModelMap map) {
        return entry(update(object, mapper, map));
    }

    @RequestMapping("/delete")
    public String delete(String id, ModelMap map) {
        return entry(delete(id, mapper, map));
    }
}
