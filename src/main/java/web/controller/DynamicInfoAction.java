package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.DynamicInfoDao;
import web.domain.DynamicInfo;

public class DynamicInfoAction extends BaseAction<DynamicInfo> {
    @Autowired
    private DynamicInfoDao dynamicInfoDao;

    public DynamicInfoAction(){
        super();
        model=new DynamicInfo();
    }

    public String entry(){
        return entry(dynamicInfoDao);
    }

    public String info() {
        return info(dynamicInfoDao);
    }

    public String publish(){
        return publish(dynamicInfoDao);
    }

    public String delete(){
        return delete(dynamicInfoDao);
    }
}
