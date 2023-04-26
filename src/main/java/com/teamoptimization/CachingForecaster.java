package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class CachingForecaster implements ForecasterClient {

    private ForecasterClient delegate;


    public CachingForecaster(ForecasterClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek dayOfWeek, String place) throws IOException {
        return delegate.forecast(dayOfWeek, place);
    }
}