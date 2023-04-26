package com.develogical;

import com.teamoptimization.CachingForecasterClient;
import org.junit.Test;

import java.io.IOException;
import java.time.DayOfWeek;

public class CachingForecasterClientTest {

    @Test
    public void testCachingForecasterClientInit() {
        CachingForecasterClient.Forecast forecast = new CachingForecasterClient.Forecast(1, 2, "test");
        assert(forecast.minTemp == 1);
        assert(forecast.maxTemp == 2);
        assert(forecast.description == "test");
    }

    @Test
    public void testCachingForecasterWednesdayOxford() throws IOException {
//        CachingForecasterClient cachingForecasterClient = new CachingForecasterClient();
//        CachingForecasterClient.Forecast forecast = cachingForecasterClient.forecast(DayOfWeek.WEDNESDAY, "Oxford");
//
//        assert(forecast.minTemp == 0);
//        assert(forecast.maxTemp == 16);
//        assert(forecast.description == "Generally clear but cold.");
    }
}
