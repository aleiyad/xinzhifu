package com.sundi.springbootdemo4.bean.royaltyRelation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wangyubing
 * @date 2020/4/12
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoyaltyRelationVO {

    private Long id;

    private Integer isActive;

    private Date createTime;

    private Date updateTime;

    private String type;

    private String account;

    private String name;

    private String memo;

    private String out_request_no;

    private Integer isDefault;
}
