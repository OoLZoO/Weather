package com.develogical;

import com.teamoptimization.CachingForecasterClient;
import com.teamoptimization.ForecasterClient;
import org.junit.Test;

import java.io.IOException;
import java.time.DayOfWeek;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CachingForecasterTest {
    @Test
    public void getsForecastFromDelegateIfNeverSeenBefore() throws IOException {
        ForecasterClient delegate = mock(ForecasterClient.class);
        CachingForecasterClient.Forecast forecast = new CachingForecasterClient.Forecast(0, 16, "Generally clear but cold.");
        given(delegate.forecast(DayOfWeek.WEDNESDAY, "Oxford")).willReturn(forecast);

        CachingForecaster cachingForecaster = new CachingForecaster(delegate);

        CachingForecasterClient.Forecast actual = cachingForecaster.forecast(DayOfWeek.WEDNESDAY, "Oxford");

        assertThat(actual, equalTo(forecast));
        verify(delegate).forecast(DayOfWeek.WEDNESDAY, "Oxford");
    }
}
