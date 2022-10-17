package ui;

import model.*;

import java.util.Scanner;

public class LinesApp {
    private BookLists bookPack;
    private Excerpts sentPack;
    private Scanner input;

    // EFFECTS: run the Lines application
    public LinesApp() {
        runLines();
    }

    // MODIFIES: this
    // EFFECTS: processes user input in main
    public void runLines() {
        boolean keepGoing = true;
        String command = null;

        init();

        System.out.println("\nWelcome to Lines!");

        while (keepGoing) {
            displayMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                accessGate(command);
            }
        }

        System.out.println("\nThank you for using!");
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes bookPack and sentPack
    public void init() {
        bookPack = new BookLists("DoneBooks", "ToReadBooks", "RecommendBooks");
        sentPack = new Excerpts("Lines Collection");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays Main menu of options to user
    private void displayMainMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tb -> Access lists of books");
        System.out.println("\te -> Access excerpts");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void accessGate(String command) {
        if (command.equals("b")) {
            doLists();
        } else if (command.equals("e")) {
            doExcerpts();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: process user inputs within Excerpts
    private void doExcerpts() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayExcerpts();
            displayExcerptsMenu();
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                doExcerpts(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process each command in Excerpts
    private void doExcerpts(String command) {
        if (command.equals("r")) {
            renameExcerpts();
        } else if (command.equals("a")) {
            addExcerpts();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: rename the excerpts
    private void renameExcerpts() {
        System.out.println("What's the new name of your excerpts?");
        input = new Scanner(System.in);
        String newName = input.nextLine();
        sentPack.renameExcerpts(newName);
    }

    // MODIFIES: this
    // EFFECTS: add an excerpt
    private void addExcerpts() {
        System.out.println("Where is your excerpt from?");
        input = new Scanner(System.in);
        String from = input.nextLine();
        System.out.println("What is the content?");
        input = new Scanner(System.in);
        String content = input.nextLine();
        sentPack.addExcerpt(new Excerpt(from, content));
    }

    // EFFECTS: displays Excerpts to user
    private void displayExcerpts() {
        System.out.println("\n" + sentPack.excerptsToString());
    }

    // EFFECTS: displays Excerpts menu of options to user
    private void displayExcerptsMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> Rename excerpts");
        System.out.println("\ta -> Add an excerpt");
        System.out.println("\tq -> quit (you will go back to the main menu)");
    }

    // MODIFIES: this
    // EFFECTS: process user inputs within BookLists
    private void doLists() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayLists();
            displayListsMenu();
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                accessLists(command);
            }
        }
    }

    // EFFECTS: displays Books lists to user
    private void displayLists() {
        System.out.println("\nHere is the lists of books:");
        System.out.println("\tDoneBooks (books that finish reading)");
        System.out.println("\tToReadBooks (books that current read or plan to read)");
        System.out.println("\tRecommendBooks (books that other recommended)");
        if (bookPack.getSpecialLists().size() != 0) {
            for (BookList bl: bookPack.getSpecialLists()) {
                System.out.println("\t" + bl.getName());
            }
        }
    }

    // EFFECTS: displays Books menu of options to user
    private void displayListsMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> Access DoneBooks");
        System.out.println("\tt -> Access ToReadBooks");
        System.out.println("\tr -> Access RecommendBooks");
        if (bookPack.getSpecialLists().size() != 0) {
            for (int i = 0; i < bookPack.getSpecialLists().size(); i++) {
                System.out.println("\t" + i + " -> Access " + bookPack.getSpecialLists().get(i).getName());
            }
        }
        System.out.println("\ta -> Add a new BookList");
        System.out.println("\tq -> quit (you will go back to the main menu)");
    }


    // MODIFIES: this
    // EFFECTS: processes user command within list
    private void accessLists(String command) {
        if (command.equals("d")) {
            accessBooks("DoneBooks");
        } else if (command.equals("t")) {
            accessBooks("ToReadBooks");
        } else if (command.equals("r")) {
            accessBooks("RecommendBooks");
        } else if (command != null && command.matches("[0-9.]+")) {
            int com = Integer.parseInt(command);
            if (com < bookPack.getSpecialLists().size()) {
                accessBooks(bookPack.getSpecialLists().get(com).getName());
            } else {
                System.out.println("Selection not valid...");
            }
        } else if (command.equals("a")) {
            addNewBookList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: add a new bookList to this
    private void addNewBookList() {
        System.out.println("What's the name of your new list?");
        input = new Scanner(System.in);
        String newName = input.nextLine();
        bookPack.addBooks(new BookList(newName));
    }

    // MODIFIES: this
    // EFFECTS: processes user command within Books (a list of book)
    private void accessBooks(String listName) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            BookList current = bookPack.getBooks(listName);
            displayBooks(current);
            displayBooksMenu(current);
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                doBooks(command, current);
            }
        }
    }

    // EFFECTS: display Books to user
    private void displayBooks(BookList bl) {
        if (bl.getBookList().size() > 0) {
            System.out.println(bl.booksToString());
        } else {
            System.out.println("\nNo books for now.");
        }
    }

    // EFFECTS: displays Books menu of options to user
    private void displayBooksMenu(BookList bl) {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add a book");
        System.out.println("\tr -> Remove a book");
        System.out.println("\tc -> Check whether a book is in this list");
        if (bl.getBookList().size() > 0) {
            System.out.println("\ts -> Get and set up detailed book info");
        }
        System.out.println("\tq -> quit (you will go back to the list menu)");
    }

