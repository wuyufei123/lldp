package com.csti.lldp.controller;

import com.csti.lldp.mapper.LldpUserMapper;
import com.csti.lldp.service.LldpService;
import com.csti.lldp.utils.LldpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestController {
    @Autowired
    LldpUserMapper lldpUserMapper;
    @Autowired
    LldpService lldpService;
    @Autowired
    LldpUtil lldpUtil;
    @RequestMapping("test")
    public void re(){
        ArrayList<String> outList=new ArrayList<>();
        ArrayList<String> passList=new ArrayList<>();
        //输入第一台设备名
        String nextdev="Sw1";
        String localDev="Sw1";
        String ip="10.1.81.1";
        String username="jsict@default";
        String password="jsict";
        passList.add(nextdev);

        lldpService.multiRound(nextdev,localDev,ip,username,password,passList,outList);
        lldpUtil.distinct(outList);
        for(int i=0;i<outList.size();i++){
            System.out.println(outList.get(i));
        }
    }
}
