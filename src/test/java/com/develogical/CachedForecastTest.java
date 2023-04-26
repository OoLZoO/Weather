package com.develogical;

import com.teamoptimization.CachedForecast;
import com.teamoptimization.CachingForecasterClient;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Instant;

public class CachedForecastTest {

    @Test
    public void testCachedForecastInit() {
        CachedForecast cachedForecast = new CachedForecast();
        assert(cachedForecast != null);
    }

    @Test
    public void testCachedForecastGetForecast() {
        CachedForecast cachedForecast = new CachedForecast();

        cachedForecast.setForecast(DayOfWeek.WEDNESDAY, "Oxford", new CachingForecasterClient.Forecast(0, 16, "Generally clear but cold."));

        CachingForecasterClient.Forecast forecast = cachedForecast.getForecast(DayOfWeek.WEDNESDAY, "Oxford");

        assert(forecast.minTemp == 0);
        assert(forecast.maxTemp == 16);
        assert(forecast.description == "Generally clear but cold.");
    }

    @Test
    public void testCachedForecastReturnsLatestForecast() {
        CachedForecast cachedForecast = new CachedForecast();

        cachedForecast.setForecast(DayOfWeek.WEDNESDAY, "Oxford", new CachingForecasterClient.Forecast(0, 16, "Generally clear but cold."));
        cachedForecast.setForecast(DayOfWeek.WEDNESDAY, "Oxford", new CachingForecasterClient.Forecast(10, 20, "It will be hot."));

        CachingForecasterClient.Forecast forecast = cachedForecast.getForecast(DayOfWeek.WEDNESDAY, "Oxford");

        assert (forecast.minTemp == 10);
        assert (forecast.maxTemp == 20);
        assert (forecast.description == "It will be hot.");
    }
}
