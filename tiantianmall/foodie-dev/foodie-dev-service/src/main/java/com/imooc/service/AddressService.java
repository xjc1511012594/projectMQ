package com.imooc.service;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;

import java.util.List;

/**
 * @author xulei
 * @date 2019-12-16 21:00
 */
public interface AddressService {
        /**
         * 根据用户id查询用户地址
         * */
        List<UserAddress>  queryAll(String userId);
        /**
         * 用户新增地址
         * */
        void addNewUserAddress(AddressBO addressBO);
        /**
         * 用户修改地址
         * */
        void updateUserAddress(AddressBO addressBO);
        /**
         *根据用户id和地址id  删除对应的用户地址信息
         * */
        void  deleteUserAddress(String userId,String addressId);
        /**
         *修改默认地址
         * */
        void  updateUserAddressToBeDefault(String userId,String addressId);
        /**
         * 用户Id和地址Id
         * */
         UserAddress queryUserAddress(String userId,String addressId);

}
