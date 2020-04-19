package com.sundi.springbootdemo4;

import com.sundi.springbootdemo4.entity.TGoods;
import com.sundi.springbootdemo4.entity.TOrder;
import com.sundi.springbootdemo4.service.ITGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@SpringBootTest(value = "classpath://application.properties")
@Slf4j
class Sundibootdemo4ApplicationTests {

    @Autowired
    private ITGoodsService itGoodsService;

    /**
     * 测试发布商品方法
     */
    @Test
    void contextLoads() {
        TGoods goods = new TGoods();
        goods.setName("测试商品");
        goods.setDescription("测试描述");
        goods.setPrice(new BigDecimal(100));
        goods.setCreated("2019-10-11");
        //发布商品
        itGoodsService.insert(goods);

        log.info("添加成功");

    }

    /**
     * 测试下单方法
     */
    @Test
    void testOrder(){
        TOrder tOrder = new TOrder();

    }

}
