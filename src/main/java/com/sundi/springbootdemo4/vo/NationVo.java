package com.sundi.springbootdemo4.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

@Data
public class NationVo {

    private int value;

    private String label;

    //如果查询出来的字段为空字段,就让他不显示
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private List<NationVo> children;
}
