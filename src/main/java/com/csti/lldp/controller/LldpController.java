package com.csti.lldp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.csti.lldp.model.LldpData;
import com.csti.lldp.service.LldpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author jinxin
 * @Date 2020/9/16 3:51 下午
 * 实时查询lldp数据
 */
@RestController
@RequestMapping("/lldp")
public class LldpController {
    @Autowired
    LldpService lldpService;
    @GetMapping("/get")
    public String sellect() {
        List<LldpData> data = lldpService.findAll();
        String s = JSONObject.toJSONString(data);
        return s;

    }
}
