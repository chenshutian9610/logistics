package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.CarDao;
import web.domain.Car;

public class CarAction extends BaseAction<Car> {
    @Autowired 
    private CarDao carDao;

    public CarAction(){
        super();
        model=new Car();
    }

    public String entry(){
        return entry(carDao);
    }

    public String info() {
        return info(carDao);
    }

    public String publish(){
        return publish(carDao);
    }

    public String delete(){
        return delete(carDao);
    }
}
