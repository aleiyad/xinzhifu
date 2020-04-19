package com.sundi.springbootdemo4.service;

import com.sundi.springbootdemo4.entity.Nation;
import com.baomidou.mybatisplus.service.IService;
import com.sundi.springbootdemo4.vo.NationVo;

import java.util.List;

/**
 * <p>
 * 城市字典表 服务类
 * </p>
 *
 * @author Mht
 * @since 2019-12-13
 */
public interface INationService extends IService<Nation> {

    List<NationVo> selectList();

}
