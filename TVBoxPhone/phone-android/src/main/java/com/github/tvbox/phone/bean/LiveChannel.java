package com.github.tvbox.phone.bean;

import java.io.Serializable;

public class LiveChannel implements Serializable {

    private String name;
    private String url;
    private String groupName;
    private int channelNum;

    public LiveChannel() {}

    public LiveChannel(String name, String url, String groupName, int channelNum) {
        this.name = name;
        this.url = url;
        this.groupName = groupName;
        this.channelNum = channelNum;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }
    public int getChannelNum() { return channelNum; }
    public void setChannelNum(int channelNum) { this.channelNum = channelNum; }
}