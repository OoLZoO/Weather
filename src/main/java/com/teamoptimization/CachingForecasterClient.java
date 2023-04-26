package com.teamoptimization;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;

public class CachingForecasterClient implements ForecasterClient {

    private CachedForecast cachedForecast;

    public CachingForecasterClient(CachedForecast cachedForecast) {
        this.cachedForecast = cachedForecast;
    }
    @Override
    public Forecast forecast(DayOfWeek dayOfWeek, String place) throws IOException {
        // perform caching of weather data in memory

        if(cachedForecast.getForecast(dayOfWeek, place) != null) {
            return cachedForecast.getForecast(dayOfWeek, place);
        }

        MetOfficeForecasterClient forecasterClient = new MetOfficeForecasterClient();
        LocatorClient locatorClient = new LocatorClient();
        LocatorClient.Location location = locatorClient.locationOf(place);

        MetOfficeForecasterClient.Forecast forecast =
                forecasterClient.forecast(dayOfWeek.getValue(), location.latitude, location.longitude);

        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, dayOfWeek, forecast.minTemp, forecast.maxTemp, forecast.description);



        // return cached weather data


        int minTemp = 0;
        int maxTemp = 0;
        String description = "";

        return new Forecast(0, 16, "Generally clear but cold.");
    }

    public static class Forecast {
        public int minTemp;
        public int maxTemp;
        public String description;

        public Forecast(int minTemp, int maxTemp, String description) {
            this.minTemp = minTemp;
            this.maxTemp = maxTemp;
            this.description = description;
        }

        public Forecast() {
        }

        @Override
        public String toString() {
            return "Forecast(" + minTemp + ", " + maxTemp + ", " + description + ")";
        }
    }


}
