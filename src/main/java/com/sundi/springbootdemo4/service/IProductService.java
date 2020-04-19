package com.sundi.springbootdemo4.service;



import com.sundi.springbootdemo4.bean.product.ProductBO;
import com.sundi.springbootdemo4.bean.product.ProductListRequestBO;
import com.sundi.springbootdemo4.bean.product.ProductVO;

import java.io.IOException;
import java.util.List;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
public interface IProductService {

    ProductVO save(ProductBO productBo);

    List<ProductVO> list(ProductListRequestBO requestBO);

    String qrCode(Long productId) throws Exception;
}
