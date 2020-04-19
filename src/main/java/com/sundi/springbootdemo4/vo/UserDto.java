package com.sundi.springbootdemo4.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDto extends Page{

    /**
     * hrID
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住宅电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    private Integer enabled;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private String userface;

    private String remark;

    private Integer[] value;

    private MultipartFile[] files;

}
