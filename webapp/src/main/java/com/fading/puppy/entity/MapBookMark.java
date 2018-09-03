package com.fading.puppy.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 地图书签/标注
 */
@Entity
@Table(name = MapBookMark.TableName)
public class MapBookMark implements Serializable {



    /**
     * 实体类对应数据库表名.
     */
    public static final String TableName = "MAP_BOOKMARK";

    private String rid;
    private String createTime;
    private String createUser;
    private String descript;
    private String extent;
    private String name;
    private String markType;

    @Id
    @Column(name = "RID")
    public String getRid() {
        return rid;
    }

    @Basic
    @Column(name = "CREATETIME")
    public String getCreateTime() {
        return createTime;
    }

    @Basic
    @Column(name = "CREATEUSER")
    public String getCreateUser() {
        return createUser;
    }

    @Basic
    @Column(name = "DESCRIPT")
    public String getDescript() {
        return descript;
    }

    @Basic
    @Column(name = "EXTENT")
    public String getExtent() {
        return extent;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Basic
    @Column(name = "MARKTYPE")
    public String getMarkType() {
        return markType;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public void setExtent(String extent) {
        this.extent = extent;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setMarkType(String markType) {
        this.markType = markType;
    }
}
