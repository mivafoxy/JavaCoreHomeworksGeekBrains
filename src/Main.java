import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private final static String WeatherURL =  "http://dataservice.accuweather.com/forecasts/v1/daily/5day/295212"; // URL для запроса погоды на 5 дней в СПБ.
    private final static String API_KEY = "?apikey=4uT3CCtP3oRbsLmC8Uvz4WZF5hmcaCun"; // API ключ для запроса. См. гайд, как его получить и вставить.
    private final static String IS_METRIC = "&metric=true";

    public static void main(String[] args) {
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
                    System.out.println(responseContent);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}