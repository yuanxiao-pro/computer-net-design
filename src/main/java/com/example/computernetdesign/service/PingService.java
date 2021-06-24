package com.example.computernetdesign.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.computernetdesign.bean.Ping;
import com.example.computernetdesign.mapper.PingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Wrapper;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class PingService {

    final static Integer TIME_OUT = 5000;
    @Autowired
    PingMapper pingMapper;


    public String ping(Ping ping){
        String result = "";
        try {
            //直接调用系统的ping命令，求要发送的回显请求数和等待每次回复的超时时间（ms）
            Process p= Runtime.getRuntime().exec("ping "+ping.getIp()+" -n 1 -w "+TIME_OUT);// runtime.exec("ping "+ping.getIp()+" -n 1 -w "+TIME_OUT);
            if(p == null)
                return result;

            //从键盘接受一行输入的ip地址或IP段
            BufferedReader buff=new BufferedReader(new InputStreamReader(p.getInputStream()));
            Pattern pattern= Pattern.compile("(\\d+)ms\\s+TTL=(\\d+)",Pattern.CASE_INSENSITIVE); // 通过 compile 静态方法实例化 Pattern 对象 ，构造用来匹配耗时和TTl的正则表达式
            String line;
            Matcher m;
            while((line = buff.readLine()) != null){
                m = pattern.matcher(line);
//                System.out.println(line);

                if(m.find()){
                    ping.setUsageTime(Integer.parseInt(m.group(1))); //指定目标占位符
                    ping.setTtl(Integer.parseInt(m.group(2)));
                    result += line;
//                    ping.setConnected(1);
//                    System.out.println(result);
                }
                //不论是否能ping到目标主机，都拼接结果字符串
                result += line;
            }
            ping.setPong(result);
            if (result.length() != 0 && result != null && !result.contains("请求找不到主机"))
                ping.setConnected(1);

            pingMapper.insert(ping);
            return result;

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }


    public List<Ping> getPingList(){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("ping_id");
        List<Ping> list = pingMapper.selectList(wrapper);
        return list;
    }

}
