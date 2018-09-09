package web.dao;

import web.beans.Corporation;
import web.beans.Member;

import java.util.List;

public interface CorporationDao {
    public void insert(Corporation corporation);

    public void update(Corporation corporation);

    public void delete(Corporation corporation);

    public List<Corporation> select(String name);

    public Corporation getById(int id);

    public Corporation getByMember(Member member);
}
