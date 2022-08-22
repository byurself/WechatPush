package com.lpc.wechatpush.controller;

import com.lpc.wechatpush.service.IPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author byu_rself
 * @date 2022/8/22 17:04
 */
@RestController
public class PushController {

    @Autowired
    private IPushService pushService;

    @Scheduled(cron = "0 30 7 * * ?")
    public void push() {
        pushService.push();
    }
}
