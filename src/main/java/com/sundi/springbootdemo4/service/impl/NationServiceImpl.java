package com.sundi.springbootdemo4.service.impl;

import com.sundi.springbootdemo4.entity.Nation;
import com.sundi.springbootdemo4.mapper.NationMapper;
import com.sundi.springbootdemo4.service.INationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sundi.springbootdemo4.vo.NationVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 城市字典表 服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-12-13
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

    @Override
    public List<NationVo> selectList() {
        return this.baseMapper.selectList();
    }
}
