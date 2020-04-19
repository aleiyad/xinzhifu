package com.sundi.springbootdemo4.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeRoyaltyRelationBindResponse;
import com.alipay.api.response.AlipayTradeRoyaltyRelationUnbindResponse;
import com.sundi.springbootdemo4.bean.persist.RoyaltyRelation;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationAddBO;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationRequestBO;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationVO;
import com.sundi.springbootdemo4.common.constant.MysqlConstant;
import com.sundi.springbootdemo4.common.enums.ResponseEnum;
import com.sundi.springbootdemo4.common.enums.RoyaltyRelationIsDefaultEnum;
import com.sundi.springbootdemo4.common.enums.RoyaltyRelationTypeEnum;
import com.sundi.springbootdemo4.common.exceptions.GlobalException;
import com.sundi.springbootdemo4.common.util.CheckParametersUtil;
import com.sundi.springbootdemo4.common.util.GenerateNumUtil;
import com.sundi.springbootdemo4.repository.RoyaltyRelationRepository;
import com.sundi.springbootdemo4.service.AlipayAuthService;
import com.sundi.springbootdemo4.service.IRoyaltyRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
@Service
public class RoyaltyRelationServiceImpl implements IRoyaltyRelationService {

    @Autowired
    RoyaltyRelationRepository relationRepository;
    @Autowired
    AlipayAuthService alipayAuthService;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public RoyaltyRelationVO add(RoyaltyRelationAddBO relationBO) {
        try {
            CheckParametersUtil.check(relationBO);
            CheckParametersUtil.check(relationBO.getAccount());
            CheckParametersUtil.check(relationBO.getMemo());
            CheckParametersUtil.check(relationBO.getName());
            RoyaltyRelation royaltyRelation = new RoyaltyRelation();
            BeanUtils.copyProperties(relationBO, royaltyRelation);

            String outRequestNo = GenerateNumUtil.outRequestNo();
            AlipayTradeRoyaltyRelationBindResponse alipayTradeRoyaltyRelationBindResponse = alipayAuthService.alipayTradeRoyaltyRelationBind(RoyaltyRelationTypeEnum.LOGIN_NAME.getType(), royaltyRelation.getAccount(), relationBO.getName(), relationBO.getMemo(), outRequestNo);
            if (alipayTradeRoyaltyRelationBindResponse.isSuccess()) {
                royaltyRelation.setCreateTime(new Date());
                royaltyRelation.setUpdateTime(new Date());
                royaltyRelation.setIsActive(MysqlConstant.IS_ACTIVE);
                royaltyRelation.setOut_request_no(outRequestNo);
                royaltyRelation.setIsDefault(RoyaltyRelationIsDefaultEnum.NO.getCode());
                royaltyRelation.setType(RoyaltyRelationTypeEnum.LOGIN_NAME.getType());
                RoyaltyRelation save = relationRepository.save(royaltyRelation);
                RoyaltyRelationVO relationVO = new RoyaltyRelationVO();
                BeanUtils.copyProperties(save, relationVO);
                return relationVO;
            } else {
                throw new GlobalException(ResponseEnum.ERROR.getCode(), alipayTradeRoyaltyRelationBindResponse.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new GlobalException(ResponseEnum.ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public List<RoyaltyRelationVO> list(RoyaltyRelationRequestBO requestBO) {
//        Pageable pageable = PageUtil.init(requestBO, Sort.by(Sort.Direction.DESC, "id"));
        Specification<RoyaltyRelation> querySpeci = (Specification<RoyaltyRelation>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), MysqlConstant.IS_ACTIVE));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        List<RoyaltyRelation> all = relationRepository.findAll(querySpeci,Sort.by(Sort.Direction.DESC,"id"));
        List<RoyaltyRelationVO> vos = new ArrayList<>();
        for (RoyaltyRelation royaltyRelation : all) {
            RoyaltyRelationVO relationVO = new RoyaltyRelationVO();
            BeanUtils.copyProperties(royaltyRelation, relationVO);
            vos.add(relationVO);
        }
        return vos;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void remove(Long id) {
        try {
            // 解除绑定
            RoyaltyRelation royaltyRelation = relationRepository.findById(id).orElse(null);
            if (royaltyRelation == null) {
                throw new GlobalException(ResponseEnum.BAD_REQUEST);
            }
            String outRequestNo = GenerateNumUtil.outRequestNo();
            AlipayTradeRoyaltyRelationUnbindResponse alipayTradeRoyaltyRelationUnbindResponse = null;

            alipayTradeRoyaltyRelationUnbindResponse = alipayAuthService.alipayTradeRoyaltyRelationUnbind(RoyaltyRelationTypeEnum.LOGIN_NAME.getType(), royaltyRelation.getAccount(), royaltyRelation.getName(), outRequestNo);
            if (alipayTradeRoyaltyRelationUnbindResponse.isSuccess()) {
                relationRepository.updateIsActie(MysqlConstant.NOT_IS_ACTIVE, id);
            } else {
                throw new GlobalException(ResponseEnum.ERROR.getCode(), alipayTradeRoyaltyRelationUnbindResponse.getSubMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new GlobalException(ResponseEnum.ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public void setDefault(Long id) {
        relationRepository.updateIsDefault(RoyaltyRelationIsDefaultEnum.YES.getCode(), id);
    }

    @Override
    public void cancelDefault(Long id) {
        relationRepository.updateIsDefault(RoyaltyRelationIsDefaultEnum.NO.getCode(), id);
    }
}
