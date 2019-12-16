package com.havenblog.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Category {
    private Long id;
    private String cateName;
    private Timestamp date;

    public Category() {
    }


}
