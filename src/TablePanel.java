import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class TablePanel extends JPanel {

    JScrollPane scrollPane;
    JTable table;
    JPanel filter;

    public TablePanel(TableModel data) {
        setBackground(Color.PINK);
        setPreferredSize(new Dimension(1400, 600));

        filter = new JPanel(); // placeholder for where the filter dropdown will be
        filter.setBackground(Color.LIGHT_GRAY);
        filter.setPreferredSize(new Dimension(1200, 80));

        table = new JTable();
        table.setModel(data);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 490));
        table.setFillsViewportHeight(true);

        add(filter);
        add(scrollPane);
    }
}
