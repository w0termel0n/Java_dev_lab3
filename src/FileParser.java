import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<String[]> readAndParse(String fileName) {
        List<String[]> data = new ArrayList<String[]>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
