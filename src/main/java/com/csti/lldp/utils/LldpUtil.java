package com.csti.lldp.utils;

import com.csti.lldp.model.TelnetBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wuyufei
 * @Date: 2020/9/16 16:21
 * @Description: LldpUtil工具类
 */
@Component
public class LldpUtil {
    /**
     * @Description 对结果去重
     * @Author wuyufei
     * @Date 2020/9/16 16:21
     * @Param * @param outList
     * @Return java.util.ArrayList<java.lang.String>
     * @Exception
     */
    public static ArrayList<String> distinct(ArrayList<String> outList) {
        String[][] out = new String[outList.size()][5];
        String[] temp;
        List<String> removeList = new ArrayList<>();
        int i, j;
        int n, m;
        //分割存入数组
        for (i = 0; i < outList.size(); i++) {
            temp = outList.get(i).split("\\s+");
            for (j = 0; j < temp.length; j++) {
                out[i][j] = temp[j];
            }
        }
        System.out.println(out);
        //双重遍历去重
        for (n = 0; n < out.length; n++) {
            for (m = n; m < out.length; m++) {
                if (out[n][0].equals(out[m][4]) && out[n][1].equals(out[m][3])) {
                    removeList.add(outList.get(n));
                } else {
                    continue;
                }
            }
        }
        outList.removeAll(removeList);
        return outList;
    }

    /**
     * @author: wuyufei
     * @Date: 2020/9/16 16:22
     * @Description: 格式化每行的输出：截取下一设备名，分割端口与设备，去除多余空格
     */
    public static Map<String, String> format(String line) {
        String lineFormat;
        String nextDev;
        String temp; //存储形如Ten-GigabitEthernet2/0/48CLL_core的子串
        int flag; //分割设备名的定位
        Map<String, String> map = new HashMap<>();
        lineFormat = line.replace("Ten-GigabitEthernet", "XGigabitEthernet");
        lineFormat = lineFormat.replace("10GE", "XGigabitEthernet");
        lineFormat = lineFormat.replace("XGE", "XGigabitEthernet");
        lineFormat = lineFormat.replaceAll("\\s{1,}", " ");
        //匹配整行格式，分为3空格和4空格
        Pattern fourSpace = Pattern.compile("^(\\S+\\s+\\S+\\s+\\S+\\s+)(\\S+)$");
        Pattern threeSpace = Pattern.compile("^(\\S+\\s+\\S+\\s+)(\\S+)$");
        Matcher fourSpaceMatch = fourSpace.matcher(lineFormat);
        Matcher threeSpaceMatch = threeSpace.matcher(lineFormat);
        //4空格的匹配最后一段为下一设备名，3空格的根据匹配分割截取最后的字符
        Pattern p = Pattern.compile("(.*\\d+/\\d+/\\d+)(.*)");
        if (fourSpaceMatch.find()) {
            nextDev = fourSpaceMatch.group(2);
        } else if (threeSpaceMatch.find()) {
            temp = threeSpaceMatch.group(2);
            Matcher m = p.matcher(temp);
            if (m.find()) {
                nextDev = m.group(2);
                flag = lineFormat.length() - nextDev.length();
                lineFormat = lineFormat.substring(0, flag) + " " + nextDev;
            } else {
                nextDev = "";
            }
        } else {
            nextDev = "";
        }
        map.put("nextDev", nextDev);
        map.put("lineFormat", lineFormat);

        return map;

    }

    /**
     * @author: wuyufei
     * @Date: 2020/9/16 16:22
     * @Description: 截取邻居设备名
     */
    public static String getDev(String line) {
        String dev = "";
        Pattern p1 = Pattern.compile("(.*)(\\d[a-zA-Z].*)");
        Pattern p2 = Pattern.compile("(Sw|sw|sW|SW)");
        Matcher m1 = p1.matcher(line);
        Matcher m2 = p2.matcher(line);
        if (m1.find()) {
            dev = m1.group(2).substring(1);
        } else if (m2.find()) {
            dev = line.substring(m2.start(), line.length());
        } else {
            dev = "";
        }

        System.out.println(dev);


        return dev;
    }

    /**
     * @author: wuyufei
     * @Date: 2020/9/16 16:22
     * @Description: 调用telnet登录方法，获取回显内容并按行存入list
     */
    public static ArrayList<String> getInfo(String ip, String username, String password) {
        TelnetBean telnetBean = new TelnetBean();
        String info = telnetBean.collectRountCsco(ip, username, password);
        ArrayList<String> lineList = new ArrayList<>();
        String[] str = info.split("\\r?\\n");
        for (int i = 0; i < str.length; i++) {
            lineList.add(str[i]);
        }
        return lineList;

    }
}
