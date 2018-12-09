package com.example.elina.weatherapp.pojoClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cities {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("count")
    @Expose
    private Integer count;

    @SerializedName("list")
    @Expose
    private java.util.List<Info> list = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<Info> getList() {
        return list;
    }

    public void setList(java.util.List<Info> list) {
        this.list = list;
    }
}
