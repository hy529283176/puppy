package com.fading.puppy.service;


import com.fading.puppy.entity.MapBookMark;

import java.util.List;

public interface IMapBookMarkService {
    public static final String SERVICE_BEAN_NAME = "mapBookMarkService";

    /*
     * 保存或修改地图书签/标注
     */
    String saveOrUpdateMapBookMark(MapBookMark mapBookMarkEntity);

    /*
     * 删除地图书签/标注
     */
    void deleteMapBookMark(String rid);

    /*
     * 通过标记类型查询地图书签/标注
     */
    List<MapBookMark> getAllsByMarkType(String markType);
}
