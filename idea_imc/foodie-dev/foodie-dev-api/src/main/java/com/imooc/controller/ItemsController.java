package com.imooc.controller;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.service.ItemService;
import com.imooc.utils.IMOOCJSONResult;
import com.imooc.utils.PagedGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xulei
 * @date 2019-12-12 11:48
 */
@Api(value = "商品", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
public class ItemsController {
    public static final String FOODIE_SHOPCART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;
    public static final Integer PAGE_SIZE = 20;

    @Autowired
    private ItemService itemService;

    @ApiOperation(value="查询商品详情",notes ="查询商品详情",tags ="GET" )
    @GetMapping("/info/{itemId}")
    public IMOOCJSONResult info(@ApiParam(name="itemId",value = "商品ID",required = true)
                                    @PathVariable("itemId")String itemId){

        if(StringUtils.isEmpty(itemId)){
            IMOOCJSONResult.errorMsg("商品ID不能为空");
        }

        Items items = itemService.queyItemById(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);

        ItemInfoVO itemInfoVO = new ItemInfoVO();
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgList);
        itemInfoVO.setItemParams(itemsParam);
        itemInfoVO.setItemSpecList(itemsSpecs);

        return IMOOCJSONResult.ok(itemInfoVO);
    }

    @ApiOperation(value="查询商品评价等级",notes ="查询商品评价等级",tags ="GET" )
    @GetMapping("/commentLevel")
    public IMOOCJSONResult commentLevel(
            @ApiParam(name="itemId",value = "商品ID",required = true)
            @RequestParam String itemId){
        if(StringUtils.isEmpty(itemId)){
            IMOOCJSONResult.errorMsg("商品ID不能为空");
        }
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return IMOOCJSONResult.ok(commentLevelCountsVO);
    }

    @ApiOperation(value="查询商品评论",notes ="查询商品评论",tags ="GET" )
    @GetMapping("/comments")
    public IMOOCJSONResult comments(
            @ApiParam(name = "itemId",value = "商品Id",required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level",value = "商品等级",required = true)
            @RequestParam Integer level,
            @ApiParam(name = "page",value = "第几页",required = true)
            @RequestParam Integer page,
            @ApiParam(name = "pageSize",value = "页面大小",required = true)
            @RequestParam Integer pageSize
    ){
        if(StringUtils.isEmpty(itemId)){
            IMOOCJSONResult.errorMsg("商品ID不能为空");
        }
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=COMMON_PAGE_SIZE;
        }
        PagedGridResult pagedComments = itemService.queryPagedComments(itemId, level, page, pageSize);
        return IMOOCJSONResult.ok(pagedComments);
    }

    @ApiOperation(value="搜索商品列表",notes ="搜索商品列表",tags ="GET" )
    @GetMapping("/search")
    public IMOOCJSONResult search( @ApiParam(name = "keywords",value = "关键字",required = true)
                                       @RequestParam String keywords,
                                   @ApiParam(name = "sort",value = "排序",required = true)
                                       @RequestParam String sort,
                                   @ApiParam(name = "page",value = "第几页",required = true)
                                       @RequestParam Integer page,
                                   @ApiParam(name = "pageSize",value = "页面大小",required = true)
                                       @RequestParam Integer pageSize){
        if(StringUtils.isEmpty(keywords)){
            IMOOCJSONResult.errorMsg("关键字不能为空");
        }
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=PAGE_SIZE;
        }
        PagedGridResult result = itemService.searhItems(keywords, sort, page, pageSize);

        return IMOOCJSONResult.ok(result);
    }

    @ApiOperation(value="通过商品分类ID搜索商品列表",notes ="通过商品分类ID搜索商品列表",tags ="GET" )
    @GetMapping("/catItems")
    public IMOOCJSONResult catItems(@ApiParam(name = "catId",value = "三级分类id",required = true)
                                        @RequestParam Integer catId,
                                    @ApiParam(name = "sort",value = "排序",required = true)
                                        @RequestParam String sort,
                                    @ApiParam(name = "page",value = "第几页",required = true)
                                        @RequestParam Integer page,
                                    @ApiParam(name = "pageSize",value = "页面大小",required = true)
                                        @RequestParam Integer pageSize){
        if(catId==null){
            IMOOCJSONResult.errorMsg("商品分类ID不能为空");
        }
        if(page==null){
            page=1;
        }
        if(pageSize==null){
            pageSize=PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searhItems(catId, sort, page, pageSize);
        return IMOOCJSONResult.ok(pagedGridResult);
    }

    /**购物车的商品规格会变化  重新查询 渲染到前端*/
    @ApiOperation(value="根据商品规格ids查找最新的商品数据",notes ="根据商品规格ids查找最新的商品数据",tags ="GET" )
    @GetMapping("/refresh")
    public IMOOCJSONResult refresh(
            @ApiParam(name = "itemSpecIds",value = "商品规格ids",required = true)
            @RequestParam String itemSpecIds
    ){
        if(StringUtils.isEmpty(itemSpecIds)){
            IMOOCJSONResult.errorMsg("商品规格id空");
        }
        List<ShopcartVO> shopcartVOList = itemService.queryItemsBySpecIds(itemSpecIds);
        return IMOOCJSONResult.ok(shopcartVOList);
    }

}
