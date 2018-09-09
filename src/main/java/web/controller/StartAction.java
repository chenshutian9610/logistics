package web.controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import web.beans.Car;
import web.beans.Corporation;
import web.beans.Goods;
import web.beans.Inform;
import web.service.CarService;
import web.service.CorporationService;
import web.service.GoodsService;
import web.service.InformService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StartAction extends ActionSupport {
    Map session = ActionContext.getContext().getSession();
    Map request = (Map) ActionContext.getContext().get("request");
    @Resource(name = "goodsService")
    private GoodsService goodsService;
    @Resource(name = "carService")
    private CarService carService;
    @Resource(name = "informService")
    private InformService informService;
    @Resource(name = "corporationService")
    private CorporationService corporationService;

    public String init() {
        List<Corporation> cors = corporationService.select("");
        List<Car> cars = carService.select("");
        List<Goods> goods = goodsService.select("");
        List<Inform> informs = informService.select("");
        if (cors == null)
            cors = new ArrayList<Corporation>();
        if (cars == null)
            cars = new ArrayList<Car>();
        if (goods == null)
            goods = new ArrayList<Goods>();
        if (informs == null)
            informs = new ArrayList<Inform>();
        request.put("cors", cors);
        request.put("cars", cars);
        request.put("goods", goods);
        request.put("informs", informs);
        return "start";
    }

    public String search() {
        request.put("search", "true");
        return init();
    }
}
