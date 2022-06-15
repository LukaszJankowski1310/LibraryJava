package app;

import javax.swing.*;

public class UserInputDialog {
    JTextField name = new JTextField();
    JTextField surname = new JTextField();
    private final JFrame frame;

    private final String inputTitle;

    public UserInputDialog(JFrame frame, String inputTitle) {
        this.inputTitle = inputTitle;
        this.frame = frame;
    }

    Object[] message = {
            "Imię:", name,
            "Nazwisko:", surname,
    };

    public int inputUser() {
        return JOptionPane.showConfirmDialog(frame,
                message, this.inputTitle, JOptionPane.OK_CANCEL_OPTION);

        //   return option;
    }

}