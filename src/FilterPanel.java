import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class FilterPanel extends JPanel {
    private JComboBox<String> typeOneFilter;
    private JComboBox<String> typeTwoFilter;
    private JComboBox<String> genFilter;
    private JComboBox<String> legendaryFilter;

    private TablePanel tablePanel;
    private List<String[]> data;

    public FilterPanel(String[][] tableData, TablePanel tablePanel) {
        this.tablePanel = tablePanel;
        data = Arrays.asList(tableData);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(BorderFactory.createTitledBorder("Filters"));

        typeOneFilter = new JComboBox<>();
        typeOneFilter.addItem("Any");
        data.stream()
                .map(to -> to[2]).distinct().sorted()
                .forEach(typeOneFilter::addItem);
        add(new JLabel("Type:"));
        add(typeOneFilter);

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
        add(new JLabel("    Second type (if applicable):"));
        add(typeTwoFilter);

        genFilter = new JComboBox<>();
        genFilter.addItem("Any");
        data.stream()
                .map(g -> g[11]).distinct().sorted()
                .forEach(genFilter::addItem);
        add(new JLabel("    Generation:"));
        add(genFilter);

        legendaryFilter = new JComboBox<>();
        legendaryFilter.addItem("Either");
        data.stream()
                .map(l -> l[12]).distinct().sorted()
                .forEach(legendaryFilter::addItem);
        add(new JLabel("    Is a legendary:"));
        add(legendaryFilter);

        ActionListener filterListener = e -> applyFilters();
        typeOneFilter.addActionListener(filterListener);
        typeTwoFilter.addActionListener(filterListener);
        genFilter.addActionListener(filterListener);
        legendaryFilter.addActionListener(filterListener);
    }

    private void applyFilters() {
        String selectedTypeOne = (String) typeOneFilter.getSelectedItem();
        String selectedTypeTwo = (String) typeTwoFilter.getSelectedItem();
        String selectedGen = (String) genFilter.getSelectedItem();
        String selectedLegendary = (String) legendaryFilter.getSelectedItem();

        List<String[]> filteredList = data.stream()
                .filter(p -> selectedTypeOne.equals("Any") || p[2].equals(selectedTypeOne))
                .filter(p -> selectedTypeTwo.equals("Any") ||
                        (p[3].equals(selectedTypeTwo) && !p[3].equals("None")) || p[3].isEmpty())
                .filter(p -> selectedGen.equals("Any") || p[11].equals(selectedGen))
                .filter(p -> selectedLegendary.equals("Either") || p[12].equals(selectedLegendary))
                .toList();
        String[][] filtered = filteredList.toArray(new String[filteredList.size()][]);

        tablePanel.updateTable(filtered);
    }
}
