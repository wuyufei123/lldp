package com.csti.lldp.cron;

import com.csti.lldp.mapper.LldpUserMapper;
import com.csti.lldp.model.LldpUser;
import com.csti.lldp.service.LldpService;
import com.csti.lldp.utils.LldpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LldpCronCollect {
    @Autowired
    LldpService lldpService;

    @Scheduled(cron = "30 40 * * * ?")
    public void LldpCron() {
        lldpService.dataBaseOfTale();
    }
}
