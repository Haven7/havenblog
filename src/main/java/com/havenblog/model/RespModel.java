package com.havenblog.model;

import lombok.Data;

@Data
public class RespModel {
    private String status;
    private String msg;

    public RespModel() {
    }

    public RespModel(String status, String msg) {

        this.status = status;
        this.msg = msg;
    }
}