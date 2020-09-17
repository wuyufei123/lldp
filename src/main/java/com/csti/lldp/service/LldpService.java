package com.csti.lldp.service;

import com.csti.lldp.model.LldpData;

import java.util.List;

/**
 * @Author jinxin
 * @Date 2020/9/16 3:53 下午
 */
public interface LldpService {
    //循环遍历设备
    String multiRound(String nextDev, String localDev, String ip, String username, String password, List<String> passList, List<String> outList);
    //查询lldp数据
    List<LldpData> findAll();
}
