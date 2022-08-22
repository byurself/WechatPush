package com.lpc.wechatpush.entity;

import lombok.Data;

/**
 * @author byu_rself
 * @date 2022/8/22 17:01
 */
@Data
public class Weather {
    /** 白天天气 */
    private String text_day;

    /** 夜晚天气 */
    private String text_night;

    /** 最高温 */
    private String high;

    /** 最低温 */
    private String low;

    /** 白天风力 */
    private String wc_day;

    /** 白天风向 */
    private String wd_day;

    /** 夜晚风力 */
    private String wc_night;

    /** 夜晚风向 */
    private String wd_night;

    /** 日期 */
    private String date;

    /** 周几 */
    private String week;

    /** 当前天气 */
    private String text_now;

    /** 当前温度 */
    private String temp;

    /** 当前风力大小 */
    private String wind_class;

    /** 当前风向 */
    private String wind_dir;
}
