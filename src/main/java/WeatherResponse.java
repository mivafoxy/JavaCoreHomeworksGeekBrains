import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.Arrays;

public class WeatherResponse {
    private final DailyForecast[] dailyForecasts;

    public WeatherResponse(JsonObject jsonObject) {
        JsonArray jsonDailyForecastsArray = jsonObject.getJsonArray("DailyForecasts");
        dailyForecasts = new DailyForecast[jsonDailyForecastsArray.size()];
        for (int forecast = 0; forecast < dailyForecasts.length; forecast++) {
            JsonObject jsonForecast = jsonDailyForecastsArray.getJsonObject(forecast);
            DailyForecast dailyForecast = new DailyForecast(jsonForecast);
            dailyForecasts[forecast] = dailyForecast;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Погода на 5 дней в СПБ: \n");

        for (DailyForecast dailyForecast : dailyForecasts) {
            stringBuilder.append(dailyForecast.toString() + "\n");
        }

        return stringBuilder.toString();
    }
}
