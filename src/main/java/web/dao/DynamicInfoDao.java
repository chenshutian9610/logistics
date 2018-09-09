package web.dao;

import web.beans.DynamicInfo;

import java.util.List;

public interface DynamicInfoDao {
    public void insert(DynamicInfo info);

    public void update(DynamicInfo info);

    public List<DynamicInfo> select(String title);

    public void delete(DynamicInfo info);

    public DynamicInfo getById(int id);
}
