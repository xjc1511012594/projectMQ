package com.imooc.service.impl;

import com.imooc.enums.Sex;
import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UserBO;
import com.imooc.service.center.UserService;
import com.imooc.utils.DateUtil;
import com.imooc.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * Created by xl on 2019/12/2.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UsersMapper userMapper;
    @Autowired
    private Sid sid;
    
    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Example userexample = new Example(Users.class);
        Example.Criteria userCriteria = userexample.createCriteria();
        userCriteria.andEqualTo("username",username);
        Users user = userMapper.selectOneByExample(userexample);
        return user==null?false:true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {
        //生成唯一的主键
        String userId = sid.nextShort();
        Users user = new Users();
        user.setId(userId);
        try {
            String md5Str = MD5Utils.getMD5Str(userBO.getPassword());
            user.setPassword(md5Str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user.setNickname(userBO.getUsername());
        user.setUsername(userBO.getUsername());
        user.setFace(USER_FACE);
        user.setSex(Sex.secret.type);
        user.setBirthday(DateUtil.convertToDate("1992-07-09"));
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        userMapper.insert(user);
        return user;
    }

    @Override
    public Users queryUserForLogin(String username, String password) {

        Example userexample = new Example(Users.class);
        Example.Criteria userCriteria = userexample.createCriteria();
        userCriteria.andEqualTo("username",username);
        userCriteria.andEqualTo("password",password);
        Users user = userMapper.selectOneByExample(userexample);
        return user;
    }
}
