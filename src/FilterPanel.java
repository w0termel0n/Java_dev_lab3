import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterPanel extends JPanel {
    private JComboBox<String> typeOneFilter;
    private JComboBox<String> typeTwoFilter;
    private JComboBox<String> genFilter;

    private TablePanel tablePanel;
    private List<String[]> data;

    public FilterPanel(String[][] tableData, TablePanel tablePanel) {
        this.tablePanel = tablePanel;
        data = Arrays.asList(tableData);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createTitledBorder("Filters"));

        // Combo box to pick the country
        typeOneFilter = new JComboBox<>();
        typeOneFilter.addItem("Any");
        data.stream()
                .map(to -> to[2]).distinct().sorted()
                .forEach(typeOneFilter::addItem);
        add(new JLabel("Type:"));
        add(typeOneFilter);

        // Combo box to pick the birth year
        typeTwoFilter = new JComboBox<>();
        typeTwoFilter.addItem("Any");
        data.stream()
                .map(tt -> {
                    if(tt[3].isEmpty()){
                        return "None";
                    }
                    return tt[3];
                }).distinct().sorted()
                .forEach(typeTwoFilter::addItem);
        add(new JLabel("Second type (if applicable):"));
        add(typeTwoFilter);

        // Combo box to pick gender
        genFilter = new JComboBox<>();
        genFilter.addItem("Any");
        data.stream()
                .map(g -> g[11]).distinct().sorted()
                .forEach(genFilter::addItem);
        add(new JLabel("Generation:"));
        add(genFilter);

        // When a filter changes, we wanna re-filter the data
        ActionListener filterListener = e -> applyFilters();
        typeOneFilter.addActionListener(filterListener);
        typeTwoFilter.addActionListener(filterListener);
        genFilter.addActionListener(filterListener);
    }

    // This applies the selected filters to the list of artists
    private void applyFilters() {
        String selectedTypeOne = (String) typeOneFilter.getSelectedItem();
        String selectedTypeTwo = (String) typeTwoFilter.getSelectedItem();
        String selectedGen = (String) genFilter.getSelectedItem();

        List<String[]> filteredList = data.stream()
                .filter(a -> selectedTypeOne.equals("Any") || a[2].equals(selectedTypeOne))
                .filter(a -> selectedTypeTwo.equals("Any") ||
                        (a[3].equals(selectedTypeTwo) && !a[3].equals("None")) || a[3].isEmpty())
                .filter(a -> selectedGen.equals("Any") || a[11].equals(selectedGen))
                .toList();
        String[][] filtered = filteredList.toArray(new String[filteredList.size()][]);

        // Push the new list to the table and other panels
        tablePanel.updateTable(filtered);
        //statsPanel.updateStats(filtered);
        //chartPanel.updateChart(filtered);
        //detailPanel.clearDetails(); // clear in case someone selected an artist before filtering
    }
}
