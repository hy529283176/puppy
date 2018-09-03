package com.fading.puppy.dao;

import com.fading.puppy.entity.MapBookMark;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * 地图书签/标注
 */
@Repository(IMapBookMarkDao.DAO_BEAN_NAME)
public interface IMapBookMarkDao extends PagingAndSortingRepository<MapBookMark,String> {
    /**
     * Spring注册时的名称.
     */
    public static final String DAO_BEAN_NAME = "mapBookMarkDao";

    List<MapBookMark> findAllByMarkType(String markType);
}
