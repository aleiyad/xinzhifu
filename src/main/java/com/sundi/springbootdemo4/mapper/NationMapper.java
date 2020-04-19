package com.sundi.springbootdemo4.mapper;

import com.sundi.springbootdemo4.entity.Nation;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sundi.springbootdemo4.vo.NationVo;

import java.util.List;

/**
 * <p>
 * 城市字典表 Mapper 接口
 * </p>
 *
 * @author Mht
 * @since 2019-12-13
 */
public interface NationMapper extends BaseMapper<Nation> {

    List<NationVo> selectList();

}
