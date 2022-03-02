import java.util.Arrays;
import java.util.Random;

public class AppData {
    private String[] headers;
    private int[][] table;

    public void init(String[] headers) {
        this.headers = headers;
        Random random = new Random();
        table = new int[headers.length][headers.length];

        for (int row = 0; row < headers.length; row++) {
            for (int column = 0; column < headers.length; column++) {
                table[row][column]  = random.nextInt(10);
            }
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (String word : headers) {
            result += word + ";";
        }

        result += "\n";

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                result += table[row][column] + ";";
            }
            result += "\n";
        }
        return result;
    }
}
