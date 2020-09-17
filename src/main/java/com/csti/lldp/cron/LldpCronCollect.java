package com.csti.lldp.cron;
import com.csti.lldp.service.LldpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

@Repository
public class LldpCronCollect {
    @Autowired
    LldpService lldpService;

    @Scheduled(cron = "0 10,20,30,40,50,0 * * * ?")
    public void LldpCron() {
        lldpService.dataBaseOfTale();
    }
}
