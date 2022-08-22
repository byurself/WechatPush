package com.lpc.wechatpush;

import com.lpc.wechatpush.entity.Weather;
import com.lpc.wechatpush.utils.CaiHongPiUtils;
import com.lpc.wechatpush.utils.WeatherUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WechatPushApplicationTests {

	@Test
	void contextLoads() {
		// Weather weather = WeatherUtils.getWeather();
		String caiHongPi = CaiHongPiUtils.getCaiHongPi();
		System.out.println(caiHongPi);
	}

}
