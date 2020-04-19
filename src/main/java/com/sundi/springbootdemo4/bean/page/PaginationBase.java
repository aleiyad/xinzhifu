package com.sundi.springbootdemo4.bean.page;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangyubing
 * @date 2020/4/9
 */
@NoArgsConstructor
@Data
public class PaginationBase {

    private Integer pageSize;

    private Integer pageNum;

}
