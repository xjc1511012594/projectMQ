package com.imooc.service;

import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * @author xulei
 * @date 2019-12-11 16:49
 */
public interface CategoryService {
    /**
     * 获取商品分类(一级分类)
     * */
    List<Category> queryAllRootLevelCat();
    /**
     * 获取商品子分类
     * */
    List<CategoryVO> getSubCatList(Integer rootCatId);
    /**
     * 查询每个一级分类下的最新6条商品数据
     * */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
