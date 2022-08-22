package com.lpc.wechatpush.service.impl;

import com.lpc.wechatpush.entity.Weather;
import com.lpc.wechatpush.service.IPushService;
import com.lpc.wechatpush.utils.CaiHongPiUtils;
import com.lpc.wechatpush.utils.JiNianRiUtils;
import com.lpc.wechatpush.utils.WeatherUtils;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author byu_rself
 * @date 2022/8/22 17:09
 */
@Service
public class IPushServiceImpl implements IPushService {
    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;

    @Override
    public void push() {
        // 1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(appSecret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        // 2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("userId")
                .templateId("templateId")
                .build();
        // 3,如果是正式版发送模版消息，这里需要配置你的信息
        Weather weather = WeatherUtils.getWeather();
        templateMessage.addData(new WxMpTemplateData("riqi", weather.getDate() + "  " + weather.getWeek(), "#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi", weather.getText_now(), "#165eaf"));
        templateMessage.addData(new WxMpTemplateData("low", weather.getLow() + "", "#a4c9dd"));
        templateMessage.addData(new WxMpTemplateData("high", weather.getHigh() + "", "#FF6347"));
        templateMessage.addData(new WxMpTemplateData("caihongpi", CaiHongPiUtils.getCaiHongPi(), "#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai", JiNianRiUtils.getLianAi() + "", "#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri", JiNianRiUtils.getBirthdayLkx() + "", "#f09199"));

        String beizhu = "cc❤xx";
        if (JiNianRiUtils.getBirthdayLkx() == 0) {
            beizhu = "老婆生日快乐！！！";
        }
        if (JiNianRiUtils.getLianAi() % 365 == 0) {
            beizhu = "今天是恋爱" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }

        if (JiNianRiUtils.getBirthdayLpc() == 0) {
            beizhu = "今天是cc的生日，嘿嘿！";
        } else if (JiNianRiUtils.getBirthdayLpc() <= 14) {
            beizhu = "离cc生日还有" + JiNianRiUtils.getBirthdayLpc() + "天啦！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu", beizhu, "#FF0000"));

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
