package com.imooc.controller;

import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.AddressBO;
import com.imooc.service.AddressService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.MobileEmailUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xulei
 * @date 2019-12-16 17:30
 */
@Api(value = "地址相关",tags = "地址相关api接口")
@RequestMapping("/address")
@RestController
public class AddressController {

        /**
         *  用户在订单页面，可以对收货地址进行如下操作：
         *  1.查询用户所有收货地址
         *  2.新增用户收货地址
         *  3.修改用户收货地址
         *  4.删除用户收货地址
         *  5.设置默认收货地址
         * */
        @Autowired
        private AddressService addressService;

        @ApiOperation(value = "根据用户ID查询收货地址",notes = "根据用户ID查询收货地址",httpMethod = "POST")
        @PostMapping("/list")
        public IMOOCJSONResult list(
                @RequestParam String userId){
            if(StringUtils.isEmpty(userId)){
                IMOOCJSONResult.errorMsg("userId不能为空");
            }
            List<UserAddress> userAddresses = addressService.queryAll(userId);
            return IMOOCJSONResult.ok(userAddresses);
        }

        @ApiOperation(value = "用户新增地址",notes = "用户新增地址",httpMethod = "POST")
        @PostMapping("/add")
        public IMOOCJSONResult add(@RequestBody AddressBO addressBO){
            IMOOCJSONResult result = checkAddress(addressBO);
            if(result.getStatus()!=200){
                return result;
            }
            addressService.addNewUserAddress(addressBO);

            return IMOOCJSONResult.ok();
        }

        private IMOOCJSONResult checkAddress(AddressBO addressBO) {
            String receiver = addressBO.getReceiver();
            if (StringUtils.isEmpty(receiver)) {
                return IMOOCJSONResult.errorMsg("收货人不能为空");
            }
            if (receiver.length() > 12) {
                return IMOOCJSONResult.errorMsg("收货人姓名不能太长");
            }

            String mobile = addressBO.getMobile();
            if (StringUtils.isEmpty(mobile)) {
                return IMOOCJSONResult.errorMsg("收货人手机号不能为空");
            }
            if (mobile.length() != 11) {
                return IMOOCJSONResult.errorMsg("收货人手机号长度不正确");
            }
            boolean isMobileOk = MobileEmailUtils.checkMobileIsOk(mobile);
            if (!isMobileOk) {
                return IMOOCJSONResult.errorMsg("收货人手机号格式不正确");
        }

        String province = addressBO.getProvince();
        String city = addressBO.getCity();
        String district = addressBO.getDistrict();
        String detail = addressBO.getDetail();
        if (StringUtils.isEmpty(province) ||
                StringUtils.isEmpty(city) ||
                StringUtils.isEmpty(district) ||
                StringUtils.isEmpty(detail)) {
            return IMOOCJSONResult.errorMsg("收货地址信息不能为空");
        }

        return IMOOCJSONResult.ok();
    }

        @ApiOperation(value = "修改用户地址",notes = "修改用户地址",tags = "POST")
        @PostMapping("/update")
        public IMOOCJSONResult update(
                @RequestBody AddressBO addressBO){
            IMOOCJSONResult result = checkAddress(addressBO);
            if(result.getStatus()!=200){
                return result;
            }
            addressService.updateUserAddress(addressBO);
            return IMOOCJSONResult.ok();
        }

        @ApiOperation(value = "删除用户地址",notes = "删除用户地址",tags = "POST")
        @PostMapping("/delete")
        public IMOOCJSONResult delete(@RequestParam String userId,
                                      @RequestParam String addressId){

            if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(addressId)){
                return IMOOCJSONResult.errorMsg("参数问题");
            }
            addressService.deleteUserAddress(userId,addressId);
            return IMOOCJSONResult.ok();
        }

        @ApiOperation(value = "用户设置默认地址",notes = "用户设置默认地址",tags = "POST")
        @PostMapping("/setDefalut")
        public IMOOCJSONResult setDefault(@RequestParam String userId,
                                          @RequestParam String addressId){
            if(StringUtils.isEmpty(userId)||StringUtils.isEmpty(addressId)){
                return IMOOCJSONResult.errorMsg("参数问题");
            }
            addressService.updateUserAddressToBeDefault(userId,addressId);
            return IMOOCJSONResult.ok();
        }

}
