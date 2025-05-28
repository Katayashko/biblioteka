import javax.swing.*;
import java.awt.event.*;

public class zwrotWypozycz extends JDialog {
    private JPanel contentPane;
    private JButton buttonReturn;
    private JButton buttonRent;
    public static boolean czyZwrot;

    public zwrotWypozycz() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonReturn);

        buttonReturn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonRent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() { //return
        czyZwrot = true;
        dispose();
    }

    private void onCancel() { //rent
        czyZwrot = false;
        dispose();
    }

}
