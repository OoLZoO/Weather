package com.develogical;

import com.teamoptimization.CachingForecaster;
import com.teamoptimization.ForecasterClient;
import com.teamoptimization.MetOfficeForecasterClient;
import org.junit.Test;

import java.io.IOException;
import java.time.DayOfWeek;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CachingForecasterTest {

    @Test
    public void getsForecastFromDelegateIfNeverSeenBefore() throws IOException {
        ForecasterClient delegate = mock(ForecasterClient.class);
        MetOfficeForecasterClient.Forecast forecast = new MetOfficeForecasterClient.Forecast(0, 16, "Generally clear but cold.");
        given(delegate.forecast(DayOfWeek.WEDNESDAY, "Oxford")).willReturn(forecast);

        CachingForecaster cachingForecaster = new CachingForecaster(delegate);

        MetOfficeForecasterClient.Forecast actual = cachingForecaster.forecast(DayOfWeek.WEDNESDAY, "Oxford");

        assertThat(actual, equalTo(forecast));
        verify(delegate).forecast(DayOfWeek.WEDNESDAY, "Oxford");
    }

    @Test
    public void getsForecastFromDelegateWhenSeenBefore() throws IOException {
        ForecasterClient delegate = mock(ForecasterClient.class);
        MetOfficeForecasterClient.Forecast forecast = new MetOfficeForecasterClient.Forecast(0, 16, "Generally clear but cold.");
        given(delegate.forecast(DayOfWeek.WEDNESDAY, "Oxford")).willReturn(forecast);

        CachingForecaster cachingForecaster = new CachingForecaster(delegate);

        cachingForecaster.forecast(DayOfWeek.WEDNESDAY, "Oxford");
        MetOfficeForecasterClient.Forecast actual = cachingForecaster.forecast(DayOfWeek.WEDNESDAY, "Oxford");

        verify(delegate, times(1)).forecast(DayOfWeek.WEDNESDAY, "Oxford");
        assertThat(actual, equalTo(forecast));
    }
}
