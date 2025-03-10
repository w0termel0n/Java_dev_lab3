import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public static List<String[]> readAndParse(String fileName) {
        List<String[]> data = new ArrayList<String[]>(); // collection for data
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // data stream that reads every line in the csv file
            String line;
            while((line = br.readLine()) != null) {
                // adds lists of the values from each line to data
                data.add(line.split(","));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
