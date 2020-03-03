package com.imooc.service;

import com.imooc.pojo.Items;
import com.imooc.pojo.ItemsImg;
import com.imooc.pojo.ItemsParam;
import com.imooc.pojo.ItemsSpec;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

/**
 * @author xulei
 * @date 2019-12-12 11:52
 */
public interface ItemService {

    /**
     *根据商品ID查询商品详情
     * */
    Items queyItemById(String itemId);
    /**
     *根据商品ID查询商品图片列表
     * */
    List<ItemsImg> queryItemImgList(String itemId);
    /**
     *根据商品ID查询商品规格
     * */
    List<ItemsSpec> queryItemSpecList(String itemId);
    /**
     *根据商品ID查询商品参数
     * */
    ItemsParam queryItemParam(String itemId);
    /**
     *根据商品id查询商品的评价等级数量
     * */
    CommentLevelCountsVO queryCommentCounts(String itemId);
    /**
     *根据商品id查询商品的评价（分页）
     * */
    PagedGridResult queryPagedComments(String itemId, Integer level,
                                       Integer page, Integer pageSize);
    /**
     *搜索商品列表
     * */
    PagedGridResult searhItems(String keywords, String sort,
                               Integer page, Integer pageSize);
    /**
     *根据分类id搜索商品列表
     * */
    PagedGridResult searhItems(Integer catId, String sort,
                               Integer page, Integer pageSize);
    /**
     *根据规格ids查询最新的购物车中商品数据（用于刷新渲染购物车中的商品数据）
     * */
    List<ShopcartVO> queryItemsBySpecIds(String specIds);
    /**
     *根据商品规格id获取规格对象的具体信息
     * */
    ItemsSpec queryItemSpecById(String specId);
    /**
     *根据商品id获得商品图片主图url
     * */
    String queryItemMainImgById(String itemId);
    /**
     *减少库存
     * */
    void decreaseItemSpecStock(String specId, int buyCounts);

    Items queryItemById(String itemId);
}
