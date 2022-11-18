package ui;

import model.*;
import persistence.JsonReaderBook;
import persistence.JsonReaderExcerpt;
import persistence.JsonWriterBook;
import persistence.JsonWriterExcerpt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represents the Lines GUI application
public class LinesAppGUI {
    private static final String JSON_STORE_Book = "./data/bookPack.json";
    private static final String JSON_STORE_Sent = "./data/sentPack.json";
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;

    private JFrame mainMenu;
    private JPanel blsPanel;
    private JPanel booksPanel;
    private JPanel excerptsPanel;
    private ArrayList<JButton> blsButton;
    private JFrame bookWindow;

    private BookList activeBooks = null;
    private BookLists bookPack;
    private Excerpts sentPack;
    private Scanner input;
    private JsonWriterBook jsonWriterBook;
    private JsonReaderBook jsonReaderBook;
    private JsonReaderExcerpt jsonReaderExcerpt;
    private JsonWriterExcerpt jsonWriterExcerpt;

    // EFFECTS: run the Lines GUI application
    public LinesAppGUI() throws FileNotFoundException {
        jsonReaderBook = new JsonReaderBook(JSON_STORE_Book);
        jsonWriterBook = new JsonWriterBook(JSON_STORE_Book);
        jsonReaderExcerpt = new JsonReaderExcerpt(JSON_STORE_Sent);
        jsonWriterExcerpt = new JsonWriterExcerpt(JSON_STORE_Sent);

        setUp();

        initGraphic();
    }

    // MODIFIES: this
    // EFFECTS: Initiate graphic and construct the layout
    public void initGraphic() {
        mainMenu = new JFrame("Lines");
        mainMenu.setSize(WIDTH, HEIGHT);
        mainMenu.setLayout(new GridLayout(1, 2));

        initBookListsDisplay();
        initExceptsDisplay();
        mainMenu.add(blsPanel);
        mainMenu.add(excerptsPanel);
        //mainMenu.setLocation(500,250);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.pack();
        mainMenu.setVisible(true);
        end();
    }

    // MODIFIES: this
    // EFFECTS: Initiate BookList area and construct the layout
    public void initBookListsDisplay() {
        blsPanel = new JPanel(new GridLayout(2, 1));
        blsPanel.setSize(WIDTH / 2, HEIGHT);
        blsPanel.setBackground(new Color(37, 56, 107));

        displayBookLists();

        booksPanel = new JPanel();
        booksPanel.setSize(WIDTH / 2, HEIGHT / 2);
        booksPanel.setBackground(new Color(46, 78, 148));
        blsPanel.add(booksPanel);

        displayBooks(activeBooks);
    }

    // MODIFIES: this
    // EFFECTS: Initiate Excerpts area and construct the layout
    public void initExceptsDisplay() {
        excerptsPanel = new JPanel();
        excerptsPanel.setSize(WIDTH / 2, HEIGHT);
        excerptsPanel.setBackground(new Color(170, 77, 61));
        excerptsPanel.setLayout(new GridLayout(2, 1));
        excerptsPanel.add(displayExcerpts());
        excerptsPanel.add(excerptButton());
    }

