package com.teamoptimization;

import com.teamoptimization.CachingForecasterClient.Forecast;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.HashMap;

public class CachedForecast {

    private Instant currentTime;
    private HashMap<String, Forecast> forecastMap;

    public CachedForecast() {
        currentTime = Clock.systemUTC().instant();
        forecastMap = new HashMap<>();
    }

    public Forecast getForecast(DayOfWeek dayOfWeek, String place) {

        String key = dayOfWeek + "_" + place;

        return forecastMap.get(key);
    }

    public void setForecast(DayOfWeek dayOfWeek, String place, Forecast forecast) {

        String key = dayOfWeek + "_" + place;

        forecastMap.put(key, forecast);
    }
}
