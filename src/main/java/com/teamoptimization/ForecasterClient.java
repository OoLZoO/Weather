package com.teamoptimization;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;

public interface ForecasterClient {
    MetOfficeForecasterClient.Forecast forecast(DayOfWeek dayOfWeek, String place) throws IOException;
}
