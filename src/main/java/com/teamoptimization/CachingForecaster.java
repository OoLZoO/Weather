package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CachingForecaster implements ForecasterClient {

    private ForecasterClient delegate;

    private List<String> history = new ArrayList<>();

    private List<MetOfficeForecasterClient.Forecast> cachedForecasts = new ArrayList<>();


    public CachingForecaster(ForecasterClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek dayOfWeek, String place) throws IOException {
        String entry = dayOfWeek.toString() + place;
        if(!history.contains(entry)) {
            history.add(entry);
            cachedForecasts.add(delegate.forecast(dayOfWeek, place));
            return cachedForecasts.get(cachedForecasts.size() - 1);
        }
        return cachedForecasts.get(history.indexOf(entry));
    }
}