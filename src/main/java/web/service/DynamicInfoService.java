package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.beans.DynamicInfo;
import web.dao.DynamicInfoDao;

import javax.annotation.Resource;
import java.util.List;

@Service("infoService")
@Transactional
public class DynamicInfoService {
    @Resource(name = "infoDao")
    DynamicInfoDao dynamicInfoDao;

    public DynamicInfoDao getDynamicInfoDao() {
        return dynamicInfoDao;
    }

    public void setDynamicInfoDao(DynamicInfoDao dynamicInfoDao) {
        this.dynamicInfoDao = dynamicInfoDao;
    }

    public void insert(DynamicInfo info) {
        dynamicInfoDao.insert(info);
    }

    public void update(DynamicInfo info) {
        dynamicInfoDao.update(info);
    }

    public List<DynamicInfo> select(String title) {
        return dynamicInfoDao.select(title);
    }

    public void delete(DynamicInfo info) {
        dynamicInfoDao.delete(info);
    }

    public DynamicInfo getById(int id) {
        return dynamicInfoDao.getById(id);
    }
}
