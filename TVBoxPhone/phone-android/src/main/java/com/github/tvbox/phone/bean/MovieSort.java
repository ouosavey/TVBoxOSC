package com.github.tvbox.phone.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieSort implements Serializable {

    public List<SortData> sortList = new ArrayList<>();

    public static class SortData implements Serializable {
        public String id;
        public String name;

        public SortData() {}

        public SortData(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}