package com.example.computernetdesign.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingDemo {

    public static void main(String[] args) {
        new PingDemo("123.56.159.50").ping();
    }

    private Runtime runtime;
    private Pattern pattern;
    private Integer ttl;
    private Integer usage;
    private String ip;
    final static Integer TIME_OUT = 5000;

    public PingDemo(String ip){
        this.ip=ip;
        runtime=Runtime.getRuntime();// 将要执行的ping命令,此命令是windows格式的命
        pattern= Pattern.compile("(\\d+)ms\\s+TTL=(\\d+)",Pattern.CASE_INSENSITIVE); // 通过 compile 静态方法实例化 Pattern 对象 ，构造用来匹配耗时和TTl的正则表达式
        ttl=0;
        usage=0;
    }

    public boolean ping(){
        try {
            //直接调用系统的ping命令，求要发送的回显请求数和等待每次回复的超时时间（ms）
            Process p=runtime.exec("ping "+ip+" -n 1 -w "+TIME_OUT);
//            System.out.println("ping "+ip+" -n 1 -w "+TIME_OUT);
//            System.out.println(this.ttl);
//            Process p=runtime.exec("ping "+ip);
            if(p == null)
                return false;

            //从键盘接受一行输入的ip地址或IP段
            BufferedReader buff=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            Matcher m;

            while((line = buff.readLine()) != null){
                System.out.println(line);
                m=pattern.matcher(line);
                if(m.find()){
                    usage=Integer.parseInt(m.group(1)); //指定目标占位符
                    ttl=Integer.parseInt(m.group(2));
                    return true;
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}

