import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class window {

    private JTextField inputSzukaj;
    private JButton szukajButton;
    private JButton wyczyśćButton;
    private JTable bookTable;
    private JPanel mainPanel;
    private JMenu fileMenu;
    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem endItem;
    private JMenuBar menuBar;
    private JTextField titleInput;
    private JTextField authorInput;
    private JTextField rokInput;
    private JButton addButton;
    private JButton rentButton;
    private JButton deleteButton;
    private JScrollPane tablePane;
    private JFrame frame;
    private JLabel statusLabel;
    public static boolean czyZwrot;




    public window() {
        initialize();

        final DefaultTableModel[] model = {(DefaultTableModel) bookTable.getModel()};
        List<Book> books = new ArrayList<>();
        final int[] selectedRow = new int[1];
        selectedRow[0] = -1;


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (titleInput.getText().isEmpty() || authorInput.getText().isEmpty() || rokInput.getText().isEmpty()){
                        komunikat dialog = new komunikat("Musisz wypełnic wszystkie pola");
                        dialog.pack();
                        dialog.setVisible(true);
                } else {
                    Book b1 = new Book(titleInput.getText(), authorInput.getText(),
                                       Integer.parseInt(rokInput.getText()));
                    books.add(b1);
                    model[0].addRow(new Object[]{b1.name, b1.author, b1.rok, "Dostępna"});
                }
            }
        });


        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zwrotWypozycz dialog = new zwrotWypozycz();
                dialog.pack();
                dialog.setVisible(true);

                if(selectedRow[0] >= 0){
                    if (zwrotWypozycz.czyZwrot){
                        System.out.println("zwrot" + selectedRow[0]);
                        Book temp = books.get(selectedRow[0]);
                        temp.status = "Dostępna";
                        books.set(selectedRow[0], temp);
                        update(selectedRow[0], temp);
                    } else {
                        System.out.println("Wypo" + selectedRow[0]);
                        Book temp = books.get(selectedRow[0]);
                        temp.status = "Wypożyczona";
                        books.set(selectedRow[0], temp);
                        update(selectedRow[0], temp);
                    }
                }
            }
        });


        bookTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                selectedRow[0] = bookTable.getSelectedRow();
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedRow[0] >= 0){
                    books.remove(selectedRow[0]);
                    ((DefaultTableModel)bookTable.getModel()).removeRow(selectedRow[0]);
                }
            }
        });
    }

    public void initialize(){
        frame = new JFrame("Biblioteka z wypożyczeniami i wyszukiwaniem");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        menuBar = new JMenuBar();

        fileMenu = new JMenu("Plik");

        openItem = new JMenuItem("Otwórz");
        saveItem = new JMenuItem("Zapisz");
        JSeparator separ = new JSeparator();
        endItem = new JMenuItem("Zakończ");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(separ);
        fileMenu.add(endItem);

        menuBar.add(fileMenu);

        frame.setJMenuBar(menuBar);


        frame.pack();
        frame.setVisible(true);
        frame.setSize(1250,600);
    }

    public void update(int index, Book newValue){
        final DefaultTableModel[] model = {(DefaultTableModel) bookTable.getModel()};
        model[0].setValueAt(newValue.name, index, 0);
        model[0].setValueAt(newValue.author, index, 1);
        model[0].setValueAt(newValue.rok, index, 2);
        model[0].setValueAt(newValue.status, index, 3);
    }

    public void main(String[] args){


    }

    private void createUIComponents() {
        bookTable = new JTable(new DefaultTableModel(new Object[]{"Tytuł", "Autor", "Rok", "Status"}, 0));

    }
}
