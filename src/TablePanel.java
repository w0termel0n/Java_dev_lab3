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
        setPreferredSize(new Dimension(1400, 500));

        filter = new JPanel();
        filter.setBackground(Color.GRAY);
        filter.setPreferredSize(new Dimension(1380, 80));

        table = new JTable();
        table.setModel(data);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1380, 490));
        table.setFillsViewportHeight(true);

        for(int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.sizeWidthToFit();
        }

        add(filter);
        add(scrollPane);
    }
}
