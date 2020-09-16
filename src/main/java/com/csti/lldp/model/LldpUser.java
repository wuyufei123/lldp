package com.csti.lldp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  @author: wuyufei
 *  @Date: 2020/9/16 15:41
 *  @Description: lldp_User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LldpUser {
    private String ip;
    private String username;
    private String password;
    private String alias;
}
