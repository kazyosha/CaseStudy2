package src;

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
            FileWriter file = new FileWriter("books.txt", true);
            for (Book book : books) {
                file.write(book.getId() + "," +
                        book.getName() + "," +
                        book.getAuthor() + "," +
                        book.getCategory() + "," +
                        book.isAvailable() + "\n");
            }
            file.close();
            System.out.println("Books saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi lưu sách: " + e.getMessage());
        }
    }

    @Override
    public void saveUserFile() {
        try {
            FileWriter file = new FileWriter("user.txt", true);
            for (User user : users) {
                file.write(user.getId() + "," +
                        user.getName() + "," +
                        user.getEmail() + "," +
                        user.getPhoneNumber() + "," +
                        user.getAddress() + "\n");
            }
            file.close();
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving list: " + e.getMessage());
        }
    }

    @Override
    public void ReadBookToFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            System.out.println("📄 Nội dung file books.txt:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }


    @Override
    public void ReadUserFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            System.out.println("📄 Nội dung file user.txt:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("❌ Error reading file: " + e.getMessage());
        }
    }
}
