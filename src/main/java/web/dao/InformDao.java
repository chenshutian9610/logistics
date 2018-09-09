package web.dao;

import web.beans.Inform;

import java.util.List;

public interface InformDao {
    public void insert(Inform inform);

    public void update(Inform inform);

    public List<Inform> select(String title);

    public void delete(Inform inform);

    public Inform getById(int id);
}
