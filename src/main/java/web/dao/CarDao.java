package web.dao;

import org.springframework.stereotype.Repository;
import web.domain.Car;

import java.util.List;

@Repository
public class CarDao extends BaseDao<Car> {
    public List<Car> selectByMemberName(String name) {
        return (List<Car>) session.find("from Car c where c.member.name=?", name);
    }

    public List<Car> selectByStart(String start) {
        return (List<Car>) session.find("from Car c where c.start like '%" + start + "%'");
    }
}
