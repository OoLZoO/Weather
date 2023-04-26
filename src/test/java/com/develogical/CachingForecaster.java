package com.develogical;

import com.teamoptimization.CachingForecasterClient;
import com.teamoptimization.ForecasterClient;

import java.io.IOException;
import java.time.DayOfWeek;

public class CachingForecaster implements ForecasterClient {

    private ForecasterClient delegate;


    public CachingForecaster(ForecasterClient delegate) {
        this.delegate = delegate;
    }

    @Override
    public CachingForecasterClient.Forecast forecast(DayOfWeek dayOfWeek, String place) throws IOException {
        return delegate.forecast(dayOfWeek, place);
    }
}