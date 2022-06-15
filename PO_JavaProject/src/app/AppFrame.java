package app;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AppFrame extends JFrame  {

  private final JFrame frame = new JFrame("Biblioteka");
  private final  JPanel panelContainer = new JPanel();

    // main panel
    {
        private final JPanel mainPanel = new JPanel();


        private final JButton displayStateLibraryBtn = new JButton("STAN BIBLIOTEKI"); // Stan biblioteki, ilosc ksiazek, dodawnaie usuwanie itp.
        private final JButton loginUserBtn = new JButton("ZALOGUJ CZYTELNIKA"); // JOption panel
        private final JButton showUsersBtn = new JButton("UŻYTKOWNICY");  // lista uzytkownikow


        // State library panel

        private final JPanel libraryState = new JPanel();

        private final JPanel libraryStateButtonsPanel = new JPanel();

        private final JButton addBookBtn = new JButton("DODAJ KSIĄŻKĘ");
        private final JButton deleteBookBtn = new JButton("USUŃ KSIĄŻKĘ");
        private final JButton updateBookBtn = new JButton("AKTUALIZUJ");
        private final JButton backToMainPanel1 = new JButton("COFNIJ");

        private final JPanel libraryStateTablePanel = new JPanel();
        private final JTable booksTable = new JTable();
        private final DefaultTableModel booksTableModel = new DefaultTableModel();


        // UserPanel


        private final JPanel userPanel = new JPanel();

        private final JPanel userPanelButtons = new JPanel();
        private final JButton borrowBookBtn = new JButton("WYPOŻYCZ KSIĄŻKĘ");
        private final JButton returnBookBtn = new JButton("ODDAJ KSIĄŻKĘ");
        private final JButton backToMainPanel2 = new JButton("COFNIJ");

        private final JPanel userPanelLabels = new JPanel();
        private final JLabel nameLabel = new JLabel("Imię: ");
        private final JLabel surnameLabel = new JLabel("Nazwisko: ");

        private final JPanel userPanelTable = new JPanel();

        private final JTable borrowedBooksTable = new JTable();
        private final DefaultTableModel borrowedBooksModel = new DefaultTableModel();


        // all users panel

        private final JPanel allUsersPanel = new JPanel();
        private final JPanel allUsersButtonsPanel = new JPanel();
        private final JButton addUserBtn = new JButton("DODAJ UŻYTKOWNIKA");
        private final JButton deleteUserBtn = new JButton("USUŃ UŻYTKOWNIKA");
        private final JButton updateUserBtn = new JButton("AKTUALIZUJ");
        private final JButton backToMainPanel3 = new JButton("COFINJ");
        private final JPanel allUsersTablePanel = new JPanel();
        private final JTable usersTable = new JTable();
        private final DefaultTableModel usersTableModel = new DefaultTableModel();


        // borrow book panel

        private final JPanel borrowBookPanel = new JPanel();

        private final JPanel borrowBookPanelButtons = new JPanel();

        private final JButton borrowBookBtn2 = new JButton("WYPOŻYCZ");
        private final JButton backToUserPanelBtn = new JButton("COFNIJ");

        private final JPanel borrowBookPanelTable = new JPanel();
        private final JTable availableBooksTable = new JTable();
        private final DefaultTableModel availableBooksModel = new DefaultTableModel();
        private final JPanel borrowBookPanelLabel = new JPanel();
        private final JLabel borrowBookLabel = new JLabel("Dostępne ksiązki");


        private final CardLayout layout = new CardLayout();

        private final Library library;
        private final String pathFile;

    }

    private void loadLibraryStateTable() {
        booksTableModel.setNumRows(0);
        library.books.forEach(book1 -> {
        Object[] row = {book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getAmount()};
        booksTableModel.addRow(row);
        });

    }

    private void loadAllUsersTable() {
        usersTableModel.setNumRows(0);
        library.users.forEach(u -> {
            Object[] row = {u.getId(), u.getName(), u.getSurname()};
            usersTableModel.addRow(row);
        });
    }


    private void loadAvailableBooksTable() {
        availableBooksModel.setNumRows(0);
        library.books.forEach(book1 -> {
            Object[] row = {book1.getId(), book1.getTitle(), book1.getAuthor(), book1.getAmount()};
            availableBooksModel.addRow(row);
        });
    }


    public AppFrame(Library library, String pathFile) {
        this.library = library;
        this.pathFile = pathFile;

        setStateLibraryPanel();
        setUserPanel();
        setAllUsersPanel();
        setBorrowBookPanel();
        setLayout();
        setupFrame();

    }

    private void setupFrame () {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // zamkniecie okna
        frame.setMinimumSize(new Dimension(500, 500)); // min rozmiar

        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panelContainer);
        frame.pack();

    }


    private void setLayout(){
        panelContainer.setLayout(layout);


        panelContainer.add(mainPanel, "main");

        panelContainer.add(libraryState, "libraryState");


        panelContainer.add(userPanel, "userPanel");
        panelContainer.add(allUsersPanel, "allUsersPanel");
        panelContainer.add(borrowBookPanel, "borrowBookPanel");

        mainPanel.add(displayStateLibraryBtn);
        mainPanel.add(loginUserBtn);
        mainPanel.add(showUsersBtn);
        layout.show(panelContainer, "main");


        displayStateLibraryBtn.addActionListener(e -> {
            loadLibraryStateTable();
            layout.show(panelContainer, "libraryState");

        });

        loginUserBtn.addActionListener(e -> {

            String userID = JOptionPane.showInputDialog(frame,"Podaj id uzytkownika");
         //   System.out.println(userID);
            if (userID == null) return;
            Integer intUserID = null;
            try {
                intUserID = Validator.validateAmount(userID);
            }
            catch (InvalidNumberFormatException exception) {
              //  System.out.println("exc"+ );
                JOptionPane.showMessageDialog(frame, exception.toString(),
                        "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            if (intUserID == null) return;

            if(library.checkUserExist(intUserID)) {
                System.out.println(intUserID);
                library.setLoggedUser(intUserID);

                loadUser(library.getLoggedUser());

              //  setUserPanel();
                layout.show(panelContainer, "userPanel");




            }
            else {
                JOptionPane.showMessageDialog(frame, "Brak użytkownika w bazie",
                        "Błąd", JOptionPane.ERROR_MESSAGE);
            }

        });



        showUsersBtn.addActionListener(e -> {
            loadAllUsersTable();
            layout.show(panelContainer, "allUsersPanel");

        });


    }

    private void setStateLibraryPanelStyle() {
        libraryState.setLayout(new BorderLayout());
        libraryState.add(libraryStateButtonsPanel, BorderLayout.NORTH);
        libraryState.add(libraryStateTablePanel, BorderLayout.CENTER);


       // libraryStateButtonsPanel.setBackground(Color.red);
      //  libraryStateTablePanel.setBackground(Color.blue);

        libraryStateButtonsPanel.add(addBookBtn);
        libraryStateButtonsPanel.add(deleteBookBtn);
        libraryStateButtonsPanel.add(updateBookBtn);
        libraryStateButtonsPanel.add(backToMainPanel1);

        libraryStateTablePanel.add(booksTable);

        booksTable.setModel(booksTableModel);

        booksTableModel.addColumn("Id");
        booksTableModel.addColumn("Tytuł");
        booksTableModel.addColumn("Autor");
        booksTableModel.addColumn("Ilość");
        libraryStateTablePanel.add(new JScrollPane(booksTable));

        booksTable.getTableHeader().setReorderingAllowed(false);
        booksTable.getTableHeader().setResizingAllowed(false);
    }

    private void setStateLibraryPanel() {


        setStateLibraryPanelStyle();

        FileDataSource file = new FileDataSource(this.pathFile);

        addBookBtn.addActionListener(e -> {
            System.out.println("Hello");


            BookInputDialog book = new BookInputDialog(frame,"Dodawanie ksiązki");


            int select = book.inputBook();
            System.out.println(select);
            String author = null;
            String title = null;
            Integer amount = null;

            String message = "";


            if (select == 0) {
                    //System.out.println("Działla");

                try {
                        author = Validator.validateAuthor(book.author.getText());
                }

                catch (InvalidNameFormatException exception) {
                  //  System.out.println(exception.toString());
                    message = message + exception.toString() + "\n";
                }

                try {
                     title = Validator.validateTitle(book.title.getText());
                } catch (InvalidTitleFormatException exception) {
                    message = message + exception.toString()+ "\n";
                }

                try {
                    amount = Validator.validateAmount(book.amount.getText());
                } catch (InvalidNumberFormatException exception) {
                    message = message + exception.toString() + "\n";
                }

               if (!message.equals("")) {
                   JOptionPane.showMessageDialog(frame, message,
                           "Błąd", JOptionPane.ERROR_MESSAGE);
               }

                if (author != null && title != null && amount != null) {
                    Book b = new Book(title, author, amount, library.getIdNextBook());
                    library.addBook(b);
                    JOptionPane.showMessageDialog(frame, "Dodanie książki przebiegło pomyślnie",
                            "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    loadLibraryStateTable();
                    file.saveToFile(library);
                    }
                }





        });

        deleteBookBtn.addActionListener(e -> {
            //int idColumn = 0;
            int selectedRow = booksTable.getSelectedRow();

            if (selectedRow >= 0) {

                int input = JOptionPane.showConfirmDialog(frame,
                        "Czy napewno chcesz usunąć książkę",
                        "Usuwanie ksiązki",
                        JOptionPane.YES_NO_OPTION);
                if (input == 0) {

                    library.deleteBook(selectedRow);
                    JOptionPane.showMessageDialog(frame, "Usunięcie książki przebiegło pomyślnie",
                            "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    loadLibraryStateTable();
                    file.saveToFile(library);
                }
            }


        });

        updateBookBtn.addActionListener(e -> {

            int selectedRow = booksTable.getSelectedRow();

            if (selectedRow >= 0) {
                String author = null;
                String title = null;
                Integer amount = null;

                String message = "";


                Book updatedBook = library.books.get(selectedRow);
                BookInputDialog book = new BookInputDialog(frame, "Aktualizacja danych");
                int select = book.inputBook();

                if (select == 0) {
                    System.out.println("Działla");

                    try {
                        author = Validator.validateAuthor(book.author.getText());
                    }

                    catch (InvalidNameFormatException exception) {
                        //  System.out.println(exception.toString());
                        message = message + exception.toString() + "\n";
                    }

                    try {
                        title = Validator.validateTitle(book.title.getText());
                    }
                    catch (InvalidTitleFormatException exception) {
                        message = message + exception.toString()+ "\n";
                    }

                    try {
                        amount = Validator.validateAmount(book.amount.getText());
                    } catch (InvalidNumberFormatException exception) {
                        message = message + exception.toString() + "\n";
                    }

                    if (!message.equals("")) {
                        JOptionPane.showMessageDialog(frame, message,
                                "Błąd", JOptionPane.ERROR_MESSAGE);
                    }


                    if (author != null && title != null && amount != null) {
                        updatedBook.updateBook(title, author, amount);
                        JOptionPane.showMessageDialog(frame, "Aktualizacja danych przebiegła pomyślnie",
                                "Informacja", JOptionPane.INFORMATION_MESSAGE);
                        loadLibraryStateTable();
                        file.saveToFile(library);
                    }

                }

            }

        });

        backToMainPanel1.addActionListener(e -> {
            layout.show(panelContainer, "main");
        });


    }


    private void setUserPanelStyle() {
        userPanel.setLayout(new BorderLayout());
        userPanel.add(userPanelButtons, BorderLayout.NORTH);
        userPanel.add(userPanelLabels, BorderLayout.SOUTH);
        userPanel.add(userPanelTable, BorderLayout.CENTER);


        userPanelButtons.add(borrowBookBtn);
        userPanelButtons.add(returnBookBtn);
        userPanelButtons.add(backToMainPanel2);

        userPanelTable.add(borrowedBooksTable);
        borrowedBooksTable.setModel(borrowedBooksModel);
        userPanelTable.add(new JScrollPane(borrowedBooksTable));



        userPanelLabels.add(nameLabel);
        userPanelLabels.add(surnameLabel);


        borrowedBooksModel.addColumn("Id");
        borrowedBooksModel.addColumn("Tytuł");
        borrowedBooksModel.addColumn("Autor");
        borrowedBooksModel.addColumn("Termin oddania");

        borrowedBooksTable.getTableHeader().setReorderingAllowed(false);
        borrowedBooksTable.getTableHeader().setResizingAllowed(false);
    }

    private void setUserPanel() {

        setUserPanelStyle();
        FileDataSource file = new FileDataSource(this.pathFile);

        borrowBookBtn.addActionListener(e -> {
            layout.show(panelContainer, "borrowBookPanel");
            loadAvailableBooksTable();
        });

        backToMainPanel2.addActionListener(e -> {
            layout.show(panelContainer, "main");
            library.setLoggedUser(-1);
        });

        returnBookBtn.addActionListener(e -> {
            int selectedRow = borrowedBooksTable.getSelectedRow();
            if (selectedRow >= 0) {
                System.out.println("oddaje ksiazke");

                int input = JOptionPane.showConfirmDialog(frame,
                        "Czy napewno chcesz oddać tę książkę",
                        "Oddawanie ksiązki",
                        JOptionPane.YES_NO_OPTION);

                if (input == 0) {
                    User loadedUser = library.getUser(library.getLoggedUser());
                    int bookID = (int) borrowedBooksModel.getValueAt(selectedRow, 0);
                    loadedUser.returnBook(library, bookID, selectedRow);
                    JOptionPane.showMessageDialog(frame, "Oddanie książki przebiegło pomyślnie",
                            "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    loadUser(library.getLoggedUser());
                    file.saveToFile(library);
                }
            }
        });

    }


    private void loadUser(int id) {
        User loadedUser = library.getUser(id);
        nameLabel.setText(loadedUser.getName());
        surnameLabel.setText(loadedUser.getSurname());
        borrowedBooksModel.setNumRows(0);
        loadedUser.borrowedBooks.forEach(borrowedBook -> {
           Object[] row = {borrowedBook.getId(), borrowedBook.getTitle(), borrowedBook.getAuthor(), borrowedBook.expiredDateToString()};
            borrowedBooksModel.addRow(row);
        });

    }

    private void setBorrowBookPanelStyle() {
        borrowBookPanel.setLayout(new BorderLayout());
        borrowBookPanel.add(borrowBookPanelButtons,BorderLayout.NORTH);
        borrowBookPanel.add(borrowBookPanelTable, BorderLayout.CENTER);
        borrowBookPanel.add(borrowBookPanelLabel, BorderLayout.SOUTH);


        borrowBookPanelTable.add(availableBooksTable);
        borrowBookPanelButtons.add(borrowBookBtn2);
        borrowBookPanelButtons.add(backToUserPanelBtn);

        borrowBookPanelLabel.add(borrowBookLabel);


        availableBooksTable.setModel(availableBooksModel);

        availableBooksModel.addColumn("Id");
        availableBooksModel.addColumn("Tytuł");
        availableBooksModel.addColumn("Autor");
        availableBooksModel.addColumn("Ilość");
        borrowBookPanelTable.add(new JScrollPane(availableBooksTable));

        availableBooksTable.getTableHeader().setReorderingAllowed(false);
        availableBooksTable.getTableHeader().setResizingAllowed(false);

    }

    private void setBorrowBookPanel() {
        FileDataSource file = new FileDataSource(this.pathFile);
        setBorrowBookPanelStyle();

        backToUserPanelBtn.addActionListener(e-> {
            layout.show(panelContainer, "userPanel");
        });

        borrowBookBtn2.addActionListener(e -> {
            System.out.println("Wypożyczam książke");
            User loadedUser = library.getUser(library.getLoggedUser());

            int selectedRow = availableBooksTable.getSelectedRow();
            int bookId = (int) availableBooksModel.getValueAt(selectedRow, 0);


            if (selectedRow >= 0) {

                int input = JOptionPane.showConfirmDialog(frame,
                        "Czy napewno chcesz wypożyczyć książkę książkę",
                        "Wypożycznie ksiązki",
                        JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    if (loadedUser.borrowBook(library, bookId) == 0) {
                        loadUser(library.getLoggedUser());
                        loadAvailableBooksTable();
                        file.saveToFile(library);
                       JOptionPane.showMessageDialog(frame, "Wypożyczenie ksiązki przebiegło pomyślnie",
                                                       "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Nie ma tej ksiązki aktualnie w bibliotece",
                                "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }


        });

    }



    private void setAllUsersPanelStyle() {

        allUsersPanel.setLayout(new BorderLayout());
        allUsersPanel.add(allUsersButtonsPanel, BorderLayout.NORTH);
        allUsersPanel.add(allUsersTablePanel, BorderLayout.CENTER);


        allUsersButtonsPanel.add(addUserBtn);
        allUsersButtonsPanel.add(deleteUserBtn);
        allUsersButtonsPanel.add(updateUserBtn);
        allUsersButtonsPanel.add(backToMainPanel3);


        allUsersTablePanel.add(usersTable);


        usersTable.setModel(usersTableModel);
        usersTableModel.addColumn("Id");
        usersTableModel.addColumn("Imię");
        usersTableModel.addColumn("Nazwisko");
        allUsersTablePanel.add(new JScrollPane(usersTable));

        usersTable.getTableHeader().setReorderingAllowed(false);
        usersTable.getTableHeader().setResizingAllowed(false);




    }


    private void setAllUsersPanel() {

        //  serializacja danych koniaczna do wykoniania

        setAllUsersPanelStyle();
        FileDataSource file = new FileDataSource(this.pathFile);

        addUserBtn.addActionListener(e -> {


            UserInputDialog userInput = new UserInputDialog(frame,"Dodawanie użytkownika");
            int select = userInput.inputUser();
            if (select == 0) {
               // System.out.println("Hello world\n");

                String name = null;
                String surname = null;

                try {
                    name = Validator.validateString(userInput.name.getText());
                    surname = Validator.validateString(userInput.surname.getText());
                } catch (InvalidStringFormatException exception) {
                    JOptionPane.showMessageDialog(frame, exception.toString(),
                            "Błąd", JOptionPane.ERROR_MESSAGE);
                }

                if (name != null && surname != null) {
                    int uID = library.getIdNextUser();
                    User user = new User(name, surname, uID);
                    library.addUser(user);
                    JOptionPane.showMessageDialog(frame, "Dodano użytkownika o id " + uID + " !",
                            "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    loadAllUsersTable();
                    file.saveToFile(library);
                }
            }

        });


        deleteUserBtn.addActionListener(e -> {


            int selectedRow = usersTable.getSelectedRow();

            if (selectedRow >= 0) {

                int input = JOptionPane.showConfirmDialog(frame,
                        "Czy napewno chcesz usunąć użytkownika",
                        "Usuwanie użytkownika",
                        JOptionPane.YES_NO_OPTION);
                if (input == 0) {
                    int uID = library.users.get(selectedRow).getId();
                    library.deleteUser(selectedRow);
                    JOptionPane.showMessageDialog(frame, "Usunięto użytkownika o id " + uID + " !",
                            "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    loadAllUsersTable();
                    file.saveToFile(library);
                }
            }
        });


        updateUserBtn.addActionListener(e -> {

            int selectedRow = usersTable.getSelectedRow();

            if (selectedRow >= 0) {

                User updatedUser = library.users.get(selectedRow);
                UserInputDialog userInput = new UserInputDialog(frame,"Aktualizacja danych");
                int select = userInput.inputUser();

                if (select == 0) {

                    String name = null;
                    String surname = null;

                    try {
                        name = Validator.validateString(userInput.name.getText());
                        surname = Validator.validateString(userInput.surname.getText());
                    } catch (InvalidStringFormatException exception) {
                        JOptionPane.showMessageDialog(frame, exception.toString(),
                                "Błąd", JOptionPane.ERROR_MESSAGE);
                    }

                    if (name != null && surname != null) {
                        updatedUser.updateUser(name, surname);
                        JOptionPane.showMessageDialog(frame, "Aktualizacja danych przebiegła pomyślnie",
                                "Informacja", JOptionPane.INFORMATION_MESSAGE);
                        loadAllUsersTable();
                        file.saveToFile(library);
                    }
                }
            }




        });

        backToMainPanel3.addActionListener(e -> {
            layout.show(panelContainer, "main");
        });

    }


}
