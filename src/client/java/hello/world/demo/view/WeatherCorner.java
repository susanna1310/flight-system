package hello.world.demo.view;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import hello.world.demo.model.Flight;

public class WeatherCorner {
    private String key = "f0920c3ea3519246ffbf017ad0efb87c";
    private String city;
    private Weather weatherJson;

    OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("f0920c3ea3519246ffbf017ad0efb87c");

    private String cityOfDestination(String city){
        return city;
    }

    public Weather weatherMethod(String city){
       return weatherJson = openWeatherClient
            .currentWeather()
            .single()
            .byCityName(city)
            .language(Language.ENGLISH)
            .unitSystem(UnitSystem.METRIC)
            .retrieve()
            .asJava();
    }

    public String getTemperature(String city) {
        String b = weatherMethod(city).getTemperature().getFeelsLike().toString();
        String a = weatherMethod(city).getTemperature().getUnit();
        String c = weatherMethod(city).getWeatherState().getDescription();
        return b + a + ", " + c;
    }

    public WeatherCorner(String city) {
        this.city = city;
    }
}
