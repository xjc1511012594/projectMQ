package com.imooc.service;

/**
 * @author xulei
 * @date 2019-12-30 16:45
 */
public interface StuService {
    void saveStu();

    void updateStu(int id);

    void deleteStu(int id);

    Object getStuInfo(int id);
}
