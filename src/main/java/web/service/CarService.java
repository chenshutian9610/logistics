package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.Car;
import web.beans.Member;
import web.dao.CarDao;

import javax.annotation.Resource;
import java.util.List;

@Service("carService")
@Transactional
public class CarService {
    @Resource(name = "carDao")
    CarDao dao;

    public CarDao getDao() {
        return dao;
    }

    public void setDao(CarDao dao) {
        this.dao = dao;
    }

    public void insert(Car car) {
        dao.insert(car);
    }

    public void update(Car car) {
        dao.update(car);
    }

    public List<Car> select(String name) {
        return dao.select(name);
    }

    public void delete(Car car) {
        dao.delete(car);
    }

    public Car getById(int id) {
        return dao.getById(id);
    }

    public List<Car> getByMember(Member member) {
        return dao.getByMember(member);
    }
}
