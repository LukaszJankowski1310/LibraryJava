package app;

import javax.swing.*;

public class BookInputDialog {
    JTextField title = new JTextField();
    JTextField author = new JTextField();
    JTextField amount = new JTextField();
    private final String inputTitle;
    private final JFrame frame;

    public BookInputDialog(JFrame frame, String inputTitle) {
        this.inputTitle = inputTitle;
        this.frame = frame;
    }

    Object[] message = {
            "Tytuł:", title,
            "Autor:", author,
            "Ilość:", amount,
    };

    public int inputBook() {
        return JOptionPane.showConfirmDialog(frame,
                message, this.inputTitle, JOptionPane.OK_CANCEL_OPTION);

        //   return option;
    }


}












