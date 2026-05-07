package com.github.tvbox.phone.bean;

import java.io.Serializable;

public class VodInfo implements Serializable {

    private String id;
    private String name;
    private String pic;
    private String remark;
    private String sourceKey;
    private String sourceName;

    public VodInfo() {}

    public VodInfo(String id, String name, String pic, String remark) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.remark = remark;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPic() { return pic; }
    public void setPic(String pic) { this.pic = pic; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getSourceKey() { return sourceKey; }
    public void setSourceKey(String sourceKey) { this.sourceKey = sourceKey; }
    public String getSourceName() { return sourceName; }
    public void setSourceName(String sourceName) { this.sourceName = sourceName; }
}