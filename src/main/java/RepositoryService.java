import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryService {
    public static final String DB_URL = "jdbc:sqlite:homework.db";
    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()
        ){
            String query =
                "CREATE TABLE IF NOT EXISTS weather " +
                    "(id integer PRIMARY KEY, " +
                    "localDate text NOT NULL, " +
                    "dayText text NOT NULL, " +
                    "nightText text NOT NULL, " +
                    "minTemperature double NOT NULL, " +
                    "maxTemperature double NOT NULL);";
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void putWeatherIntoDb(DailyForecast forecast) {
        final String query =
            "INSERT INTO weather(localDate, dayText, nightText, minTemperature, maxTemperature)" +
                " VALUES(?,?,?,?,?);";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, forecast.getDate());
            preparedStatement.setString(2, forecast.getDayTextDescription());
            preparedStatement.setString(3, forecast.getNightTextDescription());
            preparedStatement.setDouble(4, forecast.getMinimumTemperature());
            preparedStatement.setDouble(5, forecast.getMaximumTemperature());
            preparedStatement.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<DailyForecast> loadDailyForecasts() {
        final String query = "SELECT * FROM weather";
        List<DailyForecast> dailyForecasts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                DailyForecast dailyForecast =
                    new DailyForecast(
                        resultSet.getString("localDate"),
                        resultSet.getDouble("minTemperature"),
                        resultSet.getDouble("maxTemperature"),
                        resultSet.getString("dayText"),
                        resultSet.getString("nightText"));
                dailyForecasts.add(dailyForecast);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dailyForecasts;
    }
}
