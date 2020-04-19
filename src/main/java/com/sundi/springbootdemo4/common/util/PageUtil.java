package com.sundi.springbootdemo4.common.util;

import com.sundi.springbootdemo4.bean.page.PaginationBase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * 构建分页工具
 *
 * @author wangyubing
 * @date 2020/4/9
 */
public class PageUtil {

    private static int pageSize = 20;

    private static int pageNum = 1;

    /**
     * 注意此字段为实体类对应字段,非数据库对应字段
     */
    private static final String ORDER = "id";

    public static Pageable init(PaginationBase paginationBase) {
        return init(paginationBase, Sort.by(Sort.Direction.ASC, ORDER));
    }

    public static Pageable init(PaginationBase paginationBase, Sort sort) {
        if (paginationBase != null) {
            if (paginationBase.getPageNum() != null && paginationBase.getPageNum() > 0) {
                pageNum = paginationBase.getPageNum();
            }
            if (paginationBase.getPageSize() != null && paginationBase.getPageSize() > 0) {
                pageSize = paginationBase.getPageSize();
            }
        }
        PageRequest of;
        if (sort != null) {
            of = PageRequest.of(pageNum - 1, pageSize, sort);
        } else {
            of = PageRequest.of(pageNum - 1, pageSize);
        }
        return of;
    }
}
