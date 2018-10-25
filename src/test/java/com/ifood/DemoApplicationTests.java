package com.ifood;

import static org.assertj.core.api.Assertions.assertThat;

import com.ifood.controllers.About;
import com.ifood.controllers.WeatherController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private WeatherController weatherController;

	@Autowired
	private DemoApplication demoApplication;

    @Autowired
    private About aboutController;

	@Test
	public void contexLoads() throws Exception {
		assertThat(demoApplication).isNotNull();
	}

    @Test
    public void verifiesTheReturnOfAbout() throws Exception {
        ResponseEntity aboutInformation = aboutController.About();
        assertThat(aboutInformation.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(aboutInformation.getBody()).isNotNull();
    }

    @Test
    public void verifiesTheAvailabilityOfTheOpenWeatherMap() throws Exception {
        ResponseEntity cityWeather= weatherController.GetCityWeather("London");
        assertThat(cityWeather.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(cityWeather.getBody()).isNotNull();
    }
}
