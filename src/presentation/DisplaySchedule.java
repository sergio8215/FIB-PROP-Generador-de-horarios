package src.presentation;

/*
 * DisplaySchedule.java requires no other files.
 */

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.DropMode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
* DisplaySchedule class that shows the schedule with all it's functionalities.
* @author Sergio Mazzariol
 */
public class DisplaySchedule extends JPanel {

    private int daysOfTheWeek = 5;
    private int startHour = 8;
    private int hoursPerDay = 12;
    private static JFrame frame;
    private JTable table;
    private JCheckBox checkValidate;
    private HashMap<String, ArrayList<Vector< String>>> filter;
    private HashMap<String, ArrayList<Vector< String>>> schedule;

    GridBagConstraints gbc = new GridBagConstraints();

    /**
     * DisplaySchedule show the schedule on a table withe the drag and drop function
     * @param ctrlP Control Presenter
     * @param schedule Schedule to show on the table
     */
    public DisplaySchedule(CtrlPresenter ctrlP, HashMap<String, ArrayList<Vector<String>>> schedule) {
        super(new GridBagLayout());
        this.schedule = schedule;
        filter = (HashMap<String, ArrayList<Vector<String>>>) schedule.clone();

        //Create and set up the window.
        frame = new JFrame("DisplaySchedule");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.

        setPreferredSize(new Dimension(900, 500));

        //Create the scroll pane and add the table to it.
        table = new JTable(makeSchedule(schedule));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

        table.setDragEnabled(true);
        table.setDropMode(DropMode.USE_SELECTION);
        table.setTransferHandler(new TransferHelper());
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);


        TableColumn column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(5); //hour column is smaller

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
        sortTable();
        JScrollPane scrollPane = new JScrollPane(table);

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        //CHECKBOX PANEL
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridheight = 1;
        add(getCheckBoxPanel(this.schedule.keySet() ), gbc);

        //SAVE BUTTON
        JButton returnButton = new JButton("Back to START");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: que vuelva a la pestaña de inicio
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(returnButton, gbc);

