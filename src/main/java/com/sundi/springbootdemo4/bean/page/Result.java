package com.sundi.springbootdemo4.bean.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Result {

    private long pageNum;

    private long pages;

    private long total;

    private Object data;
}
