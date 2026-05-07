package com.github.tvbox.core.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

@XStreamAlias("rss")
public class AbsXml implements Serializable {
    @XStreamAlias("list")
    public Movie movie;
}
