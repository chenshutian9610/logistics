package web.dao;

import org.springframework.stereotype.Repository;
import web.domain.Corporation;

import java.util.List;

@Repository
public class CorporationDao extends BaseDao<Corporation> {
    public Corporation selectByMemberName(String name) {
        List<Corporation>result=session.find("from Corporation cor where cor.member.name=?", name);
        if(result.size()==0)
            return null;
        return result.get(0);
    }
    public List<Corporation> selectByVagueName(String name){
        return (List<Corporation>) session.find("from Corporation cor where cor.name like '%"+name+"%'");
    }
}
