package com.k2data.kbc.announce.service;

import com.k2data.kbc.announce.model.Announce;

import java.util.List;

public interface AnnounceService {

    void save(Announce model);//持久化

    void save(List<Announce> models);//批量持久化

    void deleteById(Integer id);//通过主鍵刪除

    void deleteByIds(String ids);//批量刪除 eg：ids -> “1,2,3,4”

    void update(Announce model);//更新

    Announce findById(Integer id);//通过ID查找

    Announce findBy(String fieldName, Object value) throws TooManyResultsException; //通过Model中某个成员变量名称（非数据表中column的名称）查找,value需符合unique约束

    List<Announce> findByIds(String ids);//通过多个ID查找//eg：ids -> “1,2,3,4”

    List<Announce> findByCondition(Condition condition);//根据条件查找

    List<Announce> findAll();//获取所有

}
