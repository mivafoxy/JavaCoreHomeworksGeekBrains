import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private final static String WeatherURL =  "http://dataservice.accuweather.com/forecasts/v1/daily/5day/294021"; // URL для запроса погоды на 5 дней в СПБ.
    private final static String API_KEY = "?apikey=ТУТ_НУЖНО_ВСТАВИТЬ_КЛЮЧ_API"; // API ключ для запроса. См. гайд, как его получить и вставить.

    public static void main(String[] args) {
        try {
            URL weatherUrl = new URL(WeatherURL+API_KEY); // Сформировали URL для запроса к серверу.
            HttpURLConnection urlConnection = (HttpURLConnection) weatherUrl.openConnection(); // К серверу постучались.
            if (urlConnection.getResponseCode() == 200) { // getResponseCode отправляет запрос к серверу по указанному нами URL, который по факту является GET запросом.
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