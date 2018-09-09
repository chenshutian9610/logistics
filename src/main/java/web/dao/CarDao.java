package web.dao;

import web.beans.Car;
import web.beans.Member;

import java.util.List;

public interface CarDao {
    public void insert(Car car);

    public void update(Car car);

    public List<Car> select(String title);

    public void delete(Car car);

    public Car getById(int id);

    public List<Car> getByMember(Member member);
}
