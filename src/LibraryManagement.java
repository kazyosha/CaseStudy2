package src;

import Interface.ILibrary;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class LibraryManagement implements ILibrary {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private FileHandler fw;
    private Scanner sc = new Scanner(System.in);

    public LibraryManagement() {
        fw = new FileHandler(books, users);
        this.users = fw.loadUsersFromFile();
        books.addAll(fw.loadBooksFromFile());
    }

    @Override
    public void addBook() {
        System.out.println("Enter Book ID: ");
        String bookID = sc.nextLine();
        System.out.println("Enter Book Title: ");
        String bookName = sc.nextLine();
        System.out.println("Enter Book Author: ");
        String bookAuthor = sc.nextLine();
        System.out.println("Enter Book Category: ");
        String bookCategory = sc.nextLine();

        books.add(new Book(bookID, bookName, bookAuthor, bookCategory, true));
        System.out.println(" Book added successfully.");
    }

    @Override
    public void addUser() {
        System.out.println("Enter User ID: ");
        String userID = sc.nextLine();
        System.out.println("Enter User Name: ");
        String userName = sc.nextLine();
        System.out.println("Enter User Email: ");
        String userEmail = sc.nextLine();
        System.out.println("Enter User Phone: ");
        String userPhone = sc.nextLine();
        System.out.println("Enter User Address: ");
        String userAddress = sc.nextLine();

        users.add(new User(userID, userName, userEmail, userPhone, userAddress));
        System.out.println("<UNK> User added successfully.");
    }

    @Override
    public void editBook() {
        try {
            System.out.println("Enter Book ID to edit: ");
            String bookID = sc.nextLine();

            for (Book book : books) {
                if (bookID.equals(book.getId())) {
                    System.out.println("Enter new title: ");
                    book.setName(sc.nextLine());
                    System.out.println("Enter new author: ");
                    book.setAuthor(sc.nextLine());
                    System.out.println("Enter new category: ");
                    book.setCategory(sc.nextLine());
                    System.out.println(" Book updated successfully.");
                }
            }
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook() {
        try {
            System.out.println("Enter Book ID to delete: ");
            String bookID = sc.nextLine();
            if (books.isEmpty()) {
                System.out.println(" No books to delete.");
            }
            for (Book book : books) {
                if (book.getId().equals(bookID)) {
                    books.remove(book);
                    break;
                }
            }
            System.out.println("<UNK> Book deleted successfully.");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    @Override
    public void searchBookID() {
        System.out.println("Enter Book ID to search: ");
        String bookID = sc.nextLine();
        if (books.isEmpty()) {
            System.out.println(" Book list is empty.");
        }
        for (Book book : books) {
            if (book.getId().equals(bookID)) {
                System.out.println(book);
            }
        }
    }

    @Override
    public void searchBookAuthor() {
        System.out.println("Enter author name to search: ");
        String bookAuthor = sc.nextLine();
        if (books.isEmpty()) {
            System.out.println(" Book list is empty.");
        }
        for (Book book : books) {
            if (book.getAuthor().equals(bookAuthor)) {
                System.out.println(book);
            }
        }
    }

    @Override
    public void searchBookCategory() {
        System.out.println("Enter category to search: ");
        String bookCategory = sc.nextLine();
        if (books.isEmpty()) {
            System.out.println(" Book list is empty.");
        }
        for (Book book : books) {
            if (book.getCategory().equals(bookCategory)) {
                System.out.println(book);
            }
        }
    }

    @Override
    public void viewBook() {
        if (books.isEmpty()) {
            System.out.println(" No books to display.");
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Override
    public void viewUser() {
        if (users.isEmpty()) {
            System.out.println(" No users to display.");
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

//    @Override
//    public void saveToFile() {
//        try {
//            FileWriter file = new FileWriter("books.txt", true);
//            for (Book book : books) {
//                file.write(book.getId() + "," +
//                        book.getName() + "," +
//                        book.getAuthor() + "," +
//                        book.getCategory() + "," +
//                        book.isAvailable() + "\n");
//            }
//            file.close();
//            System.out.println("Books saved successfully.");
//        } catch (IOException e) {
//            System.out.println(" Lỗi khi lưu sách: " + e.getMessage());
//        }
//    }

    @Override
    public void sortBooksById() {
        if (books.isEmpty()) {
            System.out.println(" No books to sort.");
            return;
        }

        books.sort(Comparator.comparing(Book::getId));
        System.out.println(" Books sorted by ID.");
    }

//    @Override
//    public void saveUserFile() {
//        try {
//            FileWriter file = new FileWriter("user.txt", true);
//            for (User user : users) {
//                file.write(user.getId() + "," +
//                        user.getName() + "," +
//                        user.getEmail() + "," +
//                        user.getPhoneNumber() + "," +
//                        user.getAddress() + "\n");
//            }
//            file.close();
//            System.out.println("Users saved successfully.");
//        } catch (IOException e) {
//            System.out.println(" Error saving list: " + e.getMessage());
//        }
//    }
//    @Override
//    public void loadToFile() {
//        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] data = line.split(",");
//                if (data.length == 5) {
//                    String id = data[0];
//                    String name = data[1];
//                    String author = data[2];
//                    String category = data[3];
//                    boolean available = Boolean.parseBoolean(data[4]);
//
//                    books.add(new Book(id, name, author, category, available));
//                }
//            }
//
//            System.out.println(" Books loaded from file.");
//        } catch (IOException e) {
//            System.out.println(" Failed to load books: " + e.getMessage());
//        }
//    }

    private User findUserById(String id) {
        return users.stream().filter(u -> u.toString().contains(id)).findFirst().orElse(null);
    }

    private Book findBookById(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void returnBook() {
        System.out.print("User ID: ");
        String userId = sc.nextLine();
        User user = findUserById(userId);
        if (user != null) {
            System.out.print("Book ID: ");
            String bookId = sc.nextLine();
            Book book = findBookById(bookId);
            if (book != null) user.returnBook(book);
            else System.out.println("️ No books found.");
        } else System.out.println(" User not found.");
    }

    @Override
    public void borrowBook() {
        System.out.print("ID người dùng: ");
        String userId = sc.nextLine();
        User user = findUserById(userId);
        if (user != null) {
            System.out.print("ID sách: ");
            String bookId = sc.nextLine();
            Book book = findBookById(bookId);
            if (book != null) user.borrowBook(book);
            else System.out.println(" Không tìm thấy sách.");
        } else System.out.println(" Không tìm thấy người dùng.");
    }

    @Override
    public void statistics() {
        long borrowed = books.stream().filter(b -> !b.isAvailable()).count();
        System.out.println("Tổng số sách: " + books.size());
        System.out.println("Số sách đã mượn: " + borrowed);
    }

    @Override
    public void runMenu() {
        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Edit Book");
            System.out.println("4. Search Book");
            System.out.println("5. Register User");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. View Books");
            System.out.println("9. View Users");
            System.out.println("10. Book Statistics");
            System.out.println("11. Save Books to File");
            System.out.println("12. Save User to File");
            System.out.println("13. Sort");
            System.out.println("0. Exit");
            System.out.print("Select: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    deleteBook();
                    break;
                case "3":
                    editBook();
                    break;
                case "4":
                    System.out.println("1. Search by ID");
                    System.out.println("2. Search by Author");
                    System.out.println("3. Search by Category");
                    System.out.print("Select search option: ");
                    String opt = sc.nextLine();
                    switch (opt) {
                        case "1":
                            searchBookID();
                            break;
                        case "2":
                            searchBookAuthor();
                            break;
                        case "3":
                            searchBookCategory();
                            break;
                        default:
                            System.out.println(" Invalid search option.");
                    }
                    break;
                case "5":
                    addUser();
                    break;
                case "6":
                    borrowBook();
                    break;
                case "7":
                    returnBook();
                    break;
                case "8":
                    viewBook();
                    break;
                case "9":
                    viewUser();
                    break;
                case "10":
                    statistics();
                    break;
                case "11":
                    fw.saveToFile();
                    break;
                case "12":
                    fw.saveUserFile();
                    fw.ReadUserFile();
                    break;
                case "13":
                    sortBooksById();
                    break;
                case "0":
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        }
    }

}
