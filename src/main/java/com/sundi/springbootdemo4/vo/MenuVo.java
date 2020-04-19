package com.sundi.springbootdemo4.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo {

    private int id;
    private String label;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private List<MenuVo> children;
}