    // MODIFIES: this
    // EFFECTS: initializes bookPack and sentPack
    public void init() {
        bookPack = new BookLists("DoneBooks", "ToReadBooks", "RecommendBooks");
        sentPack = new Excerpts("Lines Collection");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: load the data from the storing source if user requires; otherwise initiate new packs
    public void setUp() {
        JDialog loadData = new JDialog(mainMenu, "Loading", true);
        loadData.setSize(200, 200);
        loadData.setLocationRelativeTo(null);

        JPanel mainGui = new JPanel(new BorderLayout());
        mainGui.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainGui.add(new JLabel("Do you want to load your data from last time?"), BorderLayout.CENTER);

        String msg = "Loaded BookLists from " + JSON_STORE_Book + "\nLoaded Excerpts from " + JSON_STORE_Sent;
        JPanel buttonPanel = dataButtonsPanel(loadData, msg, 0);

        mainGui.add(buttonPanel, BorderLayout.SOUTH);

        loadData.setContentPane(mainGui);
        loadData.pack();
        loadData.setVisible(true);
    }


    // EFFECTS: Helper method of dataButtonsPanel; 0 will load data, 1 will save data
    public void select(int state) {
        if (state == 0) {
            load();
        }
        if (state == 1) {
            save();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads bls and excerpts from file
    private void load() {
        try {
            bookPack = jsonReaderBook.read();
            sentPack = jsonReaderExcerpt.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_Book + "and" + JSON_STORE_Sent + ".");
            init();
        }
    }

    // MODIFIES: this
    // EFFECTS: save the data to the storing source if user requires; otherwise quit directly
    public void end() {
        mainMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                checkOut();
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: save the data to the storing source if user requires; otherwise quit directly
    private void checkOut() {
        JDialog loadData = new JDialog(mainMenu, "Saving", true);
        loadData.setSize(200, 200);
        loadData.setLocationRelativeTo(null);

        JPanel mainGui = new JPanel(new BorderLayout());
        mainGui.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainGui.add(new JLabel("Do you want to save your data?"), BorderLayout.CENTER);

        String msg = "Saved BookLists to" + JSON_STORE_Book + "\nSaved Excerpts to " + JSON_STORE_Sent;
        JPanel buttonPanel = dataButtonsPanel(loadData, msg, 1);

        mainGui.add(buttonPanel, BorderLayout.SOUTH);

        loadData.setContentPane(mainGui);
        loadData.pack();
        loadData.setVisible(true);
    }

    // EFFECTS: saves the bookLists and excerpts to file
    private void save() {
        try {
            jsonWriterBook.open();
            jsonWriterBook.write(bookPack);
            jsonWriterBook.close();
            jsonWriterExcerpt.open();
            jsonWriterExcerpt.write(sentPack);
            jsonWriterExcerpt.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_Book + "and" + JSON_STORE_Sent + ".");
        }
    }

    // EFFECTS: Update the mainMenu
    public void updateMainMenu() {
        mainMenu.setVisible(false);
        initGraphic();
    }

    // MODIFIES: this
    // EFFECTS: arrange layout for bookLists area
    public void displayBookLists() {
        int numOfBLs = bookPack.getSpecialLists().size() + 3;
        JPanel bookLists = new JPanel(new GridLayout(numOfBLs + 1, 1));
        blsButton = new ArrayList<>();
        JButton finiBL = new JButton(bookPack.getFinishedBooks().getName());
        JButton toReadBL = new JButton(bookPack.getToRead().getName());
        JButton recomBL = new JButton(bookPack.getRecommendBooks().getName());
        blsButton.add(finiBL);
        blsButton.add(toReadBL);
        blsButton.add(recomBL);
        for (int i = 0; i < bookPack.getSpecialLists().size(); i++) {
            JButton blButton = new JButton(bookPack.getSpecialLists().get(i).getName());
            blsButton.add(blButton);
        }
        for (JButton b : blsButton) {
            int i = blsButton.indexOf(b);
            BlButtonListener accessBooks = new BlButtonListener(i);
            b.addActionListener(accessBooks);
            bookLists.add(b);
        }
        buildBL(bookLists);
        blsPanel.add(bookLists);
    }

    // MODIFIES: this
    // EFFECTS: Build a panel for bookLists area and put buttons in it
    public void buildBL(JPanel bls) {
        JPanel enterBL = new JPanel();
        enterBL.setBorder(BorderFactory.createTitledBorder("Enter the name of your new list:"));
        JTextField addBL = new JTextField(20);
        addBL.addActionListener(new AddBLListener());
        enterBL.add(addBL);
        bls.add(enterBL);
    }

    // MODIFIES: this
    // EFFECTS: arrange layout for Books area
    public void displayBooks(BookList bl) {
        String bookString;
        if (bl == null) {
            bookString = "Empty BookList! Choose one.";
        } else if (bl.getBookList().size() > 0) {
            bookString = bl.booksToString();
        } else {
            bookString = "No books for now.";
        }
        JLabel books = new JLabel(bookString);
        JPanel bookButtonPanel = new JPanel(new GridLayout(2, 2));
        JButton addBook = addBook(bl);
        JButton removeBook = removeBook(bl);
        JButton checkBook = checkBook(bl);
        JButton accessBook = doBook(bl);
        bookButtonPanel.add(addBook);
        bookButtonPanel.add(removeBook);
        bookButtonPanel.add(checkBook);
        bookButtonPanel.add(accessBook);

        JScrollPane bookScroll = new JScrollPane(books);
        bookScroll.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 4));
        booksPanel.setLayout(new GridLayout(2, 1));
        booksPanel.add(bookScroll);
        booksPanel.add(bookButtonPanel);
    }

