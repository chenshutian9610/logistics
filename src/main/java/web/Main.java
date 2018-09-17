package web;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;
import web.domain.Member;
import web.domain.MemberExample;
import web.mapper.MemberMapper;

import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
    @Test
    public void databaseInit() throws Exception{
        Resource ddl=new ClassPathResource("import.sql");
        ScriptRunner runner=new ScriptRunner(context.getBean(BasicDataSource.class).getConnection());
        runner.runScript(new InputStreamReader(ddl.getInputStream()));
    }
    @Test
    public void memberTest(){
        MemberMapper mapper=context.getBean(MemberMapper.class);
        MemberExample memberExample=new MemberExample();
        MemberExample.Criteria criteria=memberExample.createCriteria();
        criteria.andAnswerIsNull(); //  andXxxYyy()
        List<Member>members=mapper.selectByExample(memberExample);
        for(Member member:members)
            System.out.println(member.getName());
    }
    @Test
    public void customMapperTest(){
        MemberMapper mapper=context.getBean(MemberMapper.class);
    }
    @Test
    public void my(){
    }
}
