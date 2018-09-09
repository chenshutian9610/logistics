package web.dao;

import web.beans.Knowledge;

import java.util.List;

public interface KnowledgeDao {
    public void insert(Knowledge knowledge);

    public void update(Knowledge knowledge);

    public List<Knowledge> select(String title);

    public void delete(Knowledge knowledge);

    public Knowledge getById(int id);
}
