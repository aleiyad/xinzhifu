package com.sundi.springbootdemo4.config.springsecurity;

public enum RoleName {
    ROLE_LOGIN("ROLE_LOGIN",1),
    ROLE_FX("ROLE_FX",2),
    ROLE_NONE("ROLE_NONE",3),
    ;

    private String name;
    private int index;

    RoleName(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
