package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import web.domain.Member;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseDao<T> {
    @Autowired
    protected HibernateTemplate session;
    private Class<T> clazz;
    private String clazzName;

    @PostConstruct
    public void init() {
        /**
         *  泛型需要在编译时指定而不是在运行时，如果直接使用 BaseDao<Car> 则报错（类型转换错误），使用 CarDao 则不会
         *  ps:下列操作如果在构造函数中，当使用 aop 时也会报错（类型转换错误），所以最好在 init-method 初始化 clazz
         */
        Type type = getClass().getGenericSuperclass();
        clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        String str = clazz.getName();
        clazzName = str.substring(str.lastIndexOf(".") + 1);
    }

    public void insert(T obj) {
        session.save(obj);
    }

    public void update(T obj) {
        session.update(obj);
    }

    public void deleteById(int id) {
        session.bulkUpdate("delete from "+clazzName+" obj where obj.id=?",id);
    }

    public List<T> selectAll() {
        return (List<T>) session.find("from " + clazzName);
    }

    public List<T> selectAll(int size){
        List<T>result=(List<T>)session.find("from "+clazzName);
        result=result.size()>size?result.subList(0,size):result;
        return result;
    }

    public T selectById(int id) {
        return session.get(clazz, id);
    }

    public T selectByName(String name) {
        List<T>result=session.find("from " + clazzName + " obj where obj.name=?", name);
        if(result.size()==0)
            return null;
        return result.get(0);
    }

    public Member selectMemberByOtherId(int id){
        return (Member)session.find("select obj.member from "+clazzName+" obj where obj.id=?",id).get(0);
    }
}
