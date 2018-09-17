package web.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import web.domain.Corporation;
import web.domain.Example;

import java.util.List;

public interface CustomMapper<T> {
    /* 如果没使用 @Param 会报错：没有对应的 getter 方法 */
    @Select("select*from ${table} limit 5")
    public List<T>getList(@Param("table") String table);

    @Select("select*from ${table} where mem_id=#{id}")
    public List<T>getDependencies(@Param("table")String table,@Param("id")Integer id);

    /* goods 和 car 专用 */
    @Select("select*from ${table} where start like #{name}")
    public List<T>getByStart(@Param("table") String table,@Param("name") String name);

    @Select("select name from corporation where mem_id=#{memId}")
    public Corporation getByMemId(int memId);

    List<T>selectByExample(Example example);
    T selectByPrimaryKey(Integer id);
    int insertSelective(T object);
    int updateByPrimaryKeySelective(T object);
    int deleteByPrimaryKey(Integer id);
}
