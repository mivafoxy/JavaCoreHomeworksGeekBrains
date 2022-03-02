import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/*
1. Реализовать сохранение данных в csv файл;
2. Реализовать загрузку данных из csv файла. Файл читается целиком.
Структура csv файла:
| Строка заголовок с набором столбцов |
| Набор строк с целочисленными значениями |
| * Разделитель между столбцами - символ точка с запятой (;) |

Пример:
Value 1;Value 2;Value 3
100;200;123
300,400,500
Для хранения данных использовать класс вида:
public class AppData {
  private String[] header;
  private int[][] data;

 // ...
}
Если выполняется save(AppData data), то старые данные в файле полностью перезаписываются.
 */
public class Main {
    public static final String FILE_NAME = "table.csv";

    public static void main(String[] args) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            byte[] csvData = newData().toString().getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(csvData);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static AppData newData() {
        AppData appData = new AppData();
        String[] headers = new String[] { "title 1", "title 2", "title 3" };
        appData.init(headers);
        return appData;
    }
}