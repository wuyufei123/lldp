package com.csti.lldp.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test {
    //解析txt转换为csv
    public static void main(String[] args) {
        //获取文件路径
        String filePath ="D:\\cr\\222.92.224.1.txt";
        //调用
        readTxtFile(filePath);
    }

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath){
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    //截取
                    String[] line=lineTxt.split("\\^");
                    if(line.length!=0) {
                        //获取端口名
                        String name = line[2];
                        //流量入
                        String portIn = line[4];
                        //流量出
                        String portOut = line[5];
                        //时间戳
                        String timestamp=line[0].split(":")[1];
                        System.out.println(timestamp);
                        //写入文件（csv）



                    }
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
}
