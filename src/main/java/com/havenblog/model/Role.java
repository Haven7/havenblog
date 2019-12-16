package com.havenblog.model;

import lombok.Data;

@Data
public class Role {
    private Long id;
    private String name;

    public Role() {
    }

    public Long getId() {

        return id;
    }
    public Role(Long id, String name) {

        this.id = id;
        this.name = name;
    }
}
