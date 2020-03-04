package com.imooc.service;

import com.imooc.pojo.Carousel;

import java.util.List;

/**
 * @author xulei
 * @date 2019-12-11 16:33
 */
public interface CarouselService {

    /**
     * 查询轮播图列表
     * */
    List<Carousel> queryAll(Integer isShow);

}
