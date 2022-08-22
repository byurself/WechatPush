package com.lpc.wechatpush.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lpc.wechatpush.entity.Weather;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author byu_rself
 * @date 2022/8/22 17:10
 */
public class WeatherUtils {

    private static final String district_id = "330282";
    private static final String data_type = "all";
    private static final String ak = "ak";

    /**
     * 获取天气
     *
     * @author lpc
     */
    public static Weather getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("district_id", district_id); // 宁波市慈溪市行政代码
        map.put("data_type", data_type);
        map.put("ak", ak);
        String res = restTemplate.getForObject(
                "https://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type={data_type}&ak={ak}",
                String.class,
                map);
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        Weather weather = weathers.get(0);
        weather.setText_now(now.getString("text"));
        weather.setTemp(now.getString("temp"));
        weather.setWind_class(now.getString("wind_class"));
        weather.setWind_dir(now.getString("wind_dir"));
        return weather;
    }
}
