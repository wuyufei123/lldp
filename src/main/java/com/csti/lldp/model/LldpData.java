package com.csti.lldp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Author jinxin
 * @Date 2020/9/16 4:17 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LldpData {
    private String localDev;
    private String localDes;
    private String param;
    private String nextDes;
    private String nextDev;
}

