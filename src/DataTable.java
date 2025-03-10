import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DataTable extends JPanel {

    TablePanel table;

    public DataTable() {
        // gets the data and separates the first row from the rest
        List<String[]> data = FileParser.readAndParse("pokemon.csv");
        String[] headers = data.getFirst();
        String[][] tableData = data.subList(1, data.size()).toArray(new String[0][]);

        // creates the table with the data and the formatting from TablePanel
        setPreferredSize(new Dimension(1800, 1200));
        table = new TablePanel(new DefaultTableModel(tableData, headers));
        add(table);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DataTable");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new DataTable());
        frame.pack();
        frame.setVisible(true);
    }
}
