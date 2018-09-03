package com.fading.puppy.service;

import com.fading.puppy.dao.IMapBookMarkDao;
import com.fading.puppy.entity.MapBookMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service(IMapBookMarkService.SERVICE_BEAN_NAME)
public class MapBookMarkService implements IMapBookMarkService {
    @Autowired
    private IMapBookMarkDao mapBookMarkDao;

    @Override
    public String saveOrUpdateMapBookMark(MapBookMark mapBookMarkEntity) {
        if(mapBookMarkEntity != null){
            if(mapBookMarkEntity.getRid() == null || mapBookMarkEntity.getRid().isEmpty()){
                mapBookMarkEntity.setRid(UUID.randomUUID().toString());
            }
            this.mapBookMarkDao.save(mapBookMarkEntity);
            return mapBookMarkEntity.getRid();
        }
        else {
            throw null;
        }
    }

    @Override
    public void deleteMapBookMark(String rid) {
        if (rid != null && !rid.isEmpty()) {
            this.mapBookMarkDao.delete(rid);
        }
    }

    @Override
    public List<MapBookMark> getAllsByMarkType(String markType) {
        if(markType != null && !markType.isEmpty()){
            List<MapBookMark> mapBookMarkList = this.mapBookMarkDao.findAllByMarkType(markType);
            return mapBookMarkList;
        }
        return null;
    }
}
