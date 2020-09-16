package com.csti.lldp.mapper;

import com.csti.lldp.model.LldpUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  @author: wuyufei
 *  @Date: 2020/9/16 15:47
 *  @Description: LldpUser映射
 */
@Mapper
public interface LldpUserMapper {
    //查询LldpUser所有设备
    List<LldpUser> lldpUserSelectAll();
    //根据ip查询
    LldpUser lldpUserSelect(String ip);
}
