package com.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PublicDto<T> {
    private String msg;
    private int code;
    private int total;
    private List<T> data =new ArrayList<>();

    public PublicDto() {
    }

    public PublicDto(String msg, int code, int total, List<T> data) {
        this.msg = msg;
        this.code = code;
        this.total = total;
        this.data = data;
    }
}
