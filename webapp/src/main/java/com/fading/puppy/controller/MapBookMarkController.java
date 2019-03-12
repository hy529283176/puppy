package com.fading.puppy.controller;

import com.fading.puppy.entity.MapBookMark;
import com.fading.puppy.service.IMapBookMarkService;
import com.fading.puppy.tools.AccessTokenOrJsapiTicketUtil;
import com.fading.puppy.tools.ExportTextUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@Controller
@Api(tags = "图签接口")
@RequestMapping("/mapbookmark")
public class MapBookMarkController {
    @Inject
    @Named(IMapBookMarkService.SERVICE_BEAN_NAME)
    private IMapBookMarkService mapBookMarkService;

    @RequestMapping(value = "/getIndex")
    public ModelAndView getIndex(){
        return new ModelAndView("jsp/test");
    }

    @RequestMapping(value = "/getIndex2")
    public ModelAndView getIndex2(HttpServletRequest request){
        ModelAndView mov = new ModelAndView("jsp/test2");
        mov.addObject("appId", AccessTokenOrJsapiTicketUtil.getAppId());
        return mov;
    }

    /**
     * 保存或修改地图书签/标注
     */
    @ApiOperation(value = "保存图签", notes = "")
    @ApiImplicitParam(name = "bookMarkStr", value = "图签信息", required = true, dataType = "String")
    @RequestMapping(value = "/saveUpdateMapBookMark",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> saveUpdateMapBookMark(String bookMarkStr){
        JSONObject mapBookMarkJson = new JSONObject(bookMarkStr);
        System.out.println(mapBookMarkJson);
        MapBookMark mapBookMark = new MapBookMark();
        mapBookMark.setRid(mapBookMarkJson.getString("rid"));
        mapBookMark.setName(mapBookMarkJson.getString("name"));
        mapBookMark.setCreateTime(mapBookMarkJson.getString("createTime"));
        mapBookMark.setCreateUser(mapBookMarkJson.getString("createUser"));
        mapBookMark.setDescript(mapBookMarkJson.getString("descript"));
        JSONObject extent = mapBookMarkJson.getJSONObject("extent");
        String strExtent = extent.toString();
        System.out.println(strExtent);
        mapBookMark.setExtent(strExtent);
        mapBookMark.setMarkType(mapBookMarkJson.getString("markType"));
        String rid = this.mapBookMarkService.saveOrUpdateMapBookMark(mapBookMark);
        Map<String,String> map = new HashMap<String, String>();
        String status = "failed";
        if (rid != null){
            map.put("status","ok");
        }else {
            map.put("status",status);
        }
        return  map;
    }

    /**
     * 删除地图书签/标注
     */
    @RequestMapping(value = "/deleteMapBookMark",method = RequestMethod.GET)
    @ResponseBody
    public void deleteMapBookMark(String rid){
        this.mapBookMarkService.deleteMapBookMark(rid);
    }

    /**
     * 查询地图书签/标注
     */
    @RequestMapping(value = "/getMapBookMark/{markType}",method = RequestMethod.GET)
    @ResponseBody
    public List<MapBookMark> getMapBookMark(@PathVariable String markType){
        if(markType != null){
                List<MapBookMark> markBookLsit = this.mapBookMarkService.getAllsByMarkType(markType);
                return markBookLsit;
        }
        return null;
    }

    @RequestMapping("/exportText")
    public void exportText(String text,HttpServletResponse response){
        //将集合转换成字符串
        ExportTextUtil.writeToText(response,text);
    }

}
