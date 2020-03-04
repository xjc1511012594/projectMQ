package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;

/**
 * @author xulei
 * @date 2019-12-30 17:11
 */
public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    public Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId
     * @param centerUserBO
     */
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId
     * @param faceUrl
     * @return
     */
    public Users updateUserFace(String userId, String faceUrl);


}