        //BACK TO START BUTTON
        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ctrlP.saveSchedule();

                } catch (Exception exc) {
                    System.out.println(exc);
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(saveButton, gbc);



        this.setOpaque(true); //content panes must be opaque
        frame.setContentPane(this);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * getCheckBoxPanel creates a panel with 1 checkbox for each subject
     * @param subjects Subjects to create and add a checkbox to the panel
     * @return  panel with all checkbox
     */
    private JPanel getCheckBoxPanel(Set<String> subjects) {

        JPanel panel2 = new JPanel();

        for ( String s:  subjects){
            checkValidate = new JCheckBox(s);
            checkValidate.setSelected(true);
            panel2.add(checkValidate);

            checkValidate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    JCheckBox cb = (JCheckBox) event.getSource();
                    if (!cb.isSelected()) {
                        removeFilter(s);
                    } else {
                        addFilter(s);
                    }
                }
            });
        }
        return panel2;
    }

    /**
     * makeSchedule creates a new table with days, hours, subjects, groups, for the given schedule
     * @param schedule data to show on the table
     * @return a Table with all the subjects on it
     */
    private DefaultTableModel makeSchedule(HashMap<String, ArrayList<Vector<String>>> schedule){

        String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] header = new String[daysOfTheWeek + 1];
        header[0] = "Hour";

        // Initialize days of the week
        for (int i = 0; i < daysOfTheWeek; i++) header[i + 1] = week[i];

        int i;

        Object[][] data = new Object[scheduleSize(schedule.values())][daysOfTheWeek + 1];

        for (ArrayList<Vector<String>> subject : schedule.values()) {

            for (Vector<String> m : subject) {
                i = 0;
                boolean added = false;
                while(!added){
                    // column 0 is equal to my subject hour and my week column it's empty
                    if ( (data[i][0] == null) ||
                            ( ((Integer)data[i][0] == Integer.parseInt(m.get(3)) ) &&
                                    ( data[i][Integer.parseInt(m.get(4))+1] == null ) )){

                        data[i][0] = Integer.parseInt(m.get(3));            // Hour
                        int day = Integer.parseInt(m.get(4));               // Day (ordinal)
                        data[i][day + 1] = m.get(0) + " " + m.get(1) + " " + m.get(2); // Subject name, subgroup, classroom

                        added = true;
                    }
                    i++;
                }
            }
        }

        DefaultTableModel table = new DefaultTableModel(data, header) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        };
        return table;
    }

    /**
     * removeFilter Removes the subject passed by parameter from the schedule
     * @param subject Subject to be remove from the schedule
     */
    private void removeFilter(String subject){
        filter.remove(subject);
        table.setModel(makeSchedule(filter));
        sortTable();
    }

    /**
     * addFilter Adds the subject passed by parameter to the schedule
     * @param subject Subject to be added to the schedule
     */
    private void addFilter(String subject){
        filter.put(subject, schedule.get(subject));
        table.setModel(makeSchedule(filter));
        sortTable();
    }

    /**
     * SortTable sort the table per hours
     */
    private void sortTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndexToSort = 0;
        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }

    /**
     * scheduleSize calculate the size of the schedule
     * @param schedule Schedule to make the size calculation
     * @return the size of the schedule
     */
    private int scheduleSize(Collection<ArrayList<Vector< String>>> schedule) {

        int[][] scheduleSize = new int[hoursPerDay][daysOfTheWeek];
        // initialize my matrix
        for(int i=0; i<hoursPerDay; i++){
            for(int j=0; j<daysOfTheWeek;j++){
                scheduleSize[i][j] = 0;
            }
        }

        // count size per day
        for (ArrayList<Vector<String>> subject : schedule) {
            for (Vector<String> m : subject) {
                scheduleSize[Integer.parseInt(m.get(3))-startHour][Integer.parseInt(m.get(4))]++;  // [Hour][day]
            }
        }

        // search for max
        int acum = 0;
        int[] maximum = new int[hoursPerDay];
        for (int i=0; i<daysOfTheWeek; i++) maximum[i] = 0;
        for (int i=0; i<hoursPerDay;   i++) {
            for (int j = 0; j < daysOfTheWeek; j++) {
                maximum[i] = Math.max(maximum[i], scheduleSize[i][j]);
            }
            acum += maximum[i];
        }
        return acum;
    }


    // Drag and drop table

    /**
     * CellData Class that allow us to get information from one cell
     */
    public class CellData {

        private final Object value;
        private final int col;
        private final JTable table;
        private final int row;

        /**
         * CellData constructor for the class
         * @param source Table to get the data from
         */
        public CellData(JTable source) {
            this.col = source.getSelectedColumn();
            this.row = source.getSelectedRow();
            this.value = source.getValueAt(row, col);
            this.table = source;
        }

        /**
         * getColumn return the actual column number
         * @return the number of col for the cell
         */
        public int getColumn() {
            return col;
        }

        /**
         * getValue return the value of the cell
         * @return the value of the cell
         */
        public Object getValue() {
            return value;
        }

        /**
         * getTable
         * @return this table
         */
        public JTable getTable() {
            return table;
        }

        /**
         * swapValuesWith swaps two different cells
         * @param targetRow target row to move the cell
         * @param targetCol target col to move the cell
         * @return true
         */
        public boolean swapValuesWith(int targetRow, int targetCol) {

            boolean swapped = false;

            //if (targetCol == col) {

                Object exportValue = table.getValueAt(targetRow, targetCol);
                table.setValueAt(value, targetRow, targetCol);
                table.setValueAt(exportValue, row, col);
                swapped = true;

            //}

            return swapped;

        }

    }

    /**
     * DataFlavor class applies the flavor pattern
     */
    public static final DataFlavor CELL_DATA_FLAVOR = createConstant(CellData.class, "application/x-java-celldata");

    /**
     * CellDataTransferable class make the necessary
     */
    public class CellDataTransferable implements Transferable {

        private CellData cellData;

        /**
         * CellDataTransferable constructor of the class
         * @param cellData initialize the variables
         */
        public CellDataTransferable(CellData cellData) {
            this.cellData = cellData;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{CELL_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            boolean supported = false;
            for (DataFlavor available : getTransferDataFlavors()) {
                if (available.equals(flavor)) {
                    supported = true;
                }
            }
            return supported;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return cellData;
        }

    }


    static protected DataFlavor createConstant(Class clazz, String name) {
        try {
            return new DataFlavor(clazz, name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * TransferHelper class, helps to transfer the data from one cell to another
     */
    public class TransferHelper extends TransferHandler {

        private static final long serialVersionUID = 1L;

        /**
         * Empty constructor
         */
        public TransferHelper() {
        }

        /**
         * getSourceActions return the action
         * @param c component
         * @return the action
         */
        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        /**
         * createTransferable creates a new transferable cell
         * @param source source of the movemente
         * @return the cell to transfer
         */
        @Override
        protected Transferable createTransferable(JComponent source) {
            // Create the transferable
            JTable table = (JTable) source;
            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();
            Object value = table.getValueAt(row, col);
            return new CellDataTransferable(new CellData(table));
        }

        /**
         * exportDone empty function
         * @param source
         * @param data
         * @param action
         */
        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
        }

        /**
         * canImport checks if the cell drag and drop it's possible
         * @param support cells of the drag
         * @return true if it's possible to import the cell, false otherwise
         */
        @Override
        public boolean canImport(TransferSupport support) {
            // Reject the import by default...
            boolean canImport = false;
            // Can only import into another JTable
            Component comp = support.getComponent();
            if (comp instanceof JTable) {
                JTable target = (JTable) comp;
                // Need the location where the drop might occur
                DropLocation dl = support.getDropLocation();
                Point dp = dl.getDropPoint();
                // Get the column at the drop point
                int dragColumn = target.columnAtPoint(dp);
                int dragRow = target.rowAtPoint(dp);
                Object destiny = target.getValueAt(dragRow, dragColumn);

                try {
                    // Get the Transferable, we need to check
                    // the constraints
                    Transferable t = support.getTransferable();
                    CellData cd = (CellData) t.getTransferData(CELL_DATA_FLAVOR);
                    // Make sure we're not dropping onto ourselves...
                    if (cd.getTable() == target) {
                        if (dragColumn != 0 && cd.getColumn() != 0){
                            if (destiny != null ) {
                                if ( !cd.value.toString().equalsIgnoreCase(destiny.toString()) ){
                                    String[] to2   = destiny.toString().split(" ");     // 0 Subject, 1 Subgroup, 2 Classroom
                                    String[] from2 = cd.value.toString().split(" ");    // 0 Subject, 1 Subgroup, 2 Classroom



                                    Vector<String> to  = new Vector(Arrays.asList(to2));
                                    Vector<String> from = new Vector(Arrays.asList(from2));

                                    int dayTo = target.columnAtPoint(dp)-1;
                                    int dragR = target.rowAtPoint(dp);
                                    Object hourTo = target.getValueAt( dragR, 0);
                                    to.add(Integer.toString((Integer)hourTo));  // 3 hour
                                    to.add(Integer.toString(dayTo));            // 4 day

                                    int dayFrom   = cd.col-1;
                                    int dragRFrom = cd.row;
                                    Object hourFrom = target.getValueAt( dragRFrom, 0);
                                    to.add(Integer.toString((Integer)hourFrom));    // 3 hour
                                    from.add(Integer.toString(dayFrom));            // 4 day

                                    // TODO JOA necesitas usar los vectores to y from
                                }
                            }
                            canImport = true;
                        }
                    }
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
            return canImport;
        }

        /**
         * importData moves the into the cell
         * @param support cell to be imported
         * @return true on success
         */
        @Override
        public boolean importData(TransferSupport support) {
            // Import failed for some reason...
            boolean imported = false;
            // Only import into JTables...
            Component comp = support.getComponent();
            if (comp instanceof JTable) {
                JTable target = (JTable) comp;
                // Need to know where we are importing to...
                DropLocation dl = support.getDropLocation();
                Point dp = dl.getDropPoint();
                int dropCol = target.columnAtPoint(dp);
                int dropRow = target.rowAtPoint(dp);
                try {
                    // Get the Transferable at the heart of it all
                    Transferable t = support.getTransferable();
                    CellData cd = (CellData) t.getTransferData(CELL_DATA_FLAVOR);
                    if (cd.getTable() == target) {
                        if (cd.swapValuesWith(dropRow, dropCol)) {
                            imported = true;
                        }
                    }
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
            return imported;
        }
    }

}
