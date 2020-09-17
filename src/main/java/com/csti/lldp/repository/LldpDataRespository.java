package com.csti.lldp.repository;

import com.alibaba.fastjson.JSONObject;
import com.csti.lldp.model.LldpData;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author jinxin
 * @Date 2020/9/16 3:56 下午
 */
@Mapper
@Repository
public interface LldpDataRespository {
     @Select("select  *  from  lldp_data")
     @Results(id="dataResults", value={
             @Result(property="localDev",   column="local_dev"),
             @Result(property="localDes",  column="local_des"),
             @Result(property="param",  column="param"),
             @Result(property="nextDes",  column="next_des"),
             @Result(property="nextDev", column="next_dev"),
     })
     List<LldpData> findAll();
}
