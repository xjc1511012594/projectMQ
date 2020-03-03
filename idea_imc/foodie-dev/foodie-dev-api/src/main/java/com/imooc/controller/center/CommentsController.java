package com.imooc.controller.center;

import com.imooc.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xulei
 * @date 2019-12-16 18:17
 */
@Api(value = "用户中心评价模块",tags = "用户中心评价模块相关接口")
@RestController
@RequestMapping("/mycomments")
public class CommentsController {

    @ApiOperation(value = "查询评价列表", notes = "查询评价列表", httpMethod = "POST")
    @PostMapping("/pending")
    public IMOOCJSONResult pending(){

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public IMOOCJSONResult saveList(){

        return IMOOCJSONResult.ok();
    }

    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @PostMapping("/query")
    public IMOOCJSONResult query(){

        return IMOOCJSONResult.ok();
    }

}
