package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class MetOfficeForecasterClientAdaptor implements ForecasterClient {

    private MetOfficeForecasterClient forecasterClient;

    public MetOfficeForecasterClientAdaptor(MetOfficeForecasterClient forecasterClient) {
        this.forecasterClient = forecasterClient;
    }

    @Override
    public MetOfficeForecasterClient.Forecast forecast(DayOfWeek dayOfWeek, String place) throws IOException {
        int dayNumber = dayOfWeek.getValue();
        LocatorClient.Location location = new LocatorClient().locationOf(place);
        return forecasterClient.forecast(dayNumber, location.latitude, location.longitude);
    }
}
