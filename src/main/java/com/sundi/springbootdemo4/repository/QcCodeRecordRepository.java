package com.sundi.springbootdemo4.repository;

import com.sundi.springbootdemo4.bean.persist.QcCodeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 订单Repository
 *
 * @author wangyubing
 * @date 2020/4/8
 */
@Repository
public interface QcCodeRecordRepository extends JpaRepository<QcCodeRecord, Long>, JpaSpecificationExecutor<QcCodeRecord> {

}

