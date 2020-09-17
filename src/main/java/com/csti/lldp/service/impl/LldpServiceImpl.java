package com.csti.lldp.service.impl;

import com.csti.lldp.mapper.LldpUserMapper;
import com.csti.lldp.model.LldpUser;
import com.csti.lldp.model.LldpData;
import com.csti.lldp.repository.LldpDataRespository;
import com.csti.lldp.service.LldpService;
import com.csti.lldp.utils.LldpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

/**
 * @Author jinxin
 * @Date 2020/9/16 3:53 下午
 */
@Service
public class LldpServiceImpl  implements LldpService {
    @Autowired
    LldpDataRespository lldpDataRespository;
    @Autowired
    LldpUtil lldpUtil;
    @Autowired
    LldpUserMapper lldpUserMapper;
    @Override
    public List findAll() {
        List<LldpData> dataList = lldpDataRespository.findAll();
        return dataList;
    }



    /**
     * @param localDev
     * @param ip
     * @param username
     * @param password
     * @param passList
     * @param outList
     * @Description 循环遍历所有设备
     * @Author wuyufei
     * @Date 2020/9/16 16:38
     * @Param * @param nextDev
     * @Return java.lang.String
     * @Exception
     */
    @Override
    public String multiRound(String nextDev, String localDev, String ip, String username, String password, List<String> passList, List<String> outList) {
        String lineFormat;
        ArrayList<String> lineList;
        Map<String, String> map = new HashMap<>();
        //获取回显内容
        lineList = lldpUtil.getInfo(ip, username, password);
        //结果用local设备名拼接
        for (int i = 0; i < lineList.size(); i++) {
            map = lldpUtil.format(lineList.get(i));
            lineFormat = map.get("lineFormat");
            outList.add(localDev + " " + lineFormat);
        }
        for (int i = 0; i < lineList.size(); i++) {
            map = lldpUtil.format(lineList.get(i));
            //对端端口
            nextDev = map.get("nextDev");
           /* switch (nextDev) {
                case "Sw1":
                    ip = "10.1.81.1";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "Sw2":
                    ip = "10.1.81.2";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "SW3":
                    ip = "10.1.81.3";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "SW4":
                    ip = "10.1.81.4";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "SW5":
                    ip = "10.1.81.5";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "SW6":
                    ip = "10.1.81.6";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "SW7":
                    ip = "10.1.81.7";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "Sw8":
                    ip = "10.1.81.8";
                    username = "jsict@default";
                    password = "jsict";
                    break;
                case "sw9":
                    ip = "10.1.81.9";
                    username = "jsict";
                    password = "jsict";
                    break;
                case "sw10":
                    ip = "10.1.81.10";
                    username = "jsict";
                    password = "jsict";
                    break;
                default:
                    break;

            }*/
            //查询所有设备信息
            List<LldpUser> ListUser = lldpUserMapper.lldpUserSelectAll();
            for (LldpUser user : ListUser) {
                //判断对端端口是否对应本段端口
                if (nextDev.equals(user.getAlias())) {
                    //遍历
                    if (!nextDev.equals("") && !passList.contains(nextDev)) {
                        if (nextDev.contains("sw") | nextDev.contains("SW") | nextDev.contains("Sw") | nextDev.contains("sW")) {
                            passList.add(nextDev);
                            localDev = nextDev;
                            //递归查询
                            multiRound(nextDev, localDev, user.getIp(), user.getUsername(), user.getPassword(), passList, outList);
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }
}
