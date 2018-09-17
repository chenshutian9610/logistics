package web.mapper;

import org.apache.ibatis.annotations.Param;
import web.domain.Dynamicinfo;
import web.domain.DynamicinfoExample;

import java.util.List;

public interface DynamicinfoMapper extends CustomMapper<Dynamicinfo>{
    long countByExample(DynamicinfoExample example);

    int deleteByExample(DynamicinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Dynamicinfo record);

    int insertSelective(Dynamicinfo record);

    List<Dynamicinfo> selectByExample(DynamicinfoExample example);

    Dynamicinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Dynamicinfo record, @Param("example") DynamicinfoExample example);

    int updateByExample(@Param("record") Dynamicinfo record, @Param("example") DynamicinfoExample example);

    int updateByPrimaryKeySelective(Dynamicinfo record);

    int updateByPrimaryKey(Dynamicinfo record);
}