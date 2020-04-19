package com.sundi.springbootdemo4.service;

import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationAddBO;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationRequestBO;
import com.sundi.springbootdemo4.bean.royaltyRelation.RoyaltyRelationVO;

import java.util.List;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
public interface IRoyaltyRelationService {

    /**
     * 绑定分账
     *
     * @param relationBO
     * @return
     */
    RoyaltyRelationVO add(RoyaltyRelationAddBO relationBO);

    /**
     * 列表
     *
     * @param requestBO
     * @return
     */
    List<RoyaltyRelationVO> list(RoyaltyRelationRequestBO requestBO);

    /**
     * 删除绑定的分账号,解除绑定
     *
     * @param id
     */
    void remove(Long id);

    /**
     * 设置默认为分账
     *
     * @param id
     */
    void setDefault(Long id);

    /**
     * 取消分账账户
     *
     * @param id
     */
    void cancelDefault(Long id);
}
