package com.csti.lldp.controller;

import com.csti.lldp.mapper.LldpUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    LldpUserMapper lldpUserMapper;
   /* @RequestMapping("test")
    public LldpUser re(){
      return  lldpUserMapper.lldpUserSelect();
    }*/
}
