package web.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import web.beans.Car;
import web.beans.Member;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("carDao")
public class CarDao {
    @Resource(name = "template")
    private HibernateTemplate template;

    public HibernateTemplate getTemplate() {
        return template;
    }

    public void setTemplate(HibernateTemplate template) {
        this.template = template;
    }

    public void insert(Car car) {
        template.save(car);
    }

    public void update(Car car) {
        template.update(car);
    }

    public List<Car> select(String title) {
        List<Car> cars = template.find("from Car where title like '%" + title + "%'");
        return cars;
    }


    public void delete(Car car) {
        template.delete(car);
    }

    public Car getById(int id) {
        return template.get(Car.class, id);
    }

    public List<Car> getByMember(Member member) {
        List<Car> all = template.find("from Car");
        List<Car> result = new ArrayList<Car>();
        for (Car c : all) {
            if (c.getMember().getId() == member.getId())
                result.add(c);
        }
        return result;
    }

}
