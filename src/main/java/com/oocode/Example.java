package com.oocode;

import com.teamoptimization.CachingForecaster;
import com.teamoptimization.ForecasterClient;
import com.teamoptimization.MetOfficeForecasterClient;
import com.teamoptimization.MetOfficeForecasterClientAdaptor;

import java.io.IOException;
import java.time.DayOfWeek;

public class Example {
    public static void main(String[] args) throws IOException {
        args = new String[]{"Monday", "London"};
        if (args.length != 2) {
            throw new RuntimeException("Must specify Day and Place");
        }
        ForecasterClient forecasterClient = new CachingForecaster(new MetOfficeForecasterClientAdaptor(new MetOfficeForecasterClient()));
        forecast(args[0], args[1], forecasterClient);
        forecast(args[0], args[1], forecasterClient);
        forecast(args[0], args[1], forecasterClient);
    }

    private static void forecast(String day, String place, ForecasterClient forecasterClient) throws IOException {
        MetOfficeForecasterClient.Forecast forecast =
                forecasterClient.forecast(DayOfWeek.valueOf(day.toUpperCase()), place);
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);
    }
}
