package com.example.computernetdesign.controller;

import com.example.computernetdesign.bean.Ping;
import com.example.computernetdesign.bean.Result;
import com.example.computernetdesign.service.PingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
//@RequestMapping(value = "/api")
public class PingController {

    @Autowired
    PingService pingService;

    @RequestMapping(value = "/api/ping", method = RequestMethod.POST)
    public Result ping2AimIp(@RequestParam("ip") String ip){
        Result result = new Result();
        Ping ping = new Ping(ip);
        try{
            String pong = pingService.ping(ping);
            result.setValue(pong);

            if (pong.length() != 0 && pong != null && !pong.contains("请求找不到主机")){
                result.setStatus(100);
                result.setMsg("success");
            }else{
                result.setStatus(200);
                result.setMsg("failed");
            }


        } catch (Exception e){
            e.printStackTrace();
            result.setStatus(600);
            result.setMsg("error");
        }
        return result;
    }

    @RequestMapping(value = "/api/getAllPings", method = RequestMethod.POST)
    public Result getAllPings(){
        Result result = new Result();
        try{
            List<Ping> pingList = pingService.getPingList();
            result.setValue(pingList);

            if (pingList.size() != 0 && pingList != null){
                result.setStatus(100);
                result.setMsg("success");
            }else{
                result.setStatus(200);
                result.setMsg("failed");
            }


        } catch (Exception e){
            e.printStackTrace();
            result.setStatus(600);
            result.setMsg("error");
        }
        return result;



    }
}
