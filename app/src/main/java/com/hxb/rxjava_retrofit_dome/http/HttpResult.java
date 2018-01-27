package com.hxb.rxjava_retrofit_dome.http;

import java.io.Serializable;
import java.util.List;


/**
 * {
    "resultCode": 0,
    "resultMessage": "成功",
    "data": {}
    }
 * @param <T>
 */

public class HttpResult<T> implements Serializable {


    /**
     * count : 10
     * start : 0
     * subjects : []
     * title : 豆瓣电影Top250
     * total : 250
     */

    private int count;
    private int start;
    private String title;
    private int total;
    private List<T> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<T> subjects) {
        this.subjects = subjects;
    }
}