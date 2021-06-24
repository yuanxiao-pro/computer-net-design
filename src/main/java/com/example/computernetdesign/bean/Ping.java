package com.example.computernetdesign.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.regex.Pattern;

@Data
public class Ping implements Serializable {
//    private Runtime runtime;
    @TableId(type = IdType.AUTO)
    private Integer pingId;
//    private Pattern pattern;
    private Integer ttl;
    private Integer usageTime;
    private String ip;
    private String pong; //结果
    private Integer connected; //1-ping通
    public Ping(){

    }


    public Ping(String ip){
        this.ip=ip;
//        runtime=Runtime.getRuntime();// 将要执行的ping命令,此命令是windows格式的命
//        pattern= Pattern.compile("(\\d+)ms\\s+TTL=(\\d+)",Pattern.CASE_INSENSITIVE); // 通过 compile 静态方法实例化 Pattern 对象 ，构造用来匹配耗时和TTl的正则表达式
        ttl=0;
        usageTime=0;
        pingId = Integer.MAX_VALUE;
        pong = "";
        connected = 0;
    }

}
