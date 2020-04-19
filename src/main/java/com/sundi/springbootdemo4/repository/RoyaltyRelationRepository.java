package com.sundi.springbootdemo4.repository;

import com.sundi.springbootdemo4.bean.persist.RoyaltyRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangyubing
 * @date 2020/4/12
 */
@Repository
public interface RoyaltyRelationRepository extends JpaRepository<RoyaltyRelation, Long>, JpaSpecificationExecutor<RoyaltyRelation> {
    @Modifying
    @Transactional
    @Query(value = "update zfb_royalty_relation as a set a.is_active = ?1 where a.id = ?2", nativeQuery = true)
    int updateIsActie(int isActive, Long id);

    @Modifying
    @Transactional
    @Query(value = "update zfb_royalty_relation as a set a.is_default = ?1 where a.id = ?2", nativeQuery = true)
    int updateIsDefault(int isDefault, Long id);
}
