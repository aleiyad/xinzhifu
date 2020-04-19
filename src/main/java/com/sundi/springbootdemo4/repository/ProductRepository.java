package com.sundi.springbootdemo4.repository;


import com.sundi.springbootdemo4.bean.persist.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 产品Repository
 *
 * @author wangyubing
 * @date 2020/4/8
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}

