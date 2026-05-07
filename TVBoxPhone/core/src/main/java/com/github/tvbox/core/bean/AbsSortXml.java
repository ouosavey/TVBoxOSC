package com.github.tvbox.core.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias("rss")
public class AbsSortXml implements Serializable {
    @XStreamAlias("class")
    public MovieSort classes;

    @XStreamAlias("list")
    public Movie list;

    public List<Movie.Video> videoList;
}
