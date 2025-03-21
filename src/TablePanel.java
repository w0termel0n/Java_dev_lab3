import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {

    private JScrollPane scrollPane;
    private JTable table;
    private JPanel filter;

    private String[] headers;
    private String[][] tableData;
    private DefaultTableModel data;

    public TablePanel(String[] headers, String[][] tableData) {
        this.headers = headers;
        this.tableData = tableData;
        data = new DefaultTableModel(tableData, headers);

        setBackground(Color.PINK);
        setPreferredSize(new Dimension(1400, 600));

        filter = new FilterPanel(tableData, this);
        filter.setPreferredSize(new Dimension(1200, 80));

        table = new JTable();
        table.setModel(data);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 490));
        table.setFillsViewportHeight(true);

        add(filter);
        add(scrollPane);
    }

    public void updateTable(String[][] tableData) {
        this.tableData = tableData;
        data.setRowCount(0);
        for (String[] pokemon : tableData) {
            data.addRow(pokemon);
        }
    }
}