    // MODIFIES: this
    // EFFECTS: processes user command within List
    private void doBooks(String command, BookList bl) {
        if (command.equals("a")) {
            addBook(bl);
        } else if (command.equals("r")) {
            removeBook(bl);
        } else if (command.equals("c")) {
            checkBook(bl);
        } else if (command.equals("s") && (bl.getBookList().size() > 0)) {
            doBook(bl);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: add a book getting from user
    public void addBook(BookList bl) {
        System.out.println("What's the name of your new book?");
        input = new Scanner(System.in);
        String newName = input.nextLine();
        bl.addBook(new Book(newName));
    }

    // MODIFIES: this
    // EFFECTS: remove a book that user selected
    public void removeBook(BookList bl) {
        System.out.println("What's the index of the book you want to delete?");
        input = new Scanner(System.in);
        int index = input.nextInt();
        bl.removeBook((Book) bl.getBookList().get(index));
    }

    // EFFECTS: check whether the book is in the list
    public void checkBook(BookList bl) {
        System.out.println("What's the name of the book you want to check?");
        input = new Scanner(System.in);
        String given = input.nextLine();
        boolean t = bl.getBookNames().contains(given);
        if (t == true) {
            System.out.println("The book <<" + given + ">> is in this list.");
        } else {
            System.out.println("Does not find this book.");
        }
    }

    // MODIFIES: this
    // EFFECTS: step into process user command with in a book
    public void doBook(BookList bl) {
        System.out.println("Which book you want to view the detailed info (Enter the index)?");
        input = new Scanner(System.in);
        int index = input.nextInt();
        if (bl.containsBook((Book) bl.getBookList().get(index))) {
            processBook((Book) bl.getBookList().get(index));
        } else {
            System.out.println("The given book is not exists in list.");
        }
    }

    // MODIFIES: this
    // EFFECTS: process user command with in a book
    public void processBook(Book b) {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayBook(b);
            displayBookMenu(b);
            input = new Scanner(System.in);
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processBook(command, b);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Match each command to its instruction
    public void processBook(String command, Book b) {
        if (command.equals("n")) {
            renameBook(b);
        } else if (command.equals("a")) {
            addNote(b);
        } else if (command.equals("r")) {
            removeNote(b);
        } else if (command.equals("u")) {
            updateProgress(b);
        } else if (command.equals("d")) {
            done(b);
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays Book info to user
    public void displayBook(Book b) {
        System.out.println(b.getBookName() + ":");
        System.out.println("\tCurrent Reading Progress: ");
        if ((b.getLastPage() == 0) && (b.getPercentage() == 0)) {
            System.out.println("\t\tHaven't initialize your reading stage. \n\t\tTry update it!");
        } else {
            String page = "(Page " + b.getCurrentPage() + " out of " + b.getLastPage() + ")";
            System.out.println("\t\t" + b.getPercentage() + "% " + page);
        }
        System.out.println("\t" + b.getNotes().notesToString());
    }

    // EFFECTS: displays Book menu of options to user
    public void displayBookMenu(Book b) {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> Rename the book");
        System.out.println("\tu -> Update the current reading stage");
        System.out.println("\td -> Done this book");
        System.out.println("\ta -> Add a note");
        System.out.println("\tr -> Remove a note");
        System.out.println("\tq -> quit (you will go back to the list menu)");
    }


    // MODIFIES: this
    // EFFECTS: rename the book with user's given name
    public void renameBook(Book b) {
        System.out.println("What's the new name of your book?");
        input = new Scanner(System.in);
        String newName = input.nextLine();
        b.rename(newName);
    }

    // MODIFIES: this
    // EFFECTS: add the note with user's given page
    public void addNote(Book b) {
        System.out.println("Which page does the note refer to (Enter integer)?");
        input = new Scanner(System.in);
        int page = input.nextInt();
        System.out.println("What is the content of the note?");
        input = new Scanner(System.in);
        String content = input.nextLine();
        b.getNotes().addNote(new Note(page, content));
    }

    // MODIFIES: this
    // EFFECTS: remove the note that user selected
    public void removeNote(Book b) {
        System.out.println("What's the index of the note you want to delete? (Enter the number inside the bracket)");
        input = new Scanner(System.in);
        int index = input.nextInt();
        while (index >= b.getNotes().size()) {
            System.out.println("Index is not valid. Typed again:");
            input = new Scanner(System.in);
            index = input.nextInt();
        }
        b.getNotes().removeNote(b.getNotes().get(index));
    }

    // MODIFIES: this
    // EFFECTS: done a book and add it to the doneBook list
    public void done(Book b) {
        b.doneBook();
        bookPack.getFinishedBooks().addBook(b);
    }

    // MODIFIES: this
    // EFFECTS: Update the reading stage of given book
    public void updateProgress(Book b) {
        System.out.println("What's the last page of the book?");
        input = new Scanner(System.in);
        int lastPage = input.nextInt();
        b.setLastPage(lastPage);
        System.out.println("What's the current page of the book?");
        input = new Scanner(System.in);
        int currentPage = input.nextInt();
        while (currentPage > lastPage) {
            System.out.println("The current page should be less than the last page.");
            System.out.println("Enter(Integer) again:");
            input = new Scanner(System.in);
            currentPage = input.nextInt();
        }
        b.setCurrentStage(currentPage);
    }
}