    // MODIFIES: this
    // EFFECTS: create a separate frame for book and arrange layout for it
    public void bookOpen(Book book) {
        bookWindow = new JFrame("Book");
        bookWindow.setLocation(500, 250);
        bookWindow.setSize(500, 500);
        bookWindow.setLayout(new FlowLayout());

        bookWindow.add(displayBook(book));
        bookWindow.add(insertGraph());
        bookWindow.add(displayBookMenu(book), BorderLayout.SOUTH);
        bookWindow.setVisible(true);
        endBookWindow();
    }

    // MODIFIES: this
    // EFFECTS: hide the Book window to lead user back to the main menu
    public void endBookWindow() {
        bookWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                bookWindow.setVisible(false);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: update the layout of Book window by hide the previous one and open a new one
    public void updateBookWindow(Book b) {
        bookWindow.setVisible(false);
        bookOpen(b);
    }

    // EFFECTS: Return data buttonPanel with finished layout; set up actions when button get clicked
    public JPanel dataButtonsPanel(JDialog loadData, String msg, int state) {
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                select(state);
                JOptionPane.showMessageDialog(null, msg);
                loadData.setVisible(false);
            }
        });
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state == 0) {
                    init();
                }
                loadData.setVisible(false);

            }
        });
        buttonPanel.add(yes);
        buttonPanel.add(no);
        return buttonPanel;
    }

    // EFFECTS: return a JLabel with a graph in it
    public JLabel insertGraph() {
        ImageIcon img = new ImageIcon("./data/project-image-book.jpeg");
        Image image = img.getImage();
        image = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        img.setImage(image);
        JLabel graph = new JLabel(img);
        graph.setPreferredSize(new Dimension(200, 200));
        return graph;
    }

    // EFFECTS: return a JLabel that displays Book info to user
    public JLabel displayBook(Book b) {
        String bookInfo = "<html><body>" + b.getBookName() + ":" + "<br>    Current Reading Progress: ";
        if ((b.getLastPage() == 0) && (b.getPercentage() == 0)) {
            bookInfo = bookInfo + "<br>        Haven't initialize your reading stage. <br>        Try update it!";
        } else {
            String page = "(Page " + b.getCurrentPage() + " out of " + b.getLastPage() + ")";
            bookInfo = bookInfo + "<br>    " + b.getPercentage() + "% " + page;
        }
        bookInfo = bookInfo + "<br>    " + b.getNotes().notesToString() + "<body></html>";

        JLabel bookPrint = new JLabel(bookInfo);
        bookPrint.setPreferredSize(new Dimension(400, 200));
        return bookPrint;
    }


    // EFFECTS: return a panel displays Book menu of options to user
    public JPanel displayBookMenu(Book b) {
        JPanel bookButtonPanel = new JPanel(new GridLayout(5, 1));
        JButton renameBook = renameBook(b);
        JButton addNote = addNote(b);
        JButton removeNote = removeNote(b);
        JButton done = done(b);
        JButton updateProgress = updateProgress(b);
        bookButtonPanel.add(renameBook);
        bookButtonPanel.add(addNote);
        bookButtonPanel.add(removeNote);
        bookButtonPanel.add(done);
        bookButtonPanel.add(updateProgress);
        return bookButtonPanel;
    }

    // EFFECTS: return a panel with finished buttons layout
    public JPanel excerptButton() {
        JPanel excerptButtonPanel = new JPanel(new GridLayout(1, 2));
        JButton renameExcerpt = renameExcerpts();
        JButton addExcerpt = addExcerpts();
        excerptButtonPanel.add(renameExcerpt);
        excerptButtonPanel.add(addExcerpt);
        return excerptButtonPanel;
    }

    // EFFECTS: return a panel displays Excerpts to user
    private JScrollPane displayExcerpts() {
        String excerptInfo = "<html><body>" + sentPack.excerptsToString() + "<body></html>";
        JLabel excerptPrint = new JLabel(excerptInfo);
        //int excerptHeight = excerptPrint.getHeight();
        //excerptPrint.setPreferredSize(new Dimension((WIDTH / 2) - 100, excerptHeight));
        excerptPrint.setVerticalTextPosition(SwingConstants.TOP);
        JScrollPane excerptScroll = new JScrollPane(excerptPrint);
        excerptScroll.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 2));
        return excerptScroll;
    }

    // MODIFIES: this
    // EFFECTS: return addBook button and add a book to bookPack
    public JButton addBook(BookList bl) {
        JButton addBook = new JButton("Add Book");
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog("What's the name of your new book?");
                bl.addBook(new Book(newName));
                updateMainMenu();
            }
        });
        return addBook;
    }

    // MODIFIES: this
    // EFFECTS: return remove button and remove a book that user selected
    public JButton removeBook(BookList bl) {
        JButton removeBook = new JButton("Remove Book");
        removeBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("What's the index of the book you want to delete?");
                int index = Integer.parseInt(input);
                if (index >= bl.getBookList().size()) {
                    JOptionPane.showMessageDialog(booksPanel, "Does not find this book.");
                } else {
                    bl.removeBook((Book) bl.getBookList().get(index));
                }
                updateMainMenu();
            }
        });
        return removeBook;
    }

    // EFFECTS: return check button and check whether the book is in the list
    public JButton checkBook(BookList bl) {
        JButton checkBook = new JButton("Check Book");
        checkBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String given = JOptionPane.showInputDialog("What's the name of the book you want to check?");
                boolean t = bl.getBookNames().contains(given);
                if (t == true) {
                    JOptionPane.showMessageDialog(booksPanel, "The book <<" + given + ">> is in this list.");
                } else {
                    JOptionPane.showMessageDialog(booksPanel, "Does not find this book.");
                }
            }
        });
        return checkBook;
    }

    // MODIFIES: this
    // EFFECTS: return access button and step into a book
    public JButton doBook(BookList bl) {
        JButton accessBook = new JButton("Access");
        accessBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter the index of the book you want to access:");
                int index = Integer.parseInt(input);
                if (bl.containsBook((Book) bl.getBookList().get(index))) {
                    bookOpen((Book) bl.getBookList().get(index));
                } else {
                    JOptionPane.showMessageDialog(booksPanel, "The book is not exists in this list.");
                }
            }
        });
        return accessBook;
    }

    // MODIFIES: this
    // EFFECTS: return rename button and rename the book with user's given name
    public JButton renameBook(Book b) {
        JButton rename = new JButton("Rename the book");
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog("What's the new name of your book?");
                b.rename(newName);
                updateBookWindow(b);
            }
        });
        return rename;
    }

    // MODIFIES: this
    // EFFECTS: return addNote button and add the note with user's given page
    public JButton addNote(Book b) {
        JButton add = new JButton("Add a note");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Which page does the note refer to (Enter integer)?");
                int page = Integer.parseInt(input);
                String content = JOptionPane.showInputDialog("What is the content of the note?");
                b.getNotes().addNote(new Note(page, content));
                updateBookWindow(b);
            }
        });
        return add;
    }

    // MODIFIES: this
    // EFFECTS: return remove button and remove the note that user selected
    public JButton removeNote(Book b) {
        JButton remove = new JButton("Remove a note");
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("What's the index of the note you want to delete?");
                int index = Integer.parseInt(input);
                while (index >= b.getNotes().size()) {
                    input = JOptionPane.showInputDialog("Index is not valid. Typed again:");
                    index = Integer.parseInt(input);
                }
                b.getNotes().removeNote(b.getNotes().get(index));
                updateBookWindow(b);
            }
        });
        return remove;

    }

    // MODIFIES: this
    // EFFECTS: return done button and done a book and add it to the doneBook list
    public JButton done(Book b) {
        JButton done = new JButton("Done this book");
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.doneBook();
                bookPack.getFinishedBooks().addBook(b);
                JOptionPane.showMessageDialog(bookWindow, "The book is in DoneBooks now.");
                updateMainMenu();
            }
        });
        return done;
    }

    // MODIFIES: this
    // EFFECTS: return update button and update the reading stage of given book
    public JButton updateProgress(Book b) {
        JButton update = new JButton("Update reading progress");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("What's the last page of the book?");
                int lastPage = Integer.parseInt(input);
                b.setLastPage(lastPage);
                input = JOptionPane.showInputDialog("What's the current page of the book?");
                int currentPage = Integer.parseInt(input);
                while (currentPage > lastPage) {
                    input = JOptionPane.showInputDialog("The current page should be less than the last page."
                            + "\nEnter(Integer) again:");
                    currentPage = Integer.parseInt(input);
                }
                b.setCurrentStage(currentPage);
                updateBookWindow(b);
            }
        });
        return update;
    }

    // MODIFIES: this
    // EFFECTS: return rename button and rename the excerpts
    private JButton renameExcerpts() {
        JButton rename = new JButton("Rename Excerpt");
        rename.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newName = JOptionPane.showInputDialog("What's the new name of your excerpts?");
                sentPack.renameExcerpts(newName);
                updateMainMenu();
            }
        });
        return rename;
    }

    // MODIFIES: this
    // EFFECTS: return add button and add an excerpt
    private JButton addExcerpts() {
        JButton addExcerpt = new JButton("Add Excerpt");
        addExcerpt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String from = JOptionPane.showInputDialog("Where is your excerpt from?");
                String content = JOptionPane.showInputDialog("What is the content?");
                sentPack.addExcerpt(new Excerpt(from, content));
                updateMainMenu();
            }
        });
        return addExcerpt;
    }

    // Represent AddBLListener which inherit ActionListener
    class AddBLListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JTextField field = (JTextField) e.getSource();
            String name = field.getText();
            if (name == null) {
                JOptionPane.showMessageDialog(null, "Empty input!!! Try again!");
            } else {
                bookPack.addBooks(new BookList(name));
                field.setText("");
            }
            updateMainMenu();
        }
    }

    // Represent BlButtonListener which inherit ActionListener
    class BlButtonListener implements ActionListener {
        private int index;

        public BlButtonListener(int i) {
            this.index = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (index == 0) {
                activeBooks = bookPack.getFinishedBooks();
            } else if (index == 1) {
                activeBooks = bookPack.getToRead();
            } else if (index == 2) {
                activeBooks = bookPack.getRecommendBooks();
            } else {
                activeBooks = bookPack.getSpecialLists().get(index - 3);
            }
            updateMainMenu();
        }
    }
}


