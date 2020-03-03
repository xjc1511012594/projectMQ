package com.imooc.service.center;


import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;

/**
 * Created by xl on 2019/12/2.
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * */
    boolean queryUsernameIsExist(String name);
    /**
     * 创建用户
     * */
    Users createUser(UserBO userBO);

    Users queryUserForLogin(String username,String password);
}
