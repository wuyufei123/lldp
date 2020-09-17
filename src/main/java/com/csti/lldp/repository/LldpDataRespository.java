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
     @Select("select  *  from  lldp_data  where create_time > DATE_SUB(NOW(),INTERVAL  10 MINUTE)")
     @Results(id="dataResults", value={
             @Result(property="localDev",   column="local_dev"),
             @Result(property="localDes",  column="local_des"),
             @Result(property="param",  column="param"),
             @Result(property="nextDes",  column="next_des"),
             @Result(property="nextDev", column="next_dev"),
     })
     List<LldpData> findAll();

     @Insert("insert into lldp_data(local_dev,local_des,param,next_des,next_dev)"+"values(#{localDev},#{localDes},#{param},#{nextDes},#{nextDev})")
     int insertLldpData(LldpData lldpData);
}
