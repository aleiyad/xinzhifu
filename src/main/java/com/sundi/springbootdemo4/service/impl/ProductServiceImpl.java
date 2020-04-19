package com.sundi.springbootdemo4.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayFundAuthOrderVoucherCreateResponse;
import com.google.zxing.WriterException;
import com.sundi.springbootdemo4.bean.persist.Product;
import com.sundi.springbootdemo4.bean.persist.QcCodeRecord;
import com.sundi.springbootdemo4.bean.product.ProductBO;
import com.sundi.springbootdemo4.bean.product.ProductListRequestBO;
import com.sundi.springbootdemo4.bean.product.ProductVO;
import com.sundi.springbootdemo4.common.constant.MysqlConstant;
import com.sundi.springbootdemo4.common.enums.OrderStatusEnum;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.common.exceptions.GlobalException;
import com.sundi.springbootdemo4.common.util.*;
import com.sundi.springbootdemo4.repository.ProductRepository;
import com.sundi.springbootdemo4.repository.QcCodeRecordRepository;
import com.sundi.springbootdemo4.service.AlipayAuthService;
import com.sundi.springbootdemo4.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyubing
 * @date 2020/4/8
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QcCodeRecordRepository qcCodeRecordRepository;

    @Autowired
    private AlipayAuthService alipayAuthService;

    @Override
    public ProductVO save(ProductBO productBo) {
        CheckParametersUtil.check(productBo);
        CheckParametersUtil.check(productBo.getName());
        CheckParametersUtil.check(productBo.getPrice());
        Product product = new Product();
        BeanUtils.copyProperties(productBo, product);
        product.setAmount(MoneyUtil.of(productBo.getPrice()));
        product.setIsActive(MysqlConstant.IS_ACTIVE);
        Product save = productRepository.save(product);
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(save, productVO);
        productVO.setAmount(save.getAmount().getAmount().toPlainString());
        return productVO;
    }

    @Override
    public List<ProductVO> list(ProductListRequestBO requestBO) {
//        Pageable pageable = PageUtil.init(requestBO, Sort.by(Sort.Direction.DESC, "id"));
        Specification<Product> querySpeci = (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), MysqlConstant.IS_ACTIVE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<Product> all= productRepository.findAll(querySpeci, Sort.by(Sort.Direction.DESC, "id"));
        List<ProductVO> list = new ArrayList<>();
        for (Product product : all) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVO.setAmount(product.getAmount().getAmount().toPlainString());
            list.add(productVO);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public String qrCode(Long productId) throws IOException, WriterException {
        try {
            // 获取产品id
            Product product = productRepository.findById(productId).orElse(null);
            if (product == null) {
                throw new GlobalException(ResponseEnum.BAD_REQUEST);
            }
            // 订单
            String orderNum = GenerateNumUtil.orderId();
            // 流水
            String operationNo = GenerateNumUtil.operationNo();
            // 产品名称
            String name = product.getName();
            // 产品价格
            double price = product.getAmount().getAmount().doubleValue();

            // 获取二维码
            AlipayFundAuthOrderVoucherCreateResponse response = alipayAuthService.fundAuthOrderVoucherCreate(orderNum, operationNo, name, price);
            if (response.isSuccess()) {
                // 生成订单
                QcCodeRecord qcCodeRecord = new QcCodeRecord();
                qcCodeRecord.setIsActive(MysqlConstant.IS_ACTIVE);
                qcCodeRecord.setOrderNo(orderNum);
                qcCodeRecord.setOperationNo(operationNo);
                qcCodeRecord.setAmount(product.getAmount());
                qcCodeRecord.setStatus(OrderStatusEnum.INIT.getStatus());
                qcCodeRecord.setCreateTime(new Date());
                qcCodeRecord.setUpdateTime(new Date());
                qcCodeRecordRepository.save(qcCodeRecord);
                log.info("调用成功");
//                // 生成二维码并返回base64字符串
//                new Thread(
//                        new Runnable() {
//                            @Override
//                            public void run() {
//                                long start = System.currentTimeMillis();
//                                while (true) {
//                                    long end = System.currentTimeMillis();
//                                    if (end - start >= 30000) {
//                                        // 设置订单为关闭
//                                        alipayAuthService.
//                                        break;
//                                    }
//                                    // 查询
//                                    try {
//                                        AlipayFundAuthOperationDetailQueryResponse alipayFundAuthOperationDetailQueryResponse = alipayAuthService.fundAuthQuery(orderNum, operationNo);
//                                        if (alipayFundAuthOperationDetailQueryResponse.isSuccess()){
//
//                                        }
//                                    } catch (AlipayApiException e) {
//                                        e.printStackTrace();
//                                        log.error("请求失败");
//                                    }
//
//                                }
//                            }
//                        }
//                ).start();
                //return Base64Util.dataToBase64(GenerateQCCodeUtil.getQRCodeImage(response.getCodeValue()));
                return response.getCodeValue();
            } else {
                throw new GlobalException(ResponseEnum.ERROR.getCode(), "生成二维码失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new GlobalException(ResponseEnum.ERROR.getCode(), e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;

        }
    }

}
