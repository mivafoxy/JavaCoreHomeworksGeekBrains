import com.sun.deploy.util.ArrayUtil;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Main {
    private final static String WeatherURL =  "http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212"; // URL для запроса погоды на 5 дней в СПБ.
    private final static String API_KEY = "?apikey=4uT3CCtP3oRbsLmC8Uvz4WZF5hmcaCun"; // API ключ для запроса. См. гайд, как его получить и вставить.
    private final static String IS_METRIC = "&metric=true";

    public static void main(String[] args) {
        String forecastJson = load5DayForecastOrNull();

        if (forecastJson != null) {
            // Получили данные с сервера
            StringReader forecastJsonReader = new StringReader(forecastJson);
            JsonReader jsonReader = Json.createReader(forecastJsonReader);
            JsonObject weatherResponseJson = jsonReader.readObject();
            WeatherResponse weatherResponse = new WeatherResponse(weatherResponseJson);
            System.out.println(weatherResponse);

            // Записали в БД
            RepositoryService.createTable();

            for (DailyForecast dailyForecast : weatherResponse.getDailyForecasts()) {
                RepositoryService.putWeatherIntoDb(dailyForecast);
            }

            List<DailyForecast> dailyForecasts = RepositoryService.loadDailyForecasts();
            System.out.println(dailyForecasts);
        } else {
            System.out.println("Не удалось прочитать данные с сервера.");
        }
    }

    public static String load5DayForecastOrNull() {
        try {
            // Сформировали URL для запроса к серверу.
            URL weatherUrl = new URL(WeatherURL+API_KEY + IS_METRIC);
            // К серверу постучались.
            HttpURLConnection urlConnection = (HttpURLConnection) weatherUrl.openConnection();
            // getResponseCode отправляет запрос к серверу по указанному нами URL, который по факту является GET запросом.
            if (urlConnection.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) { // Как было на предыдущих занятиях, чтобы считать данные из
                    StringBuilder responseContent = new StringBuilder();                                                  // сети, необходимо открыть Stream для их чтения.
                    String line = "";                                                                                     // Тут мы используем для работы с сетью BufferedReader.
                    while ((line = reader.readLine()) != null) { // Считываем данные от сервера до конца (тут нет EOF, как в случае с фалами. Если данных нет от сервера, то метод readLine()
                        responseContent.append(line);            // вернет null.
                    }
                    return responseContent.toString();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}