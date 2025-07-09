package src;

import Interface.IFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler implements IFile {
    private ArrayList<Book> books;
    private ArrayList<User> users;

    public FileHandler(ArrayList<Book> books, ArrayList<User> users) {
        this.books = books;
        this.users = users;
    }

    @Override
    public void saveToFile() {
        try {
            FileWriter file = new FileWriter("books.txt");
            for (Book book : books) {
                file.write(book.getId() + ";" +
                        book.getName() + ";" +
                        book.getAuthor() + ";" +
                        book.getCategory() + ";" +
                        book.isAvailable() + "\n");
            }
            file.close();
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println(" Error while saving book: " + e.getMessage());
        }
    }

    @Override
    public void saveUserFile() {
        try {
            FileWriter file = new FileWriter("user.txt", true);
            for (User user : users) {
                file.write(user.getId() + ";" +
                        user.getName() + ";" +
                        user.getEmail() + ";" +
                        user.getPhoneNumber() + ";" +
                        user.getAddress() + "\n");
            }
            file.close();
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.out.println(" Error saving list: " + e.getMessage());
        }
    }

    @Override
    public void ReadBookToFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            System.out.println("books.txt file content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    @Override
    public void ReadUserFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            System.out.println("user.txt file content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(" Error reading file: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<User> loadUsersFromFile() {
        ArrayList<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 5) {
                    User user = new User(data[0], data[1], data[2], data[3], data[4]);
                    userList.add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return userList;
    }

    @Override
    public ArrayList<Book> loadBooksFromFile() {
        ArrayList<Book> bookList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 5) {
                    String id = data[0];
                    String name = data[1];
                    String author = data[2];
                    String category = data[3];
                    boolean available = Boolean.parseBoolean(data[4]);
                    bookList.add(new Book(id, name, author, category, available));
                }
            }
        } catch (IOException e) {
            System.out.println(" Error reading book file: " + e.getMessage());
        }
        return bookList;

    }
}
